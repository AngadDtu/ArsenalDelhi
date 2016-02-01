package com.example.dell.arsenaldelhi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by DELL on 30-12-2015.
 */
public class drawerAdapter extends RecyclerView.Adapter<drawerAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<information> data = Collections.emptyList();
    Context context;


    public drawerAdapter(Context context, List<information> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.IconId);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.texticon);
            icon = (ImageView) itemView.findViewById(R.id.imageicon);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position == 0) {
                Toast.makeText(context, "You are at Home", Toast.LENGTH_SHORT).show();
            }
            if (position == 1) {
                context.startActivity(new Intent(context, Event.class));
            }
            if (position == 2) {
                context.startActivity(new Intent(context, Merchandises.class));
            }
            if (position == 3) {
                context.startActivity(new Intent(context, News.class));
            }
            if (position == 4) {
                context.startActivity(new Intent(context, Contact.class));
            }
            if (position == 5) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("fb://page/262452267292513"));
                   context.startActivity(i);
                }
                catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.facebook.com/arsenaldelhi/"));
                    Intent chooser = Intent.createChooser(i, "choose");

                    context.startActivity(chooser);
                }
            }
            if (position == 6) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("twitter://user?screen_name=arsenaldelhi"));
                    context.startActivity(i);
                }
                catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/arsenaldelhi"));
                    Intent chooser = Intent.createChooser(i, "choose");

                    context.startActivity(chooser);
                }

            }
            if (position == 7) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:angad.dtu2012@gmail.com"));
                i.putExtra(Intent.EXTRA_SUBJECT, "subject");
                Intent chooser = Intent.createChooser(i, "title");
                if (i.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(chooser);
                } else {

                }
            }
        }
    }
}

