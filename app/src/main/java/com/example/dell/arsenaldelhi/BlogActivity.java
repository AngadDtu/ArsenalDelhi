package com.example.dell.arsenaldelhi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class BlogActivity extends AppCompatActivity  {
Toolbar toolbar;
    ArrayList<Blogger>blogger;
    CustomQueryAdapter blogAdapter;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        blogger=new ArrayList<>();
        progress = new ProgressDialog(this);
        progress.setMessage("Loading...Please Wait!!");
        progress.show();
        ParseQuery<Blogger> query=new ParseQuery<Blogger>("Blogger");
        query.orderByDescending("createdAt").setLimit(10);
        query.findInBackground(new FindCallback<Blogger>() {
            @Override
            public void done(List<Blogger> blogs, ParseException e) {
                if(e!=null)
                    Toast.makeText(BlogActivity.this,"Error"+e,Toast.LENGTH_SHORT).show();
                for(Blogger blog: blogs){
                    Blogger newblog=new Blogger();
                    newblog.setAuthor(blog.getAuthor());
                    newblog.setTitle(blog.getTitle());
                    newblog.setBrief(blog.getBrief());
                    newblog.setContent(blog.getContent());
                    newblog.setDate(blog.getDate());
                    blogger.add(newblog);
                }
                progress.dismiss();
            }
        });

        blogAdapter=new CustomQueryAdapter(this);
        ListView list=(ListView)findViewById(R.id.blog_list);
        list.setAdapter(blogAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blog, menu);
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
