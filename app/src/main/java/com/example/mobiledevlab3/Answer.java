package com.example.mobiledevlab3;

public class Answer {
    private final String AnswerText;
    private final boolean correctly;

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
