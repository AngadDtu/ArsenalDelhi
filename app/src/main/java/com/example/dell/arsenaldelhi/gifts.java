package com.example.dell.arsenaldelhi;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by DELL on 12-01-2016.
 */
@ParseClassName("gifts")
public class gifts extends ParseObject {
public  gifts(){}
    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }

    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }
    public  String getTitle(){
        return getString("title");
    }
    public  void setTitle(String title){
        put("title",title);
    }
    public static ParseQuery<Screenings> getQuery() {
        return ParseQuery.getQuery(Screenings.class);
    }

}
