package com.example.dell.arsenaldelhi;

import android.app.ProgressDialog;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Event extends AppCompatActivity {
Toolbar toolbar;
    ArrayList<events>event;
    ProgressDialog progres;
    CustomEvents adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        event=new ArrayList<>();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progres=new ProgressDialog(this);
        progres.setMessage("Loading...Please Wait!!");
        progres.show();
        ParseQuery<events>query=new ParseQuery<events>("events");
        query.orderByDescending("matchday").setLimit(10);
        query.findInBackground(new FindCallback<events>() {
            @Override
            public void done(List<events> objects, ParseException e) {
                if (e == null) {
                    for (events eve : objects) {
                        events newEvent = new events();
                        newEvent.setTitle(eve.getTitle());
                        newEvent.setMessage(eve.getMessage());
                        newEvent.setDate(eve.getDate());
                        event.add(newEvent);
                    }
                }
                progres.dismiss();
            }
        });
        ListView list=(ListView)findViewById(R.id.events_list);
        adapter=new CustomEvents(getApplicationContext());
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }


        return super.onOptionsItemSelected(item);
    }
}
