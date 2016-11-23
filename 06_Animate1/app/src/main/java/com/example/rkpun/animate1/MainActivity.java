package com.example.rkpun.animate1;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean toggled = false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changeCat(View view){
        final ImageView imageView = (ImageView) findViewById(R.id.catImageView);

        imageView.animate().alpha(0f).setDuration(1000);

        if(!toggled){
            imageView.animate().alpha(0f).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(R.drawable.cat_2);
                    imageView.animate().alpha(1f).setDuration(1000);
                }
            });

            toggled = true;
        }else{
            imageView.animate().alpha(0f).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(R.drawable.cat_1);
                    imageView.animate().alpha(1f).setDuration(1000);
                    toggled = false;
                }
            });

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
