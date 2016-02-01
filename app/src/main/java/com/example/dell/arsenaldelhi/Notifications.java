package com.example.dell.arsenaldelhi;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {
Toolbar toolbar;
ArrayList<notify>notification;
    ProgressDialog progress;
    CustomNotification adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i=getIntent();
        notification=new ArrayList<>();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        progress=new ProgressDialog(Notifications.this);
        progress.setMessage("Loading...Please Wait!!");
        progress.show();
        ParseQuery<notify>query=new ParseQuery<notify>("notify");
        query.orderByDescending("score").setLimit(10);
        query.findInBackground(new FindCallback<notify>() {
            @Override
            public void done(List<notify> objects, ParseException e) {
                if (e == null) {
                    for (notify note : objects) {
                        notify newobject = new notify();
                        newobject.setTitle(note.getTitle());
                        newobject.setMessage(note.getMessage());
                        newobject.setDate(note.getDate());
                        notification.add(newobject);
                    }
                    progress.dismiss();
                }

            }
        });

        adapter = new CustomNotification(getApplicationContext());
        ListView list = (ListView) findViewById(R.id.notify_list);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
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
