package com.json.rahul.vapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by rahul on 2/15/2017.
 */

public class Rquoast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);

        TextView txt = (TextView) findViewById(R.id.txtlist1);
        Intent intent = getIntent();
        final String quotes = intent.getStringExtra("quotePos");

        txt.setText(quotes);
        txt.setTextIsSelectable(true);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {

            openShareMenu();
        }
        else if(id == android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public void openShareMenu(){

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);//android.content.Intent.ACTION_SEND
        sharingIntent.setType("text/plain");
//        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "quotes");
//        String shareText = "this is the share text";
        Intent intent = getIntent();
        final String quotes = intent.getStringExtra("quotePos");

        sharingIntent.putExtra(Intent.EXTRA_TEXT, quotes);


        //display apps that support the intent
//        List<ResolveInfo> respondingApps = getPackageManager().queryIntentActivities(sharingIntent,0);
//        for(ResolveInfo ri : respondingApps){
//            ComponentName name = new ComponentName(ri.activityInfo.packageName,ri.activityInfo.name);
//            Log.v("TAG", "-->" + ri.loadLabel(getPackageManager()));
//        }

        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.share, menu);

