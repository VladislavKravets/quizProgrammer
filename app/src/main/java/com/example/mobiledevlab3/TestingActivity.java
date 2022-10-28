package com.example.mobiledevlab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestingActivity extends AppCompatActivity {
    int idQuestions = 0;
    int correct = 0;
    RadioGroup radioGroup;
    List<Questions> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        radioGroup = findViewById(R.id.radioGroup);

        init();
    }

    public void clickToNext(View view) {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            if (questions.get(idQuestions).getAnswers(radioGroup.getCheckedRadioButtonId() - 1).correctly) {
                correct++;
            }
            idQuestions++;
        }
    }

    public void init(){
        radioGroup.clearCheck(); // чистим все переключатели
        questions.add(new Questions("Как вас зовут?",
                new Answer[]{
                        new Answer("ответ 1", false),
                        new Answer("ответ 2", false),
                        new Answer("ответ 3", false),
                        new Answer("ответ 4", true),}));
        for (Answer answer : questions.get(idQuestions).getAnswers()) {
            RadioButton newRadioButton = new RadioButton(this);
            newRadioButton.setText(answer.getAnswerText());
            radioGroup.addView(newRadioButton);
        }
    }
}