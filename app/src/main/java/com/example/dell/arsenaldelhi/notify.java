package com.example.dell.arsenaldelhi;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 18-01-2016.
 */
@ParseClassName("notify")
public class notify extends ParseObject implements Serializable {
public String getTitle(){
    return getString("title");
}
    public  String getMessage(){
        return getString("message");
    }
    public void setTitle(String title){
        put("title",title);
    }
    public void setMessage(String message){
        put("message",message);
    }
    public String getDate(){
        return String.valueOf(getCreatedAt());
    }
    public void setDate(String date){
        put("createdAt",date);
    }
    public static ParseQuery<notify> getQuery() {
        return ParseQuery.getQuery(notify.class);
    }

}
