package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by DELL on 12-01-2016.
 */
public class FavoriteMatchAdapter extends ParseQueryAdapter<Screenings> {

    public FavoriteMatchAdapter(Context context) {
        super(context, new QueryFactory<Screenings>() {
            @Override
            public ParseQuery<Screenings> create() {
                ParseQuery query = new ParseQuery("Screenings");
                return query;
            }
        });
    }
    @Override
    public View getItemView(final Screenings object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.screen_list_item, null);
        }

        super.getItemView(object, v, parent);

        ParseImageView mealImage = (ParseImageView) v.findViewById(R.id.MatchImage);
        ParseFile photoFile = object.getParseFile("pic");
        if (photoFile != null) {
            mealImage.setParseFile(photoFile);
            mealImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }
        return v;
    }


}