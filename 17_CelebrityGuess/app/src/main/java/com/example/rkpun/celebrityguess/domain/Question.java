package com.example.rkpun.celebrityguess.domain;

import java.util.HashMap;
import java.util.Map;

public class Question {

    private String photoUrl = null;
    private Map<String, String> options = null;
    private String answerOption = null;
    private String userOption = null; // for future, incase we need to show a listing of user options against correct answers

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
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

    public void addOption(String optionName, String optionValue){
        if(options == null){
            options = new HashMap<String, String>();
        }
        options.put(optionName, optionValue);
    }

    public String getAnswer(){
        if(options==null || options.isEmpty() || answerOption==null || answerOption.equals("") || !options.containsKey(answerOption) ){
            return null;
        }
        return options.get(answerOption);
    }

}
