package com.example.mobiledevlab3;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    private final String questionsText;
    private final List<Answer> answers;

    public Questions(String questionsText, ArrayList<Answer> answers) {
        this.questionsText = questionsText;
        this.answers = answers;
    }


    public String getQuestionsText() {
        return questionsText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

}
