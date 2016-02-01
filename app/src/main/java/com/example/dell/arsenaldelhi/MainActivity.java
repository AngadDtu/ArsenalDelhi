package com.example.dell.arsenaldelhi;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView list;
    CustomArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawer_fragment);
        drawerFragment.setup(R.id.drawer_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        String[] title = {"About Us", "Blogs", "Screening", "Fixtures", "Membership", "Table"};
        int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f};
        adapter = new CustomArrayAdapter(this, title, images);
        list = (ListView) findViewById(R.id.home_list);
        list.setAdapter(adapter);
        list.setFastScrollEnabled(true);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCoun) {
            }

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState != 0)
                    adapter.isScrolling = true;
                else {
                    adapter.isScrolling = false;
                    adapter.notifyDataSetChanged();
                }
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(MainActivity.this, About_us.class);
                    startActivity(i);
                }
                if (position == 1) {
                    Intent i = new Intent(MainActivity.this, BlogActivity.class);
                    startActivity(i);
                }

                if (position == 2) {
                    Intent i = new Intent(MainActivity.this, Screening.class);
                    startActivity(i);
                }
                if (position == 3) {
                    Intent i = new Intent(MainActivity.this, Fixtures.class);
                    startActivity(i);
                }
                if (position == 4) {
                    Intent i = new Intent(MainActivity.this, Membership.class);
                    startActivity(i);
                }
                if (position == 5) {
                    Intent i = new Intent(MainActivity.this, Table.class);
                    startActivity(i);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if(id==R.id.notification){
            Intent i=new Intent(MainActivity.this,Notifications.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}