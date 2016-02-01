package com.example.dell.arsenaldelhi;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;

/**
 * Created by DELL on 17-01-2016.
 */
@ParseClassName("season")
public class season extends ParseObject implements Serializable {
public String getTitle(){
    return getString("year");
}
    public static ParseQuery<season> getQuery() {
        return ParseQuery.getQuery(season.class);
    }
}
