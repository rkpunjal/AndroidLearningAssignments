package com.example.rkpun.celebrityguess.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by rkpun on 11/22/2016.
 */

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
            Log.e("ImageDownloaderTask", "MalformedURL", e);
        } catch (IOException e) {
            Log.e("ImageDownloaderTask", "IOException", e);
        }catch (Exception e){
            Log.e("ImageDownloaderTask", "Exception", e);
        }
        return null;
    }
}
