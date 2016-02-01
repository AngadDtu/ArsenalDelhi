package com.example.dell.arsenaldelhi;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment  {
    private RecyclerView recyclerView;
    private drawerAdapter adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private View containerView;
    private static final String PREF_FILE_NAME = "check.txt";
    private static final String KEY_MUSER_LEARNED = "user_learned_drawer";

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_MUSER_LEARNED, "false"));
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler);
        adapter = new drawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<information> getData() {
        List<information> data = new ArrayList<>();
        int[] icons = {R.drawable.homey, R.drawable.events, R.drawable.shop, R.drawable.news, R.drawable.location, R.drawable.facebook,R.drawable.twitter, R.drawable.feedback, R.drawable.share};
        String[] titles = {"Home", "Event", "Merchandises", "Arsenal News", "Contact Us", "Facebook","Twiter", "Feedback", "Share App"};
        for (int i = 0; i < icons.length && i < titles.length; i++) {
            information current = new information();
            current.IconId = icons[i];
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    public void setup(int Fragment_Id, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        containerView = getActivity().findViewById(Fragment_Id);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_MUSER_LEARNED, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.7) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }

        };
        if (!mUserLearnedDrawer) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context, String PreferenceName, String PreferenceValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PreferenceName, PreferenceValue);
        editor.commit();

    }

    public static String readFromPreferences(Context context, String PreferenceName, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString(PreferenceName, defaultValue);
    }
}