package com.example.dell.arsenaldelhi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
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
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class Membership extends AppCompatActivity {
Toolbar toolbar;
boolean b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[]data={"Why should i join?","Apply Here","FAQS"};
        ArrayAdapter<String> arrayAdapter1 = new  ArrayAdapter<String> (this, android.R.layout.simple_list_item_1,data);
        ListView lv=(ListView)findViewById(R.id.members_list);
        ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)lv.getLayoutParams();
        listViewParams.height = 400;
        lv.requestLayout();
        lv.setAdapter(arrayAdapter1);
        ParseQuery<member>query=new ParseQuery<member>("member");
        query.findInBackground(new FindCallback<member>() {
            @Override
            public void done(List<member> objects, ParseException e) {
                if(e==null){
                    for(member members:objects){
                  b=members.getValue();
                    }
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(Membership.this, Why_should_i_join.class);
                    startActivity(i);
                }
                if (position == 1 ) {
                    if(b==true) {
                        Intent i = new Intent(Membership.this, How_to_apply.class);
                        startActivity(i);
                    }
                    else{
                        //Toast.makeText(getApplicationContext(),"Memberships are closed now...",Toast.LENGTH_LONG).show();
                         AlertDialog.Builder b=new AlertDialog.Builder(Membership.this);
                        b.setTitle("OUR MEMBERSHIPS ARE CLOSED");
                        b.setMessage("Our Memberships Are Closed Now...Keep in touch with fb page and website for further notifications");
                        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        b.create().show();
                    }
                }
                if (position == 2) {
                    Intent i = new Intent(Membership.this, FAQS.class);
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_membership, menu);
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
