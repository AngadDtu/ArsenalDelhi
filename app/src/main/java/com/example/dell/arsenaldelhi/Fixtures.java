package com.example.dell.arsenaldelhi;

import android.app.ProgressDialog;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Fixtures extends AppCompatActivity implements FixtureAsyncTaskInterface {
Toolbar toolbar;
    ArrayList<fixture>fixtures;
    ProgressDialog progress;
    FixtureAdapter adapter;
    String[]s=new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fixtures=new ArrayList<>();
String urlString=getURLString();
FixtureAsyncTask task=new FixtureAsyncTask();
        task.listener = this;
        task.execute(urlString);
        progress = new ProgressDialog(Fixtures.this);
        progress.setMessage("Loading...Please Wait!!");
        progress.show();
        final ListView list=(ListView)findViewById(R.id.fixture_list);
        ParseQuery<season>query=new ParseQuery<season>("season");
        query.findInBackground(new FindCallback<season>() {
            @Override
            public void done(List<season> objects, ParseException e) {
                if (e == null) {
                    int i=0;
                    for (season year : objects) {
                       s[i]=year.getString("year");
                        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.kit_header, list, false);
                        list.addHeaderView(headerView);
                        TextView text=(TextView)headerView.findViewById(R.id.kitname);
                        text.setText("Season " +s[0]);
                        break;
                    }
                }
                    else{

                    }

            }
        });

        fixtures = new ArrayList<>();
        adapter=new FixtureAdapter(Fixtures.this,fixtures);
        list.setAdapter(adapter);




    }

    private String getURLString() {
        return "http://api.football-data.org/v1/teams/57/fixtures";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fixtures, menu);
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


    @Override
    public void FixtureTaskOnComplete(fixture[] f) {

          fixtures.clear();
        for(fixture match : f){
            fixtures.add(match);
        }
        adapter.notifyDataSetChanged();
        progress.dismiss();

    }
}

