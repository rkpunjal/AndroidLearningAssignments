package com.example.rkpun.download1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //private static String DATA_URL = "https://drive.google.com/open?id=0B8g9SuVKESoBQU9kWml3NU5fblU";
    private static String DATA_URL = "http://time.jsontest.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView urlTextView = (TextView)findViewById(R.id.urlTextView);
        urlTextView.setText(DATA_URL);

        TextView urlContentsTextView = (TextView)findViewById(R.id.urlContentsTextView);

        UrlResponseDownloadTask urlResponseDownloadTask = new UrlResponseDownloadTask();
        try {
            String response = urlResponseDownloadTask.execute(DATA_URL).get();
            urlContentsTextView.setText(response);

        } catch (InterruptedException e) {
            Log.e("MainActivity", "InterruptedException from : " + DATA_URL, e);
        } catch (ExecutionException e) {
            Log.e("MainActivity", "ExecutionException from : " + DATA_URL, e);
        }

    }
}
