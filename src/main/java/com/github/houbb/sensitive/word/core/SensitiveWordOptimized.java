package com.github.houbb.sensitive.word.core;

import com.github.houbb.sensitive.word.api.*;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;
import com.github.houbb.sensitive.word.core.SensitiveWord;
import com.github.houbb.sensitive.word.support.check.WordCheckResult;
import com.github.houbb.sensitive.word.support.check.WordCheckWordAllow;
import com.github.houbb.sensitive.word.support.result.WordResult;
import com.github.houbb.sensitive.word.utils.InnerWordFormatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class SensitiveWordOptimized extends SensitiveWord {

    private static final int CHUNK_SIZE = 2000; // 每个文本块的大小
    private static final ISensitiveWord INSTANCE = new SensitiveWordOptimized();

    public static ISensitiveWord getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<IWordResult> doFindAll(String text, IWordContext context) {
        return innerSensitiveWordsParallel(text, WordValidModeEnum.FAIL_OVER, context);
    }

    private List<IWordResult> innerSensitiveWordsParallel(final String text,
                                                          final WordValidModeEnum modeEnum,
                                                          final IWordContext context) {
        int length = text.length();
        List<Future<List<IWordResult>>> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < length; i += CHUNK_SIZE) {
            final int start = i;
            final int end = Math.min(i + CHUNK_SIZE, length);
            final String chunk = text.substring(start, end);

            Future<List<IWordResult>> future = executorService.submit(new Callable<List<IWordResult>>() {
                @Override
                public List<IWordResult> call() throws Exception {
                    return innerSensitiveWords(chunk, modeEnum, context, start);
                }
            });
            futureList.add(future);
        }

        List<IWordResult> results = new ArrayList<>();
        for (Future<List<IWordResult>> future : futureList) {
            try {
                results.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        return results;
    }

    private List<IWordResult> innerSensitiveWords(final String text,
                                                  final WordValidModeEnum modeEnum,
                                                  final IWordContext context,
                                                  final int offset) {
        final IWordCheck sensitiveCheck = context.sensitiveCheck();
        List<IWordResult> resultList = new ArrayList<>();
        final Map<Character, Character> characterCharacterMap = InnerWordFormatUtils.formatCharsMapping(text, context);
        final InnerSensitiveWordContext checkContext = InnerSensitiveWordContext.newInstance()
                .originalText(text)
                .wordContext(context)
                .modeEnum(modeEnum)
                .formatCharMapping(characterCharacterMap);
        final IWordResultCondition wordResultCondition = context.wordResultCondition();
        final IWordCheck wordCheckAllow = new WordCheckWordAllow();

        for (int i = 0; i < text.length(); i++) {
            WordCheckResult wordCheckAllowResult = wordCheckAllow.sensitiveCheck(i, checkContext);
            int wordLengthAllow = wordCheckAllowResult.index();
            if (wordLengthAllow > 0) {
                i += wordLengthAllow - 1;
                continue;
            }

            WordCheckResult checkResult = sensitiveCheck.sensitiveCheck(i, checkContext);
            int wordLength = checkResult.index();
            if (wordLength > 0) {
                WordResult wordResult = WordResult.newInstance()
                        .startIndex(i + offset)
                        .endIndex(i + wordLength + offset)
                        .type(checkResult.type());
                if (wordResultCondition.match(wordResult, text, modeEnum, context)) {
                    resultList.add(wordResult);
                }

                if (WordValidModeEnum.FAIL_FAST.equals(modeEnum)) {
                    break;
                }

                i += wordLength - 1;
            }
        }
        return resultList;
    }
}
