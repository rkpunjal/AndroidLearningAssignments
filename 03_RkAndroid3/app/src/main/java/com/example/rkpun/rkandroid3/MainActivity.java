package com.example.rkpun.rkandroid3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean toggled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeCat(View view){
        ImageView imageView = (ImageView) findViewById(R.id.catImageView);

        if(!toggled){
            imageView.setImageResource(R.drawable.cat_2);
            toggled = true;
        }else{
            imageView.setImageResource(R.drawable.cat_1);
            toggled = false;
        }

    }

}
