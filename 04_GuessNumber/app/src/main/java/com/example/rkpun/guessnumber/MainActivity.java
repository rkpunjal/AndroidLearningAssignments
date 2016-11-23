package com.example.rkpun.guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int generatedNumber = generateRandumNumber();

    public void onGuessBtnClick(View view){
        EditText guessedNumberInput = (EditText) findViewById(R.id.guessedNumber);
        int guessedNumber = Integer.parseInt(guessedNumberInput.getText().toString());

        if(guessedNumber < generatedNumber){
            showToast("It's higher !");
        }else if(guessedNumber > generatedNumber){
            showToast("It's Lower !");
        }else{
            String message = "Correct ! It is : " + generatedNumber+ ".\n" + "Let's play again !";
            showToast(message);
            generatedNumber = generateRandumNumber();
        }
        guessedNumberInput.setText("");

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private int generateRandumNumber(){
        Random rand = new Random();
        return rand.nextInt(20) + 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
