package com.example.rkpun.braintrainer.helper;

import android.util.Log;
import android.widget.Switch;

import com.example.rkpun.braintrainer.domain.Question;
import com.example.rkpun.braintrainer.domain.Quiz;

public class QuizHelper {
    public static final int MAX_QUIZ_QUESTIONS = 50;

    private static final int NUMBER_MIN = 4;
    private static final int NUMBER_MAX = 20;

    private static final int ANSWER_DIFFERENCE_RANGE = 15;

    public static Quiz buildQuiz(){
        Quiz quiz = new Quiz();
        for(int i =0; i<= MAX_QUIZ_QUESTIONS; i++){
            quiz.addQuestion(buildQuestion());
        }
        return quiz;
    }


    private static Question buildQuestion(){
        Question question = new Question();

        int a = NumberHelper.getRandomNumber(NUMBER_MIN, NUMBER_MAX);
        int b = NumberHelper.getRandomNumber(NUMBER_MIN, NUMBER_MAX);

        int answer = 0;

        int randomNumber = NumberHelper.getRandomNumber(NUMBER_MIN, NUMBER_MAX);
        int operationCode = randomNumber % 2;

        if(operationCode == 0){

            // even then add
            answer = a+b;
            question.setQuestion(a + "+" + b);

        }else{

            // odd then subtract

            if(a>b){
                answer = (a+randomNumber) - b;
                question.setQuestion((a+randomNumber) + "-" + b);
            }else{
                answer = (b+randomNumber) - a;
                question.setQuestion((b+randomNumber) + "-" + a);
            }

        }

        // which will be the right option?
        int answerOptionNumber = NumberHelper.getRandomNumber(1, 4);

        // set the correct answer
        switch (answerOptionNumber){
            case 1 :{
                question.setOptionA(String.valueOf(answer));
                question.setAnswerOption("A");
                break;
             }
            case 2 :{
                question.setOptionB(String.valueOf(answer));
                question.setAnswerOption("B");
                break;
             }
            case 3 :{
                question.setOptionC(String.valueOf(answer));
                question.setAnswerOption("C");
                break;
             }
            case 4 :{
                question.setOptionD(String.valueOf(answer));
                question.setAnswerOption("D");
                break;
             }

        }

        // populate other answers
        for(int i=1; i<=4; i++){
            switch (i){
                case 1 :{
                    if(question.getOptionA() == null){
                        question.setOptionA(String.valueOf(generateRandomAnswer(answer)));
                    }
                    break;
                }
                case 2 :{
                    if(question.getOptionB() == null){
                        question.setOptionB(String.valueOf(generateRandomAnswer(answer)));
                    }
                    break;
                }
                case 3 :{
                    if(question.getOptionC() == null){
                        question.setOptionC(String.valueOf(generateRandomAnswer(answer)));
                    }
                    break;
                }
                case 4 :{
                    if(question.getOptionD() == null){
                        question.setOptionD(String.valueOf(generateRandomAnswer(answer)));
                    }
                    break;
                }

            }
        }

        //Log.i("info", question.toString());

        return question;
    }

    private static int generateRandomAnswer(int answer){

        int randomNumber = 0;

        if(answer>=ANSWER_DIFFERENCE_RANGE){
            randomNumber = NumberHelper.getRandomNumber(1,ANSWER_DIFFERENCE_RANGE);
        }else{
            randomNumber = NumberHelper.getRandomNumber(1, ANSWER_DIFFERENCE_RANGE-answer);
        }

        int operationCode = randomNumber % 2;

        if(operationCode == 0){
            // even then add
            return answer + randomNumber;
        }else{
            // odd then subtract
            return answer - randomNumber;
        }

    }

    public static boolean registerAnswer(Quiz quiz, Question question, String answer){
        question.setUserOption(answer);

        if(question.getAnswerOption().equalsIgnoreCase(answer)){
            quiz.incrementScore();
            return true;
        }else{
            return false;
        }
    }


}
