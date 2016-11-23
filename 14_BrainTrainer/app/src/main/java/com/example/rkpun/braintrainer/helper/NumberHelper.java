package com.example.rkpun.braintrainer.helper;

import java.util.Random;

public class NumberHelper {

    public static int getRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max+1);
    }

    public static int getRandomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
