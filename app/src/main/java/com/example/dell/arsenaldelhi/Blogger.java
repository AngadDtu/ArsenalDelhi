package com.example.dell.arsenaldelhi;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 08-01-2016.
 */
@ParseClassName("Blogger")
public class Blogger extends  ParseObject  implements Serializable{
    public String getAuthor() {
        return getString("Author");
    }

    public void setAuthor(String name) {
        put("Author", name);
    }

    public String getTitle() {
        return getString("title");

    }

    public void setTitle(String title) {
        put("title", title);
    }

    public String getBrief() {
        return getString("brief");
    }

    public void setBrief(String brief) {
        put("brief", brief);
    }

    public String getContent() {
        return getString("content");
    }

    public void setContent(String content) {
        put("content", content);
    }

    public String getDate() {
        return getString("date");
    }

    public void setDate(String date) {
        put("date", date);
    }
    public ParseFile getPhoto(){
        return getParseFile("photo");
    }

    public void setPhoto(ParseFile photo){
        put("photo", photo);
    }
    public static ParseQuery<Blogger> getQuery() {
        return ParseQuery.getQuery(Blogger.class);
    }

}
