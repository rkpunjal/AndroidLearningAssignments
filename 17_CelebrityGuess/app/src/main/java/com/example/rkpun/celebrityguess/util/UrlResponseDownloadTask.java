package com.example.rkpun.celebrityguess.util;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class UrlResponseDownloadTask extends AsyncTask<String, Void, String> {
    
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String...  urls) {
        String downloadUrl = urls[0];

        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(downloadUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept-Encoding", "gzip");

            Reader reader = null;
            if ("gzip".equals(con.getContentEncoding())) {
                reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()), StandardCharsets.ISO_8859_1);
            }
            else {
                reader = new InputStreamReader(con.getInputStream());
            }

            while (true) {
                int ch = reader.read();
                if (ch==-1) {
                    break;
                }
                sb.append((char)ch);
            }

        }catch (Exception e){
            Log.e("UrlResponseDownloadTask", "Exception with downloadUrl : " + downloadUrl , e);
        }
        return sb.toString();
    }


}
