package com.example.dell.arsenaldelhi;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by DELL on 12-01-2016.
 */
@ParseClassName("Screenings")
public class Screenings extends ParseObject {
    public  Screenings(){}
    public ParseFile getPhotoFile() {
        return getParseFile("pic");
    }

    public void setPhotoFile(ParseFile file) {
        put("pic", file);
    }
    public  String getTitle(){
        return  getString("match");
    }
    public static ParseQuery<Screenings> getQuery() {
        return ParseQuery.getQuery(Screenings.class);
    }
}
