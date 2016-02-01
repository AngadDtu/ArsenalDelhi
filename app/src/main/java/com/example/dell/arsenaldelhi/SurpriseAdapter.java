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
 * Created by DELL on 22-01-2016.
 */
public class SurpriseAdapter extends ParseQueryAdapter<surprise> {
    public SurpriseAdapter(Context context) {
        super(context, new QueryFactory<surprise>() {
            @Override
            public ParseQuery<surprise> create() {
                ParseQuery query = new ParseQuery("surprise");
                return query;
            }
        });
    }
    @Override
    public View getItemView(surprise object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.merchand_list_item, null);
        }

        super.getItemView(object, v, parent);

        ParseImageView mealImage = (ParseImageView) v.findViewById(R.id.MerchandImage);
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
