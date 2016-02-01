package com.example.dell.arsenaldelhi;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Blog_Content extends AppCompatActivity {
Toolbar toolbar;
    String objectId;
    TextView tv1;
    TextView tv2;
    ParseImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog__content);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        iv=(ParseImageView)findViewById(R.id.blog_image);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        objectId=i.getStringExtra("objectId");
        ParseQuery<ParseObject>query=new ParseQuery<ParseObject>("Blogger");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null){
                    String title=object.getString("title");
                    String content=object.getString("content");
                    tv1.setText(title);
                    tv2.setText(content);
                    ParseFile file=object.getParseFile("photo");
                    iv.setParseFile(file);
                    iv.loadInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            // nothing to do
                        }
                    });
                }
                else{

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blog__content, menu);
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
