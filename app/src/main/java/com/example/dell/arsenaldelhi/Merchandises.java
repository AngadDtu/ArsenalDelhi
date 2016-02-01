package com.example.dell.arsenaldelhi;

import android.app.ProgressDialog;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Merchandises extends AppCompatActivity {
Toolbar toolbar;
    ProgressDialog progress;
private ParseQueryAdapter<surprise> mainAdapter2;
    private ParseQueryAdapter<gifts> mainAdapter1;
    private SeasonGiftAdapter giftsAdapter;
    private SurpriseAdapter Sadapter;
    String s1;
    String s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechandises);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  mainAdapter1 = new ParseQueryAdapter<gifts>(this,gifts.class);
        //mainAdapter2=new ParseQueryAdapter<surprise>(this,surprise.class);
        // Subclass of ParseQueryAdapter
        giftsAdapter = new SeasonGiftAdapter(this);
        Sadapter=new SurpriseAdapter(this);
        ListView list=(ListView)findViewById(R.id.merchand_list);
        ViewGroup headerView1 = (ViewGroup) getLayoutInflater().inflate(R.layout.kit_header, list, false);
        final TextView title1 = (TextView) headerView1.findViewById(R.id.kitname);
        ViewGroup headerView2 = (ViewGroup) getLayoutInflater().inflate(R.layout.kit_header, list, false);
        final TextView title2 = (TextView) headerView2.findViewById(R.id.kitname);
        progress=new ProgressDialog(this);
        progress.setMessage("Loading...Please Wait!!");
        progress.show();
ParseQuery<gifts>query=new ParseQuery<gifts>("gifts");
        query.findInBackground(new FindCallback<gifts>() {
            @Override
            public void done(List<gifts> objects, ParseException e) {
                if (e == null) {
                    for (gifts gift : objects) {
                        s1 = gift.getTitle();

                    }
                }
                title1.setText(s1);
                progress.dismiss();
            }
        });
      /*  ParseQuery<surprise>query1=new ParseQuery<surprise>("surprise");
        query1.findInBackground(new FindCallback<surprise>() {
            @Override
            public void done(List<surprise> objects, ParseException e) {
                if(e==null){
                    for(surprise s:objects){
                        s2=s.getTitle();
                    }
                }
                title2.setText(s2);
                progress.dismiss();
            }
        });
        */
        list.addHeaderView(headerView1);
        list.setAdapter(giftsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mechandises, menu);
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
