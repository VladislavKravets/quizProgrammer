package com.example.mobiledevlab3;

public class Answer {
    String AnswerText;
    boolean correctly;

    public Answer(String answerText, boolean correctly) {
        AnswerText = answerText;
        this.correctly = correctly;
    }

    public String getAnswerText() {
        return AnswerText;
    }

    public boolean isCorrectly() {
        return correctly;
    }
}
