package com.json.rahul.vapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rahul on 2/15/2017.
 */

public class CustomAdapter2 extends BaseAdapter {
    Context listcontext;
    ArrayList<Qlist> qlist;

    public CustomAdapter2(Context listcontext, ArrayList<Qlist> qlist) {
        this.listcontext = listcontext;
        this.qlist = qlist;
    }

    @Override
    public int getCount() {
        return qlist.size();
    }

    @Override
    public Object getItem(int position) {
        return qlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewListHolder {
        TextView Listquotes;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewListHolder listholder = new ViewListHolder();
        if (convertView == null){

            convertView = LayoutInflater.from(listcontext).inflate(R.layout.single_row,parent,false);
            listholder.Listquotes = (TextView)convertView.findViewById(R.id.txtsinglerow);

            convertView.setTag(listholder);
        }else

        {
            listholder = (ViewListHolder)convertView.getTag();
        }
        listholder.Listquotes.setText(qlist.get(position).getQuotes());

        listholder.Listquotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(listcontext,Rquoast.class);
                myintent.putExtra("quotePos",qlist.get(position).getQuotes());
                listcontext.startActivity(myintent);
            }
        });

        return convertView;
    }


    }

