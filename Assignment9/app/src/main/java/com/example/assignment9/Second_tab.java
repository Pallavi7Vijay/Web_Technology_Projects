package com.example.assignment9;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;


public class
Second_tab extends AppCompatActivity {
    TabLayout tb;
    ViewPager viewPager;
    String url;
    String url1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tb = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        tb.addTab(tb.newTab().setText("Business Details"));
        tb.addTab(tb.newTab().setText("Map Location"));
        tb.addTab(tb.newTab().setText("Reviews"));
        tb.setTabGravity(TabLayout.GRAVITY_FILL);
        String d = getIntent().getStringExtra("data");

        try {

            JSONObject response1 = new JSONObject(d);
            setTitle("name".length());
            URL url = new URL("http://www.facebook.com/share.php?u=");
            String u=url+response1.getString("url");
            URL url1 = new URL("https://twitter.com/intent/tweet?text=Check%20");
            String u2=url1+"%20on%20Yelp.%0A"+ response1.getString("url");

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String rub = getIntent().getStringExtra("reviews_data");


        try {
            JSONObject reviews_response = new JSONObject(rub);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final Myad a = new Myad(this, getSupportFragmentManager(),
                tabLayout.getTabCount()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return null;
            }
        };
        a.putExtra("data", d, rub,"name");



        viewPager.setAdapter(a);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void back() {

        Intent intent = new Intent(Second_tab.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT| Intent.FLAG_DEBUG_LOG_RESOLUTION
                | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(intent);

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem it)
    {
        switch(it.getItemId()) {
        case R.id.add:

            Intent add = null;
            Intent bi = new Intent(String.valueOf(getApplicationContext()));

            startActivity(bi);
            return true;

        case R.id.reset:

            Intent bi1= new Intent(String.valueOf(getApplicationContext()));
            startActivity(bi1);
            return(true);

            case android.R.id.home:
                back();
                int resultOk = super.RESULT_OK;
                return true;

        }
        return(super.onOptionsItemSelected(it));
    }





}