package com.saviour.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnGo,answerBtn0,answerBtn1,answerBtn2,answerBtn3,btnReset;
    private ConstraintLayout UiHolder;
    private Random randomNumber;
    private TextView sumView,resultView,scoreView,timerView;
    private int locationOfCorrectAnswer,numberOfQuestions = 0,score = 0;
    private ArrayList<Integer> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = findViewById(R.id.btn_go);
        sumView = findViewById(R.id.sum_text);
        resultView = findViewById(R.id.result_text);
        scoreView = findViewById(R.id.score_text);
        timerView = findViewById(R.id.timer_text);
        btnReset = findViewById(R.id.btnReset);
        UiHolder = findViewById(R.id.UI_holder);
        randomNumber = new Random();
        answerBtn0 = findViewById(R.id.button0);
        answerBtn1 = findViewById(R.id.button1);
        answerBtn2 = findViewById(R.id.button2);
        answerBtn3 = findViewById(R.id.button3);
        answers = new ArrayList<Integer>();


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGo.setVisibility(View.GONE);
                UiHolder.setVisibility(View.VISIBLE);
                setTimerView();
                setSumTextView();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

    }

    private void reset() {
        answers.clear();
        score = 0;
        numberOfQuestions = 0;

        setSumTextView();
        setTimerView();
        resultView.setVisibility(View.INVISIBLE);
        btnReset.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view) {
        resultView.setVisibility(View.VISIBLE);
        if( Integer.toString(locationOfCorrectAnswer).equals(view.getTag())){
            resultView.setText("Correct !!");
            score++;
        }
        else resultView.setText("Wrong :(");

        numberOfQuestions++;
        answers.clear();
        setSumTextView();
    }

    private void setSumTextView(){
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        int a = randomNumber.nextInt(21);
        int b = randomNumber.nextInt(21);

        sumView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        setAnswerButtons(a,b);
    }

    private void setAnswerButtons(int a,int b){
        locationOfCorrectAnswer = randomNumber.nextInt(4);

        for (int i=0 ; i<4 ; i++ ){
            if(i == locationOfCorrectAnswer)
                answers.add(a+b);
            else {
                int randomAnswer = randomNumber.nextInt(41);
                while (randomAnswer == a+b){
                    randomAnswer = randomNumber.nextInt(41);
                }
                answers.add(randomAnswer);
            }
        }

        answerBtn0.setText(Integer.toString(answers.get(0)));
        answerBtn1.setText(Integer.toString(answers.get(1)));
        answerBtn2.setText(Integer.toString(answers.get(2)));
        answerBtn3.setText(Integer.toString(answers.get(3)));
    }

    private void setTimerView(){
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf(millisUntilFinished/1000) + "s" );
            }

            @Override
            public void onFinish() {
                resultView.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                resultView.setText("Done");
            }
        }.start();
    }
}