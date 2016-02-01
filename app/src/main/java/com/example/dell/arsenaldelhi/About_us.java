package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class About_us extends AppCompatActivity {
Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[]data={" What We Do","Executive Members"};
        ArrayAdapter<String> arrayAdapter1 = new  ArrayAdapter<String> (this, android.R.layout.simple_list_item_1,data);
        ListView lv=(ListView)findViewById(R.id.about_list);
        ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)lv.getLayoutParams();
        listViewParams.height = 400;
        lv.requestLayout();
        lv.setAdapter(arrayAdapter1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent i=new Intent();
                    i.setClass(About_us.this,What_we_do.class);
                    startActivity(i);
                }
                if(position==1){
                    Intent i=new Intent();
                    i.setClass(About_us.this,Executive_members.class);
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
