package com.example.dell.arsenaldelhi;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by DELL on 12-01-2016.
 */
@ParseClassName("member")
public class member extends ParseObject {
    public member (){}
    public boolean getValue(){
        return getBoolean("link");
    }

}
