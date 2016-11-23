package com.example.rkpun.rkandroid2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void onLoginClick(View view){
        EditText userNameInput = (EditText) findViewById(R.id.userName);
        EditText passwordInput = (EditText) findViewById(R.id.userPassword);
        Log.i("info", String.format("\nUser : %s\nPassword : %s", userNameInput.getText().toString(), passwordInput.getText().toString()));
        Toast.makeText(this, "Hello " + userNameInput.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
