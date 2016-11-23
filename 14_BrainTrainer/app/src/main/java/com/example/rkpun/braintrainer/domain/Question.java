package com.example.rkpun.braintrainer.domain;

public class Question {
    String question = null;
    String optionA = null;
    String optionB = null;
    String optionC = null;
    String optionD = null;
    String answerOption = null;
    String userOption = null;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public String getUserOption() {
        return userOption;
    }

    public void setUserOption(String userOption) {
        this.userOption = userOption;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", answerOption='" + answerOption + '\'' +
                ", userOption='" + userOption + '\'' +
                '}';
    }

/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (getQuestion() != null ? !getQuestion().equals(question1.getQuestion()) : question1.getQuestion() != null)
            return false;
        if (getOptionA() != null ? !getOptionA().equals(question1.getOptionA()) : question1.getOptionA() != null)
            return false;
        if (getOptionB() != null ? !getOptionB().equals(question1.getOptionB()) : question1.getOptionB() != null)
            return false;
        if (getOptionC() != null ? !getOptionC().equals(question1.getOptionC()) : question1.getOptionC() != null)
            return false;
        if (getOptionD() != null ? !getOptionD().equals(question1.getOptionD()) : question1.getOptionD() != null)
            return false;
        if (getAnswerOption() != null ? !getAnswerOption().equals(question1.getAnswerOption()) : question1.getAnswerOption() != null)
            return false;
        return getUserOption() != null ? getUserOption().equals(question1.getUserOption()) : question1.getUserOption() == null;

    }

    @Override
    public int hashCode() {
        int result = getQuestion() != null ? getQuestion().hashCode() : 0;
        result = 31 * result + (getOptionA() != null ? getOptionA().hashCode() : 0);
        result = 31 * result + (getOptionB() != null ? getOptionB().hashCode() : 0);
        result = 31 * result + (getOptionC() != null ? getOptionC().hashCode() : 0);
        result = 31 * result + (getOptionD() != null ? getOptionD().hashCode() : 0);
        result = 31 * result + (getAnswerOption() != null ? getAnswerOption().hashCode() : 0);
        result = 31 * result + (getUserOption() != null ? getUserOption().hashCode() : 0);
        return result;
    }
*/

}
