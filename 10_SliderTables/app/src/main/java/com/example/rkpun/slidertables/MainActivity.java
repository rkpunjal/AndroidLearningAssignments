package com.example.rkpun.slidertables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int SEEK_MIN_VALUE = 1;
    public static int SEEK_MAX_VALUE = 20;
    public static int SEEK_DEFAULT_VALUE = 10;

    private SeekBar seekBar;
    private ListView tablesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.tablesSeekBar);
        tablesListView = (ListView)findViewById(R.id.tablesListView);

        initializeSeekbar();

    }

    private void initializeSeekbar() {

        seekBar.setMax(SEEK_MAX_VALUE);
        seekBar.setProgress(SEEK_DEFAULT_VALUE);

        showTablesInListView(SEEK_DEFAULT_VALUE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progress = 1;
            int previous=1;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;

                if(progressValue < SEEK_MIN_VALUE){
                    progress = SEEK_MIN_VALUE;
                    seekBar.setProgress(SEEK_MIN_VALUE);
                }

                if(progress != previous){
                    previous = progress;
                    showTablesInListView(progress);
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void showTablesInListView(int tableNumber){

        Log.i("info", "tableNumber : " + tableNumber);

//        final List<String> tables = new ArrayList<String>();
//        for(int i=1; i<=10; i++){
//            tables.add(String.valueOf(i*tableNumber));
//        }
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableNumber);
//        tablesListView.setAdapter(arrayAdapter);

        final List<String> tables = new ArrayList<String>();
//        tables.add("Ram");
//        tables.add("Babita");
//        tables.add("Advait");
//        tables.add("Someone");
//        tables.add("Somebody");
//        tables.add("Noone");
//        tables.add("Nobody");

        for(int i = 1; i<=10; i++){
            tables.add(String.valueOf(i*tableNumber));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tables);
        tablesListView.setAdapter(arrayAdapter);

    }


}
