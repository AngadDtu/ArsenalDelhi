package com.example.dell.arsenaldelhi;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Table extends AppCompatActivity implements TableAsyncTaskInterface {
Toolbar toolbar;
    ProgressDialog progress;
    ArrayList<Tables>tables;
    TableAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String  urlString=getURLString();
        TableAsyncTask task=new TableAsyncTask();
        task.listener = this;
        task.execute(urlString);
        progress = new ProgressDialog(Table.this);
        progress.setMessage("Loading...Please Wait!!");
        progress.show();
        tables=new ArrayList<>();
        adapter=new TableAdapter(Table.this,tables);
        ListView list=(ListView)findViewById(R.id.table_list);
        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.league_table_header, list, false);
        headerView.setBackgroundColor(Color.LTGRAY);
        list.addHeaderView(headerView);
        list.setAdapter(adapter);
    }

    private String getURLString() {
        return "http://api.football-data.org/v1/soccerseasons/398/leagueTable";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table, menu);
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
    public void TableTaskOnComplete(Tables[] t) {
        tables.clear();
        for(Tables tab : t){
            tables.add(tab);
        }
        adapter.notifyDataSetChanged();
        progress.dismiss();
    }
}
