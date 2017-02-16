package com.json.rahul.vapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rahul on 2/15/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Post> posts;



    public CustomAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }
    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {

        TextView name;
        ImageView imageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            imageView=new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200,200));
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
            imageView.setPadding(15,15,15,15);

            convertView = LayoutInflater.from(context).inflate(R.layout.single_row_grid, parent, false);
            // holder.iid = (TextView) convertView.findViewById(R.id.list_line);
            holder.name = (TextView) convertView.findViewById(R.id.txtgrid);
            holder.imageView=(ImageView) convertView.findViewById(R.id.img1);
            //
            //TV1 = (TextView) convertView.findViewById(R.id.grid_cat);

            convertView.setTag(holder);
//        return convertView;
        }else

        {
            holder = (ViewHolder) convertView.getTag();

            //return imageView;

        }
        holder.name.setText(posts.get(position).getName());
        holder.imageView.setImageResource(post[position]);



        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gridintent = new Intent(context,MainActivity2.class);
                gridintent.putExtra("listquotes",posts.get(position).getId());
                context.startActivity(gridintent);
            }
        });

        return convertView;
    }
    public Integer[] post={

            R.drawable.eclipse,R.drawable.inception,
            R.drawable.index,   R.drawable.manofsteel,
            R.drawable.neerja,R.drawable.roy,
            R.drawable.superman
    };



}
