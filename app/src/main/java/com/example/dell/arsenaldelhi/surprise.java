package com.example.dell.arsenaldelhi;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;

/**
 * Created by DELL on 22-01-2016.
 */
@ParseClassName("surprise")
public class surprise extends ParseObject implements Serializable {
public  void surprise(){}
    public String getTitle(){
        return getString("title");
    }
    public void setTitle(String title){
        put("title",title);
    }
    public ParseFile getPhoto(){
        return getParseFile("pic");
    }
    public static ParseQuery<surprise> getQuery() {
        return ParseQuery.getQuery(surprise.class);
    }
}
