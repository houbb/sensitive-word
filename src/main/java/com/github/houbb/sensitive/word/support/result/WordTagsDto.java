package com.github.houbb.sensitive.word.support.result;

import java.io.Serializable;
import java.util.Set;

/**
 * @since 0.12.0
 */
public class WordTagsDto implements Serializable {

    private String word;

    private Set<String> tags;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "WordTagsDto{" +
                "word='" + word + '\'' +
                ", tags=" + tags +
                '}';
    }

}
