package com.orugasumu.android.sproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
//This first window with the CSUDH logo
public class MainActivity extends AppCompatActivity {

    private ImageButton mRoomSelect;
    private ImageButton mTeddy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRoomSelect = (ImageButton) findViewById(R.id.roomSelect);
        mRoomSelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, RoomSelectActivity.class);
                startActivity(intent);
                //open new activity to select start and target
            }

        });

        mTeddy = (ImageButton) findViewById(R.id.freeTeddy);
        mTeddy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, BuildingNsmActivity.class);
                startActivity(intent);
                //open new activity to select floors
            }
        });

        getSupportActionBar().hide();                                                   //Hide the ActionBar

    }
}