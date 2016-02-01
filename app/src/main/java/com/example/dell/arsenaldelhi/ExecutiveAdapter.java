package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 05-01-2016.
 */
public class ExecutiveAdapter extends ArrayAdapter<members> {
    Context context;
    ArrayList<members>data;
    public ExecutiveAdapter(Context context, ArrayList<members> data) {
        super(context,0,data);
        this.data=data;
        this.context=context;
    }
    public static class ViewHolder{
        ImageView iv;
        TextView tvleft;
        TextView tv;
        TextView tvright;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.members_list,null);
            ViewHolder vh=new ViewHolder();
            vh.iv=(ImageView)convertView.findViewById(R.id.icon);
            vh.tvleft=(TextView)convertView.findViewById(R.id.Itemname);
            vh.tv=(TextView)convertView.findViewById(R.id.add);
            vh.tvright=(TextView)convertView.findViewById(R.id.desig);
            convertView.setTag(vh);
        }
        members m=getItem(position);
        ViewHolder VH=(ViewHolder)convertView.getTag();
        VH.iv.setImageResource(m.image);
        VH.tvleft.setText(m.name);
        VH.tv.setText(m.address);
        VH.tvright.setText(m.designation);
        return convertView;
    }
}
