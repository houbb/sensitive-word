package com.github.houbb.sensitive.word.data;

public class WordCountDto implements Comparable<WordCountDto> {

    private String code;
    private int count;

    public WordCountDto(String code, int count) {
        this.code = code;
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public int compareTo(WordCountDto o) {
        return this.count - o.count;
    }

    @Override
    public String toString() {
        return "{" +
                "n='" + code + '\'' +
                ", c=" + count +
                '}';
    }
}
