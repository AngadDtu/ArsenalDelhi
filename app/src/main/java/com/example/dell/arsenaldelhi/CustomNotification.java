package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DELL on 18-01-2016.
 */
public class CustomNotification extends ParseQueryAdapter<notify>{

    public CustomNotification(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<notify>() {
            @Override
            public ParseQuery<notify> create() {
                ParseQuery query = new ParseQuery("notify");
                return query;
            }
        });

    }

    @Override
    public View getItemView(notify object, View v, ViewGroup parent) {
        if(v==null){
            v=View.inflate(getContext(),R.layout.notification_list_item,null);
        }
        super.getItemView(object,v,parent);
        TextView title=(TextView)v.findViewById(R.id.titleText);
        TextView message=(TextView)v.findViewById(R.id.messageText);
        TextView date=(TextView)v.findViewById(R.id.date);
        title.setText(object.getTitle());
        message.setText(object.getMessage());
        String s=object.getDate().substring(0,object.getDate().indexOf("G"));
        date.setText(s);
        return  v;
    }
}
