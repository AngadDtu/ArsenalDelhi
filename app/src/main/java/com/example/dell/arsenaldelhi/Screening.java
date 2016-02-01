package com.example.dell.arsenaldelhi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;

public class Screening extends AppCompatActivity {
    Toolbar toolbar;
    ParseQueryAdapter<Screenings> mainAdapter;
ProgressDialog progress;
FavoriteMatchAdapter favoritesAdapter;
    String s;
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenings);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Subclass of ParseQueryAdapter
        favoritesAdapter = new FavoriteMatchAdapter(this);
        ListView list = (ListView) findViewById(R.id.screen_list);
        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.screening_title, list, false);
        final TextView text = (TextView) headerView.findViewById(R.id.txtname);
        progress = new ProgressDialog(this);
        progress.setMessage("Loading...Please Wait!!");
        progress.show();
        ParseQuery<Screenings> query = new ParseQuery<Screenings>("Screenings");
        query.findInBackground(new FindCallback<Screenings>() {
            @Override
            public void done(List<Screenings> objects, ParseException e) {
                if (e == null) {
                    for (Screenings screen : objects) {
                        s = screen.getTitle();
                    }
                }
                text.setText(s);
                progress.dismiss();
            }
        });
        list.addHeaderView(headerView);
        list.setAdapter(favoritesAdapter);



    }






    @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_screenings, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }
            if (id == android.R.id.home) {
                NavUtils.navigateUpFromSameTask(this);
            }


            return super.onOptionsItemSelected(item);
        }

    }



