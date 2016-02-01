package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.dell.arsenaldelhi.R.drawable.afc;

/**
 * Created by DELL on 14-01-2016.
 */
public class FixtureAdapter  extends ArrayAdapter<fixture>{
    Context context;
    ArrayList<fixture>matches;
    public FixtureAdapter(Context context, ArrayList<fixture> matches) {
        super(context,0,matches);
        this.context=context;
        this.matches=matches;
    }
protected static class MyViewHolder{
    TextView ttv;
    TextView ltv;
    TextView rtv;
    TextView mtv;
    ImageView liv;
    ImageView riv;
    TextView vs;
    TextView t;
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.fixture_list_item,null);
            MyViewHolder vh=new MyViewHolder();
            vh.ttv=(TextView)convertView.findViewById(R.id.matchday);
            vh.ltv=(TextView)convertView.findViewById(R.id.homeText);
            vh.rtv=(TextView)convertView.findViewById(R.id.awayText);
            vh.mtv=(TextView)convertView.findViewById(R.id.date);
            vh.vs=(TextView)convertView.findViewById(R.id.versus);
            vh.t=(TextView)convertView.findViewById(R.id.time);
             convertView.setTag(vh);
        }
        fixture f=getItem(position);
        MyViewHolder v=(MyViewHolder)convertView.getTag();
        v.ttv.setText("Matchday "+String.valueOf(f.matchday));
        v.ltv.setText(f.homeTeam);
        v.rtv.setText(f.awayTeam);
        int len=f.date.length();
        String date = f.date.substring(0, f.date.indexOf("T"));
        String time  = f.date.substring(f.date.indexOf("T") + 1, len - 1);
        v.mtv.setText(date);
        v.t.setText(time);
        if(f.status.equals("FINISHED")){
            v.vs.setText(f.goalsHome + "-" + f.goalsAway);
        }
        if(f.status.equals("TIMED")){
            v.vs.setText("V");
        }
        return  convertView;
    }
}
