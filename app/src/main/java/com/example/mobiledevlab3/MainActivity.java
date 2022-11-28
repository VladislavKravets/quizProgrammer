package com.example.mobiledevlab3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private User user;
    private int idQuestions = 0;
    private int salary = 0;
    private RadioGroup radioGroup;
    private TextView txtQuestion;
    private TextView txtStatus;
    private TextView mTextView;
    private final List<Questions> questions = fillList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.constraint_layout_stage1).setVisibility(View.VISIBLE);
        final SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

        mTextView = (TextView)findViewById(R.id.seekBarInfo);
        mTextView.setText("0");

        radioGroup = findViewById(R.id.radioGroup);
        txtQuestion = findViewById(R.id.txt_question);
        txtStatus = findViewById(R.id.txt_status);

        txtStatus.setText("Введіть початкові данні");
        getNewRadioButton(); // заполняем начальные данные
    }


    /*   Slider   */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mTextView.setText(String.valueOf(seekBar.getProgress()));
        salary = seekBar.getProgress();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /* ========= */

    /* button onClick */
    public void runOnClickQuiz(View v){
        TextView textViewFullName = findViewById(R.id.fullNameInput);
        String fullName = String.valueOf(textViewFullName.getText());
        TextView textViewAge = findViewById(R.id.ageInput);
        int age;
        if(textViewAge.getText().toString().equals("")) return;
        else age = Integer.parseInt(textViewAge.getText().toString());

        if(fullName.length() < 5) { txtStatus.setText("Не вірно введено ім'я"); return; }
        if(age <= 18 || age >= 60) { txtStatus.setText("Не вірно введено рік"); return; }

        if(salary != 0) {
            user = new User(fullName, age, salary);
            findViewById(R.id.constraint_layout_stage1).setVisibility(View.GONE);
            findViewById(R.id.constraint_layout_stage2).setVisibility(View.VISIBLE);
            txtStatus.setText("Пройдіть маленьке тестування");
            closeKeyBoard();
        }else{
            txtStatus.setText("Не вірно вибрана зарплатня");
        }
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    public void clickToNextQuiz(View view) {
        TextView txtStatusQuiz = findViewById(R.id.txt_status_quiz);
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (questions.get(idQuestions).getAnswers().get(radioGroup.getCheckedRadioButtonId() - 1).isCorrectly()) {
                user.addRating(2);
            }
            if(idQuestions != 4) {
                idQuestions++;
                getNewRadioButton();
                txtStatusQuiz.setText("Ви відповіли на: " + idQuestions + " із 5");
            }else{
                findViewById(R.id.constraint_layout_stage2).setVisibility(View.GONE);
                findViewById(R.id.constraint_layout_stage3).setVisibility(View.VISIBLE);
                txtStatus.setText("Осталось ще трохи.. Заповніть остаточні дані.");
            }
        }
    }

    public void endTestingButton(View view) {
        findViewById(R.id.constraint_layout_stage3).setVisibility(View.GONE);
        findViewById(R.id.txt_finish_status).setVisibility(View.VISIBLE);
        txtStatus.setText("Вы пройшли тест, вітаю!");
        CheckBox experience_chek = findViewById(R.id.experience_chek);
        CheckBox command_chek = findViewById(R.id.command_chek);
        CheckBox assignment_chek = findViewById(R.id.assignment_chek);
        if(experience_chek.isChecked()){
            user.addRating(2);
        }
        if(command_chek.isChecked()){
            user.addRating(1);
        }
        if(assignment_chek.isChecked()){
            user.addRating(1);
        }
        TextView txtFinish = findViewById(R.id.txt_finish_status);

        if ((user.getRating() >= 10)) {
            txtFinish.setText("Ви правильно відповіли на всі питання, з вами обов'язково звяжутся.");
        } else {
            txtFinish.setText("Вы нам не підходите, вибачте.");
        }

        txtFinish.append("\n"+user);
    }

    /* ============ */

    private void getNewRadioButton() {
        radioGroup.clearCheck(); // чистим все переключатели
        radioGroup.removeAllViews(); // чистим предыдущие колонки
        int i = 1;
        for (Answer answer : questions.get(idQuestions).getAnswers()) {
            txtQuestion.setText(questions.get(idQuestions).getQuestionsText());
            RadioButton newRadioButton = new RadioButton(this);
            newRadioButton.setId(i);
            newRadioButton.setText(answer.getAnswerText());
            radioGroup.addView(newRadioButton);
            i++;
        }
    }

    private List<Questions> fillList() {
        List<Questions> newList = new ArrayList<>();
        newList.add(new Questions("Що виведе даний код?\n" +
                "int a = 9;\n" +
                "switch (a) {\n" +
                "    case 0: System.out.print (\"0\");\n" +
                "    case 5: System.out.print (\"5\"); break;\n" +
                "    case 9: System.out.print (\"9\");\n" +
                "    case 10: System.out.print (\"10\"); break;\n" +
                "    default: System.out.print (\"!\");\n" +
                "}", new ArrayList<>(Arrays.asList(
                new Answer("9", false),
                new Answer("10", false),
                new Answer("910", true),
                new Answer("910!", false)
        ))));
        newList.add(new Questions("Які математичні операції є у Java?",
                new ArrayList<>(Arrays.asList(
                        new Answer("+, -, *, /", false),
                        new Answer("+, -, *, /, %\n", false),
                        new Answer("+, -, *, /, --, ++", false),
                        new Answer("Всі перечислені вище", true)
                ))));
        newList.add(new Questions("Скільки параметрів може використовувати функція?",
                new ArrayList<>(Arrays.asList(
                        new Answer("Не більше 3", false),
                        new Answer("Не більше 5", false),
                        new Answer("Не більше 20", false),
                        new Answer("Безгранична кількість", true)
                ))));

        newList.add(new Questions("Який метод дозволяє запустити програму Java?",
                new ArrayList<>(Arrays.asList(
                        new Answer("З методу main у будь-якому з класів", true),
                        new Answer("З класу, що був написаний першим і з методів, що є всередині нього", false),
                        new Answer("Запуск програми відбувається через компіляцію проекту, основного методу немає", false),
                        new Answer("Будь-який, його можна задавати в налаштуваннях проекту", false)
                ))));
        newList.add(new Questions("Де правильно створена проста змінна?",
                new ArrayList<>(Arrays.asList(
                        new Answer("byte x = 100000;", false),
                        new Answer("int[] a;", false),
                        new Answer("float x = 23.3f;", true),
                        new Answer("char str = 'ab';", false),
                        new Answer("bool isDone = true;", false)
                ))));
        return newList;
    }

    private void closeKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}