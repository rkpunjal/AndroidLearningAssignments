package com.example.rkpun.gridlayout1;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, Integer> phraseDb = new HashMap<String, Integer>();
    //private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializePhraseDb();
    }

    private void initializePhraseDb(){
        phraseDb.put("Hei", R.raw.hei);
        phraseDb.put("God morgen", R.raw.goodmorning_no);
        phraseDb.put("Tusen Takk", R.raw.tusen_takk);
        phraseDb.put("God kveld", R.raw.goodevening_no);
        phraseDb.put("God natt", R.raw.goodnight_no);
        phraseDb.put("Hva heter du", R.raw.name1_no);
        phraseDb.put("Jeg heter", R.raw.mynameis1_no);
        phraseDb.put("Ha det bra", R.raw.goodbye1_no);
    }

    public void playPhrase(View view){
        String phraseName = view.getTag().toString();
        Log.i("info", "Phrase : " + phraseName );
//        try {
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            // mediaPlayer.setDataSource("android.resource://" + getPackageName() + "/" + phraseDb.get(phraseName));
//            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/raw/" + "hei.mp3"));
//           // mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (IOException e) {
//            // e.printStackTrace();
//            Log.e("error","Exception trying to play audio", e);
//        }

        MediaPlayer mp = MediaPlayer.create(this, phraseDb.get(phraseName));
        //MediaPlayer mp = MediaPlayer.create(this, phraseDb.get(phraseName));
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setLooping(false);
        mp.start();
    }
}
