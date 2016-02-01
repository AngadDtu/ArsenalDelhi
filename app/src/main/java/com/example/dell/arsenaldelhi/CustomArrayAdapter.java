package com.example.dell.arsenaldelhi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 02-01-2016.
 */
public class CustomArrayAdapter extends ArrayAdapter<String>{
    Context context;
    String[] title;
    int [] images;
    public boolean isScrolling=true;

    public CustomArrayAdapter(Context context,String[] title,int[] images) {
        super(context,0,title);
        this.context=context;
        this.title=title;
        this.images=images;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    public static class MyViewHolder{
    ImageView iv;
    TextView tv;
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MyViewHolder vh=new MyViewHolder();
      if(convertView==null) {

      if (getItemViewType(position) == 0) {
        convertView = View.inflate(context, R.layout.home_cardview_first, null);


    } else {
        convertView = View.inflate(context, R.layout.home_cardview_second, null);

    }

           vh.iv = (ImageView) convertView.findViewById(R.id.icon);
          vh.tv = (TextView) convertView.findViewById(R.id.Itemname);
    convertView.setTag(vh);
}
        String s=getItem(position);
        int resID=images[position];
        MyViewHolder VH=(MyViewHolder)convertView.getTag();
      VH.iv.setImageResource(resID);
        VH.tv.setText(s);
        return convertView;
    }
}
