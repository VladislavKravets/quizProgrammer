package com.example.mobiledevlab3;

public class Questions {
    String questionsText;
    Answer[] answers;

    public Questions(String questionsText, Answer[] text) {
        this.questionsText = questionsText;
        this.answers = text;
    }

    public String getQuestionsText() {
        return questionsText;
    }

    public Answer[] getAnswers() {
        return answers;
    }
    public Answer getAnswers(int index) {
        return answers[index];
    }
}
