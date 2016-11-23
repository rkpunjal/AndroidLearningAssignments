package com.example.rkpun.celebrityguess.util;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.rkpun.celebrityguess.MainActivity;
import com.example.rkpun.celebrityguess.domain.Question;
import com.example.rkpun.celebrityguess.domain.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuizHelper {

    public static final String QUIZ_DATA_URL = "http://www.posh24.com/celebrities";

    public Quiz extractCelebritiesAndBuildQuiz(String responseHTML){
        Quiz quiz = new Quiz();

        List<Question> questions = new ArrayList<Question>();

        List<String> photos = new ArrayList<String>();
        List<String> names = new ArrayList<String>();

        String extract =
                responseHTML.substring(
                        responseHTML.indexOf("<p class=\"link\">Top 100 celebs</p>")+"<p class=\"link\">Top 100 celebs</p>".length()
                        , responseHTML.indexOf("<h1 class=\"header\">Trending news</h1>")
                );

        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(extract);

        while(matcher.find()) {
            String photoUrl = matcher.group(1);
            photos.add(photoUrl);
        }

        pattern = Pattern.compile("alt=\"(.*?)\"");
        matcher = pattern.matcher(extract);

        while(matcher.find()) {
            String celebrityName = matcher.group(1);
            names.add(celebrityName);
        }

        for(int counter=0; counter<photos.size(); counter++){
            String photoUrl = photos.get(counter);
            String celebrityName = names.get(counter);

            questions.add(buildQuestion(photoUrl, celebrityName, names));
        }

        quiz.setQuestions(questions);
        return quiz;
    }

    private Question buildQuestion(String photoUrl, String celebrityName, List<String> names){
        int answerOptionNumber = NumberHelper.getRandomNumber(1, 4);
        String answerOption = getOptionName(answerOptionNumber);

        Question question = new Question();

        question.setPhotoUrl(photoUrl);
        question.setAnswerOption(answerOption);

        question.addOption(answerOption, celebrityName);

        Map<String, String> options = question.getOptions();

        for(int optionCounter=1; optionCounter<=4; optionCounter++){
            String optionName = getOptionName(optionCounter);

            if(!options.containsKey(optionName)){ // leave out the answer-option (already added)

                String randomName = null;
                do{
                    randomName = getRandomName(names);
                }while(isNameAlreadyInOptions(randomName, options));

                options.put(optionName, randomName);

            }

        }

        return question;

    }

    private boolean isNameAlreadyInOptions(String nameToFind, Map<String, String> options){
        if(options==null || options.isEmpty()){
            return false;
        }
        for(String name : options.values()){
            if(name.equals(nameToFind)){
                return true;
            }
        }
        return false;
    }

    private String getRandomName(List<String> names){
        int randomNumber = NumberHelper.getRandomNumber(0, names.size()-1);
        return names.get(randomNumber);
    }


    private String getOptionName(int number){
        return String.valueOf((char) (number+64));
    }




}
