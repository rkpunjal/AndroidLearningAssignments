package com.example.rkpun.showhide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean visible = true;

    private Button showHideButton = null;
    private TextView secretText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showHideButton = (Button) findViewById(R.id.showHideButton);
        secretText = (TextView) findViewById(R.id.secretText);
    }

    public void onShowHideButtonClick(View view){
        if(visible){
            showHideButton.setText("Show");
            secretText.setVisibility(View.GONE);
            visible = false;
        }else{
            showHideButton.setText("Hide");
            secretText.setVisibility(View.VISIBLE);
            visible = true;
        }
    }
}
