package com.example.dell.arsenaldelhi;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Executive_members extends AppCompatActivity {
ExecutiveAdapter adapter;
    ArrayList<members>data;
    int i;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executive_members);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data=new ArrayList<>();
        String[] name={"Shalin Ahuja","Nishant Singhal","Aditya Wadhwani","Varun Khanna","Sumer Singh","Swadeep Popli"};
        String []designation={"Chairman","Club Secretary","Treasurer","Member","Member","Founder & CEO of THE CHATTER HOUSE"};
        String[] address={"shalin@arsenaldelhi.com","nishant@arsenaldelhi.com","aditya@arsenaldelhi.com","varun@arsenaldelhi.com","sumer@arsenaldelhi.com","swadeep@arsenaldelhi.com"};
        Integer[] image={R.drawable.person,R.drawable.person,R.drawable.person,R.drawable.person,R.drawable.person,R.drawable.swadeep};
        for(i=0;i<6;i++) {
            data.add(new members(name[i],designation[i],address[i],image[i]));
        }
        adapter=new ExecutiveAdapter(Executive_members.this,data);
        ListView lv=(ListView)findViewById(R.id.Executive_list);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_executive_members, menu);
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
