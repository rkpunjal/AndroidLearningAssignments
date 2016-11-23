package com.example.rkpun.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            try {
                URLConnection conn = new URL(imageUrl).openConnection();
                conn.connect();
                InputStream in = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;

            } catch (MalformedURLException e) {
                Log.e("DownloadTask", "MalformedURL", e);
            } catch (IOException e) {
                Log.e("DownloadTask", "IOException", e);
            }catch (Exception e){
                Log.e("DownloadTask", "Exception", e);
            }
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void downloadImage(View view){
        Log.i("downloadImage", "Clicked on download Image");

        ImageDownloaderTask imageDownloaderTask = new ImageDownloaderTask();
        try {
            Bitmap myImage = imageDownloaderTask
                    .execute("https://s-media-cache-ak0.pinimg.com/originals/63/8c/7f/638c7fe8f9c2939185e32c1c03d6a0df.jpg")
                    .get();

            ImageView downloadedImage = (ImageView)findViewById(R.id.downloadedImageView);
            downloadedImage.setImageBitmap(myImage);

        } catch (InterruptedException e) {
            Log.i("downloadImage", "InterruptedException", e);
        } catch (ExecutionException e) {
            Log.i("downloadImage", "ExecutionException", e);
        } catch (Exception e){
            Log.e("downloadImage", "Exception", e);
        }

    }

}
