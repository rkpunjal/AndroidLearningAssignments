package com.example.rkpun.listview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView)findViewById(R.id.myListView);

        final List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Babita");
        names.add("Advait");
        names.add("Someone");
        names.add("Somebody");
        names.add("Noone");
        names.add("Nobody");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        myListView.setAdapter(arrayAdapter);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Toast.makeText(MainActivity.this, "Clicked on : " + names.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
