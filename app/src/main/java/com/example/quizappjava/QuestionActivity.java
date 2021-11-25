package com.example.quizappjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<com.example.quizappjava.Question> questions;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.question);
        optionA = findViewById(R.id.optA);
        optionB = findViewById(R.id.optB);
        optionC = findViewById(R.id.optC);
        optionD = findViewById(R.id.optD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);

        questions = new ArrayList<com.example.quizappjava.Question>(){
            {
                add(new com.example.quizappjava.Question("Who invented the BALLPOINT PEN?", "A", "Biro Brothers", "Waterman Brothers","Bicc Brothers", "Write Brothers"));
                add(new com.example.quizappjava.Question("In which decade was the first solid state integrated circuit demonstrated?", "A", "1950s", "1960s","1970s", "1980s"));
                add(new com.example.quizappjava.Question("What J. B. Dunlop invented?", "B", "Automobile wheel rim", "Pneumatic rubber tire","Rubber boot", "Model airplanes"));
                add(new com.example.quizappjava.Question("Which scientist discovered the radioactive element radium?", "D", "Isaac Newton", "Albert Einstein","Benjamin Franklin", "Marie Curie"));
                add(new com.example.quizappjava.Question("When was barb wire patented?", "C", "1840", "1895","1874", "1900"));

            }
        };

        loadQuestion();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            com.example.quizappjava.Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, com.example.quizappjava.ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.optA:
                Answer="A";
                break;

            case R.id.optB:
                Answer="B";
                break;

            case R.id.optC:
                Answer="C";
                break;

            case R.id.optD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, com.example.quizappjava.RightActivity.class);

        }else {
            screen = new Intent(this, com.example.quizappjava.WrongActivity.class);
        }

        return screen;
    }
}
