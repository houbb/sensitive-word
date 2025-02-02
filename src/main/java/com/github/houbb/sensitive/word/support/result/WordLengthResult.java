package com.github.houbb.sensitive.word.support.result;

public class WordLengthResult {
    private  int wordAllowLen;
    private  int wordDenyLen;


    private WordLengthResult(){}

    public static WordLengthResult newInstance(){
        return new WordLengthResult();
    }


    public int wordAllowLen(){
        return this.wordAllowLen;
    }
    public WordLengthResult wordAllowLen(int wordAllowLen){
        this.wordAllowLen=wordAllowLen;
        return this;
    }

    public int wordDenyLen(){
        return this.wordDenyLen;
    }
    public WordLengthResult wordDenyLen(int wordDenyLen){
        this.wordDenyLen=wordDenyLen;
        return this;
    }


}
