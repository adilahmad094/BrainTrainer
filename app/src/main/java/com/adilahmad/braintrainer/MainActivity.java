package com.adilahmad.braintrainer;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button playButton, playAgainButton, button0,button1,button2,button3;
    ImageView timerImageView, tickImageView,crossImageView, timesUpImageView;
    TextView scoreTextView, correctTextView, incorrectTextView, timerTextView;
    TextView questionTextView, resultTextView;
    ConstraintLayout layout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfAnswer;
    int correct, incorrect, score;

    CountDownTimer countDownTimer;

    public void start(View view) {

        playButton.setVisibility(View.INVISIBLE);
        layout.setBackgroundColor(getResources().getColor(R.color.white));
        resultTextView.setVisibility(View.INVISIBLE);
        timesUpImageView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        timerImageView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        tickImageView.setVisibility(View.VISIBLE);
        crossImageView.setVisibility(View.VISIBLE);
        correctTextView.setVisibility(View.VISIBLE);
        incorrectTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        correct = 0;
        incorrect = 0;
        score = 0;

        timerTextView.setText("0:30");
        correctTextView.setText("0");
        incorrectTextView.setText("0");
        scoreTextView.setText("Score : 0");

        countDownTimer = new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;
                if (seconds >= 10)
                    timerTextView.setText("0:" + Integer.toString(seconds));
                else
                    timerTextView.setText("0:0" + Integer.toString(seconds));
            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mediaPlayer.start();
                countDownTimer.cancel();

                resultTextView.setText("Score : " + Integer.toString(score) + " points");

                questionTextView.setVisibility(View.INVISIBLE);
                timerImageView.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);
                scoreTextView.setVisibility(View.INVISIBLE);
                tickImageView.setVisibility(View.INVISIBLE);
                crossImageView.setVisibility(View.INVISIBLE);
                correctTextView.setVisibility(View.INVISIBLE);
                incorrectTextView.setVisibility(View.INVISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);

                resultTextView.setVisibility(View.VISIBLE);
                timesUpImageView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(100);
        int b = rand.nextInt(100);

        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfAnswer = rand.nextInt(4);
        answers.clear();
        for (int i=0; i<4; ++i){
            if(i == locationOfAnswer)
                answers.add(a+b);
            else{
                int incorrect;
                incorrect = rand.nextInt(198);
                while (incorrect == a+b){
                    incorrect = rand.nextInt(198);
                }
                answers.add(incorrect);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public  void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfAnswer))) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            correct++;
            score++;
            scoreTextView.setText("Score : " + Integer.toString(score));
            correctTextView.setText(Integer.toString(correct));
        }
        else{
            Toast.makeText(this,"Incorrect", Toast.LENGTH_SHORT).show();
            incorrect++;
            score--;
            scoreTextView.setText("Score : " + score);
            incorrectTextView.setText(Integer.toString(incorrect));
        }

        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout)findViewById(R.id.constraintLayout);
        playButton = (Button)findViewById(R.id.playButton);
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        timerImageView = (ImageView)findViewById(R.id.timerImageView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        correctTextView = (TextView)findViewById(R.id.correctTextView);
        incorrectTextView = (TextView)findViewById(R.id.incorrectTextView);
        tickImageView = (ImageView)findViewById(R.id.tickImageView);
        crossImageView = (ImageView)findViewById(R.id.crossImageView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        timesUpImageView = (ImageView)findViewById(R.id.timesUpImageView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);


        generateQuestion();

    }
}
