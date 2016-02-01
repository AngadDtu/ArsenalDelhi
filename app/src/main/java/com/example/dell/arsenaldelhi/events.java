package com.example.dell.arsenaldelhi;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.Serializable;

/**
 * Created by DELL on 18-01-2016.
 */
@ParseClassName("events")
public class events extends ParseObject implements Serializable {
public  events(){

}
    public  String getTitle(){
        return  getString("match");
    }
    public  void setTitle(String  title){
        put("match",title);
    }
    public String getMessage(){
        return getString("message");
    }
    public void setMessage(String message){
        put("message",message);
    }
    public String getDate(){
        return getString("date");
    }
    public void setDate(String date){
        put("date",date);
    }
    public ParseFile getPhoto(){
        return  getParseFile("photo");
    }
    public static ParseQuery<events> getQuery() {
        return ParseQuery.getQuery(events.class);
    }

}
