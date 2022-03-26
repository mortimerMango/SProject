package com.orugasumu.android.sproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//Window displaying choices to makes then to start the path
public class RoomSelectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton mRunAlgorithm;                                  //Button that launches DIJKSTRA'S ALGORITHM
    private boolean isSpinner1Touched   = false;                        //flag to detect if user actually selected something
    private boolean isSpinner2Touched   = false;                        //flag to detect if user actually selected something
    private boolean isSpinner3Touched   = false;
    private String finalChoice[]        = new String[3];                //this holds the START, END selected

    private String locations[] = {                                      //Array to hold selections for SPINNER drop down menu
            "SELECT",
            "B-1", "B-2", "C-1", "C-2","D-1", "D-2",
            "Elevator-1", "Elevator-2",
            "restroom-M-1", "restroom-W-1", "restroom-M-2", "restroom-W-2",
            "B-102", "B-104", "B-106", "B-110", "B-122", "B-132", "B-134", "B-136",
            "B-138", "B-140", "B-144", "B-148", "B-154",
            "B-202", "B-204", "B-206", "B-208", "B-210", "B-212", "B-216", "B-218",
            "B-220", "B-224", "B-234", "B-242", "B-252",
            "C-101", "C-105", "C-109", "C-121", "C-131", "C-139", "C-151",
            "C-201", "C-203", "C-205", "C-207", "C-209", "C-213", "C-217", "C-221",
            "C-235", "C-239", "C-243", "C-251",
            "D-225", "D-229", "D-231",
            "F-121", "F-123", "F-127", "F-129", "F-133"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);

        Spinner spinner1 = (Spinner) findViewById(R.id.roomStart);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RoomSelectActivity.this, android.R.layout.simple_spinner_item, locations);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnTouchListener(new View.OnTouchListener() {                        //This checks to see if the person chose something on the Drop Down
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinner1Touched = true;
                return false;
            }
        });

        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.roomTarget);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RoomSelectActivity.this, android.R.layout.simple_spinner_item, locations);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnTouchListener(new View.OnTouchListener() {                        //check to see if anything was actually selected
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinner2Touched = true;
                return false;
            }
        });

        spinner2.setOnItemSelectedListener(this);

        mRunAlgorithm = (ImageButton) findViewById(R.id.runIcon);
        mRunAlgorithm.setOnClickListener(new View.OnClickListener(){                    //This makes sure that all Start and Target have been chosen before running
            @Override
            public void onClick(View v){
                //if(isSpinner1Touched && isSpinner2Touched && isSpinner3Touched) {
                if(isSpinner1Touched && isSpinner2Touched) {

                    Toast.makeText(RoomSelectActivity.this, "Launching APP ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RoomSelectActivity.this, PathNsmActivity.class);
                    Bundle bundle = new Bundle();                                       //added to pass selected parameters to new INTENT

                    bundle.putString("start", finalChoice[0]);                          //Gather choices for new INTENT PathNsmActivity
                    bundle.putString("target", finalChoice[1]);

                    intent.putExtras(bundle);
                    startActivity(intent);                                              //Start the new Activity PathNsmActivity

                }
                else
                    Toast.makeText(RoomSelectActivity.this, "Missing Selections!", Toast.LENGTH_SHORT).show();  //Some Choice was missing
            }
        });

        getSupportActionBar().hide();                                                   //Hide the action bar
    }//onCREATE() END


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){               //The the drop down was activated, store the choices

        if (!isSpinner1Touched && !isSpinner2Touched) {
            Toast.makeText(this, "No Items Selected ", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            switch (parent.getId()) {                                                               //Check what choice was made, and store it
                case R.id.roomStart:
                    Toast.makeText(this, "Start Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    finalChoice[0] = parent.getSelectedItem().toString();
                    break;
                case R.id.roomTarget:
                    Toast.makeText(this, "Target Selected: " + parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    finalChoice[1] = parent.getSelectedItem().toString();
                    break;

            }
        }

        ((TextView) parent.getChildAt(0)).setTextColor(Color.RED);
        ((TextView) parent.getChildAt(0)).setTextSize(20);

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){                             //Android requires this to make Spinner functional
        //do something
    }

    @Override
    public void onBackPressed() {                                                                   //This restarts MainActivity when clicking back, ADDS TO STACK!!
        startActivity(new Intent(RoomSelectActivity.this, MainActivity.class));
    }
}