package com.example.rkpun.celebrityguess.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<Question> questions = null;
    private int score=0;
    private int currentQuestionIndex=0;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question){
        if(questions == null){
            questions = new ArrayList<Question>();
        }
        questions.add(question);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public void incrementQuestionIndex(){
        currentQuestionIndex++;
    }

    public void incrementScore(){
        score++;
    }

    public void resetForNewRound(){
        for(Question question : questions){
            question.setUserOption(null);
        }
        shuffleQuestions();
    }

    private void shuffleQuestions(){
        if(questions ==null || questions.isEmpty()){
            return;
        }
        score = 0;
        currentQuestionIndex=0;
        Collections.shuffle(questions);
    }
}
