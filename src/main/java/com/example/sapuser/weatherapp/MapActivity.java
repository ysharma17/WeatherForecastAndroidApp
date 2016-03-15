package com.example.sapuser.weatherapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;


public class MapActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        try {

            Bundle bundle = getIntent().getExtras();
            String city = bundle.getString("city").toString();
            String respon  = bundle.getString("response");
            JSONObject response = new JSONObject(respon);
            String lat = response.getString("latitude");
            Log.d("latitude",lat);

            String lng = response.getString("longitude");
            Log.d("longitude",lng);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            MapFragment fragment = new MapFragment();

            Bundle bundle1 = new Bundle();
            bundle1.putString("lat", lat);
            bundle1.putString("lng", lng);

            fragment.setArguments(bundle1);

            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}