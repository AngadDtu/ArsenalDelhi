package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 24-01-2016.
 */
public class TableAdapter extends ArrayAdapter<Tables>{
Context context;
ArrayList<Tables>data;
    public TableAdapter(Context context, ArrayList<Tables> resource) {
        super(context,0, resource);
        this.context=context;
        this.data=resource;
    }
protected static class MyViewHolder{
TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;

}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.table_list_item,null);
            MyViewHolder vh=new MyViewHolder();
            vh.t1=(TextView)convertView.findViewById(R.id.position);
            vh.t2=(TextView)convertView.findViewById(R.id.team);
            vh.t3=(TextView)convertView.findViewById(R.id.played);
            vh.t4=(TextView)convertView.findViewById(R.id.won);
            vh.t5=(TextView)convertView.findViewById(R.id.lose);
            vh.t6=(TextView)convertView.findViewById(R.id.draw);
            vh.t7=(TextView)convertView.findViewById(R.id.goaldiff);
            vh.t8=(TextView)convertView.findViewById(R.id.points);
convertView.setTag(vh);
        }
        Tables t=getItem(position);
        MyViewHolder v=(MyViewHolder)convertView.getTag();
        v.t1.setText(String.valueOf(t.pos));
if(t.team.equals("Arsenal FC")){
    convertView.setBackgroundColor(Color.RED);
}else{
convertView.setBackgroundColor(Color.LTGRAY);
}

        if (t.team.equals("Manchester City FC")) {
                v.t2.setText("Man City");
            } else if (t.team.equals("Manchester United FC")) {
                v.t2.setText("Man Utd");
            } else if(t.team.length()>=12) {
                String modified = t.team.substring(0, 11);
                v.t2.setText(modified + "...");
            }
        else {
            v.t2.setText(t.team);
        }
        v.t3.setText(String.valueOf(t.played));
        v.t4.setText(String.valueOf(t.won));
        v.t5.setText(String.valueOf(t.lost));
        v.t6.setText(String.valueOf(t.draw));
        v.t7.setText(String.valueOf(t.diff));
        v.t8.setText(String.valueOf(t.points));
        return convertView;
    }
}
