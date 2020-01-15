package com.github.houbb.sensitive.word.data;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.11
 */
public class NumUtilTest {

    @Test
    public void groupNumTest() {
        String nums = "１２３４５６７８９" +
                "一二三四五六七八九" +
                "壹贰叁肆伍陆柒捌玖" +
                "¹²³⁴⁵⁶⁷⁸⁹" +
                "₁₂₃₄₅₆₇₈₉" +
                "①②③④⑤⑥⑦⑧⑨" +
                "⑴⑵⑶⑷⑸⑹⑺⑻⑼" +
                "⒈⒉⒊⒋⒌⒍⒎⒏⒐" +
                "❶❷❸❹❺❻❼❽❾" +
                "➀➁➂➃➄➅➆➇➈" +
                "➊➋➌➍➎➏➐➑➒" +
                "㈠㈡㈢㈣㈤㈥㈦㈧㈨" +
                "⓵⓶⓷⓸⓹⓺⓻⓼⓽" +
                "㊀㊁㊂㊃㊄㊅㊆㊇㊈" +
                "ⅰⅱⅲⅳⅴⅵⅶⅷⅸ" +
                "ⅠⅡⅢⅣⅤⅥⅦⅧⅨ";

        for(int l = 0; l < 9; l++) {
            for(int i = 0; i < 16; i++) {
                System.out.print(nums.charAt(i*9+l)+" ");
            }
            System.out.println();
        }

    }


    @Test
    public void groupEnglishTest() {
        List<String> lines = Arrays.asList("ⒶⒷⒸⒹⒺⒻⒼⒽⒾⒿⓀⓁⓂⓃⓄⓅⓆⓇⓈⓉⓊⓋⓌⓍⓎⓏ",
                "ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩ",
                "⒜⒝⒞⒟⒠⒡⒢⒣⒤⒥⒦⒧⒨⒩⒪⒫⒬⒭⒮⒯⒰⒱⒲⒳⒴⒵");
        for(int i = 0; i < 26; i++) {
            System.out.print(lines.get(0).charAt(i)+" ");
            System.out.print(lines.get(1).charAt(i)+" ");
            System.out.print(lines.get(2).charAt(i));
            System.out.println();
        }

    }

}
