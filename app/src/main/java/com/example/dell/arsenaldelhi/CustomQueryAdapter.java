package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by DELL on 09-01-2016.
 */
public class CustomQueryAdapter extends ParseQueryAdapter<Blogger> {
    public CustomQueryAdapter(Context context) {
        super(context, new QueryFactory<Blogger>() {
            @Override
            public ParseQuery<Blogger> create() {
                ParseQuery query = new ParseQuery("Blogger");
                return query;
            }
        });
    }

    @Override
    public View getItemView(final Blogger object, View v, final ViewGroup parent) {
        if (v == null){
            v = View.inflate(getContext(), R.layout.blogs_item_view, null);
        }

        super.getItemView(object, v, parent);
        TextView Author=(TextView)v.findViewById(R.id.writter);
        TextView title=(TextView)v.findViewById(R.id.title);
        TextView brief=(TextView)v.findViewById(R.id.brief);
        TextView date=(TextView)v.findViewById(R.id.date);

        ParseImageView BlogImage = (ParseImageView) v.findViewById(R.id.image_heading);
        ParseFile photoFile = object.getParseFile("photo");
        if (photoFile != null) {
            BlogImage.setParseFile(photoFile);
            BlogImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }
        Author.setText(object.getAuthor());
        title.setText(object.getTitle());
        brief.setText(object.getBrief());
        date.setText(object.getDate());
        Button button=(Button)v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objectId=object.getObjectId();
                Intent i=new Intent(getContext(),Blog_Content.class);
                i.putExtra("objectId", objectId);
                getContext().startActivity(i);
            }
        });
        return v;
    }
}
