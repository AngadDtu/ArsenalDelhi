package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by DELL on 18-01-2016.
 */
public class CustomEvents extends ParseQueryAdapter<events> {
    public CustomEvents(Context context) {
        super(context, new QueryFactory<events>() {
            @Override
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");
                return query;
            }
        });
    }

    @Override
    public View getItemView(events object, View v, ViewGroup parent) {
        if(v==null){
            v=View.inflate(getContext(),R.layout.events_list_item,null);
        }
        super.getItemView(object,v,parent);
        TextView title=(TextView)v.findViewById(R.id.eventTitle);
        TextView message=(TextView)v.findViewById(R.id.message);
        TextView date=(TextView)v.findViewById(R.id.eventDate);
        ParseImageView MatchImage = (ParseImageView) v.findViewById(R.id.imageView);
        ParseFile photoFile = object.getParseFile("photo");
        if (photoFile != null) {
            MatchImage.setParseFile(photoFile);
            MatchImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }
        title.setText(object.getTitle());
        date.setText(object.getDate());
        message.setText(object.getMessage());
return  v;
    }
}
