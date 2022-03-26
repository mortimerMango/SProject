package com.orugasumu.android.sproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
//This is suppose to be the Teddy Window
public class BuildingNsmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_nsm);

        getSupportActionBar().hide();
    }

}