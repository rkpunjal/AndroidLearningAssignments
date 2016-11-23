package com.example.rkpun.download1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlResponseDownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String...  urls) {
        String downloadUrl = urls[0];

        String contents ="";
        try {
            URLConnection conn = new URL(downloadUrl).openConnection();
            InputStream in = conn.getInputStream();
            contents = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e("UrlResponseDownloadTask", "MalformedURL", e);
        } catch (IOException e) {
            Log.e("UrlResponseDownloadTask", "IOException", e);
        }
        return contents;
    }

    private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {

        BufferedReader reader = new BufferedReader(new
                InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            Log.e("UrlResponseDownloadTask", "exception downloading", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e("UrlResponseDownloadTask", "exception downloading", e);
            }
        }
        return sb.toString();
    }

}
