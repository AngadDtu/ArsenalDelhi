package com.example.dell.arsenaldelhi;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;

/**
 * Created by DELL on 09-01-2016.
 */
public class ParseActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Blogger.class);
        ParseObject.registerSubclass(Screenings.class);
        ParseObject.registerSubclass(gifts.class);
        ParseObject.registerSubclass(member.class);
        ParseObject.registerSubclass(season.class);
        ParseObject.registerSubclass(notify.class);
        ParseObject.registerSubclass(events.class);
        ParseObject.registerSubclass(surprise.class);
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "aWNJgh6Yh2tp1gqB7YNkrIjQ3YmaAnl1BfHmDozA", "vdl7nuFPfwKpIynaIQ0Q0bXoHAXZGJEdrgTz0O6O");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);
    }


}
