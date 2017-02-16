package com.json.rahul.vapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rahul on 2/15/2017.
 */

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        int quotePosition = getIntent().getIntExtra("listquotes", -1);

        new Listclass().execute("http://rapidans.esy.es/test/getquotes.php?cat_id=" + quotePosition);



    }

    class Listclass extends AsyncTask<String,Void,String> {

        private ProgressDialog Ldialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Ldialog = new ProgressDialog(MainActivity2.this);
            Ldialog.setMessage("Loading...");
            Ldialog.setCancelable(false);
            Ldialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);//new URL("http://rapidans.esy.es/test/getquotes.php?cat_id=1");
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (Ldialog.isShowing()) {
                Ldialog.dismiss();
            }
            ArrayList<Qlist> QArrayList = new ArrayList<>();
//            ArrayList<categories> cats = new ArrayList<>();
            try {
                JSONObject obj1 = new JSONObject(s);
                JSONArray jsonobj = obj1.getJSONArray("data");
                for (int i = 0; i < jsonobj.length(); i++) {
                    JSONObject obj2 = jsonobj.getJSONObject(i);

                    int id = obj2.getInt("id");
                    int catid = obj2.getInt("cat_id");
                    String qname = obj2.getString("quotes");

                    Qlist post = new Qlist();
                    post.setId(id);
                    post.setCat_id(catid);
                    post.setQuotes(qname);
                    QArrayList.add(post);
                }


            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            CustomAdapter2 listadapter = new CustomAdapter2(MainActivity2.this, QArrayList);
            ListView listview = (ListView) findViewById(R.id.lstview);
            listview.setAdapter(listadapter);

        }
    }

}
