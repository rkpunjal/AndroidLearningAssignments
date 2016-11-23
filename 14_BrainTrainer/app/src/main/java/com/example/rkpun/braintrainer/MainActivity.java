package com.example.rkpun.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.rkpun.braintrainer.domain.Question;
import com.example.rkpun.braintrainer.domain.Quiz;
import com.example.rkpun.braintrainer.helper.QuizHelper;

public class MainActivity extends AppCompatActivity {

    public static final int ONE_SECOND = 1000;
    public static final int QUIZ_TIMEOUT = 30*ONE_SECOND;

    private TextView timeTextView = null;
    private TextView resultTextView = null;
    private Button playAgainButton = null;

    private TextView questionTextView = null;
    private TextView scoreTextView = null;

    private TextView optionATextView = null;
    private TextView optionBTextView = null;
    private TextView optionCTextView = null;
    private TextView optionDTextView = null;

    private Quiz quiz = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeApp();
    }

    private void initializeApp(){

        optionATextView = (TextView) findViewById(R.id.optionATextView);
        optionBTextView = (TextView) findViewById(R.id.optionBTextView);
        optionCTextView = (TextView) findViewById(R.id.optionCTextView);
        optionDTextView = (TextView) findViewById(R.id.optionDTextView);

        setupStartScreen();
    }

    private void setupStartScreen(){
        hideQuizScreen();
        hideResults();
        String descriptionText = ""
                + "\n* Tap on the Correct Option."
                + "\n* Try to score as much as you can in 30 seconds."
                + "";
        TextView descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(descriptionText);
    }

    public void onGoClick(View view){
        hideStartup();
        showQuizScreen();
    }
    public void onPlayAgainClick(View view){
       // Log.i("Info", "Clicked on Play Again");
        startQuiz();
    }

    private void hideStartup(){
        TextView appLabel = (TextView)findViewById(R.id.appLabelTextView);
        TextView descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
        Button goButton = (Button)findViewById(R.id.goButton);

        appLabel.setVisibility(View.INVISIBLE);
        descriptionTextView.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.INVISIBLE);
    }

    private void showQuizScreen(){
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.optionsGrid);

        timeTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);

        startQuiz();

    }

    private void startQuiz(){
        hideResults();
        quiz = QuizHelper.buildQuiz();
        enableAnswerOptions();
        showNextQuestion();
        showCurrentScore();
        startTimer();
    }

    private void showResults(){
        String result = String.format("%d/%d", quiz.getScore(), quiz.getCurrentQuestionIndex()+1);
        resultTextView.setText(result);

        resultTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
    }

    private void startTimer(){

        new CountDownTimer(QUIZ_TIMEOUT, ONE_SECOND) {

            public void onTick(long millisUntilFinished) {
                showTime((int)(millisUntilFinished / 1000));
            }

            public void onFinish() {
                showTime(0);
                disableAnswerOptions();
                showResults();
            }
        }.start();
    }

    private void showTime(int seconds){
        timeTextView.setText(String.format("%ds", seconds));
    }


    private void hideQuizScreen(){
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        questionTextView = (TextView)findViewById(R.id.questionTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.optionsGrid);

        timeTextView.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);

    }


    private void hideResults(){
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

    }

    private void disableAnswerOptions(){
        optionATextView.setEnabled(false);
        optionBTextView.setEnabled(false);
        optionCTextView.setEnabled(false);
        optionDTextView.setEnabled(false);
    }

    private void enableAnswerOptions(){
        optionATextView.setEnabled(true);
        optionBTextView.setEnabled(true);
        optionCTextView.setEnabled(true);
        optionDTextView.setEnabled(true);
    }

    public void onAnswerSelect(View view){
        String selectedOption = view.getTag().toString();
      //  Log.i("info", "selectedOption : " + selectedOption);
        QuizHelper.registerAnswer(quiz, quiz.getQuestions().get(quiz.getCurrentQuestionIndex()), selectedOption);
        quiz.incrementQuestionIndex();
        showNextQuestion();
        showCurrentScore();
    }

    private void showNextQuestion(){
        Question question = quiz.getQuestions().get(quiz.getCurrentQuestionIndex());

        questionTextView.setText(question.getQuestion());

        optionATextView.setText(question.getOptionA());
        optionBTextView.setText(question.getOptionB());
        optionCTextView.setText(question.getOptionC());
        optionDTextView.setText(question.getOptionD());

    }

    private void showCurrentScore(){
        scoreTextView.setText(String.valueOf(quiz.getScore()));
    }


}
