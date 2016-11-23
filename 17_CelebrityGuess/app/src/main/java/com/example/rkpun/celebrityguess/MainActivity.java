package com.example.rkpun.celebrityguess;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rkpun.celebrityguess.domain.Question;
import com.example.rkpun.celebrityguess.domain.Quiz;
import com.example.rkpun.celebrityguess.util.ImageDownloaderTask;
import com.example.rkpun.celebrityguess.util.QuizHelper;
import com.example.rkpun.celebrityguess.util.UrlResponseDownloadTask;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTextView = null;
    private ImageView photoImageView = null;

    private Button answerButtonA = null;
    private Button answerButtonB = null;
    private Button answerButtonC = null;
    private Button answerButtonD = null;

    private Quiz quiz = null;
    private QuizHelper quizHelper = new QuizHelper();

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeApp();
    }

    private void initializeApp(){

        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        photoImageView = (ImageView)findViewById(R.id.photoImageView);

        answerButtonA = (Button) findViewById(R.id.answerButtonA);
        answerButtonB = (Button) findViewById(R.id.answerButtonB);
        answerButtonC = (Button) findViewById(R.id.answerButtonC);
        answerButtonD = (Button) findViewById(R.id.answerButtonD);

        loadCelebritiesAndBuildQuiz();
    }    

    public void onAnswerSelect(View view){
        String selectedOption = view.getTag().toString();
        //  Log.i("info", "selectedOption : " + selectedOption);
        registerAnswer(quiz, quiz.getQuestions().get(quiz.getCurrentQuestionIndex()), selectedOption);
        showCurrentScore();
        quiz.incrementQuestionIndex();
        showNextQuestion();
    }

    public void startAgain(View view){
        quiz.resetForNewRound();
        showCurrentScore();
        showNextQuestion();
    }

    private void loadCelebritiesAndBuildQuiz(){

        UrlResponseDownloadTask urlResponseDownloadTask = new UrlResponseDownloadTask();
        try {
            String responseHTML = urlResponseDownloadTask.execute(QuizHelper.QUIZ_DATA_URL).get();
            // Log.i("QuizHelper", responseHTML);

            this.quiz = quizHelper.extractCelebritiesAndBuildQuiz(responseHTML);
            showCurrentScore();
            showNextQuestion();

        } catch (InterruptedException e) {
            Log.e("MainActivity", "InterruptedException from : " + QuizHelper.QUIZ_DATA_URL, e);
        } catch (ExecutionException e) {
            Log.e("MainActivity", "ExecutionException from : " + QuizHelper.QUIZ_DATA_URL, e);
        }
    }

    private boolean registerAnswer(Quiz quiz, Question question, String answer){
        question.setUserOption(answer);
        boolean isCorrect = false;
        String message = null;

        if(question.getAnswerOption().equalsIgnoreCase(answer)){
            quiz.incrementScore();
            isCorrect = true;
            message = "Correct!";
        }else{
            isCorrect = false;
            message = "Wrong! It's " + question.getAnswer();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        return isCorrect;
    }

    public void showNextQuestion(){

        Question question = quiz.getQuestions().get(quiz.getCurrentQuestionIndex());

        showPhotoAndInitializeOptions(question);

    }

    private void showPhotoAndInitializeOptions(Question question){

        ImageDownloaderTask imageDownloaderTask = new ImageDownloaderTask();
        try {
            Bitmap myImage = imageDownloaderTask
                    .execute(question.getPhotoUrl())
                    .get();

            photoImageView = (ImageView)findViewById(R.id.photoImageView);
            photoImageView.setImageBitmap(myImage);

            answerButtonA.setText(question.getOptions().get("A"));
            answerButtonB.setText(question.getOptions().get("B"));
            answerButtonC.setText(question.getOptions().get("C"));
            answerButtonD.setText(question.getOptions().get("D"));

        } catch (InterruptedException e) {
            Log.i("downloadImage", "InterruptedException", e);
        } catch (ExecutionException e) {
            Log.i("downloadImage", "ExecutionException", e);
        } catch (Exception e){
            Log.e("downloadImage", "Exception", e);
        }

    }

    private void showCurrentScore(){
        String result = String.format("%d/%d", quiz.getScore(), quiz.getCurrentQuestionIndex()+1);
        scoreTextView.setText(result);
    }

}
