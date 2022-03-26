package com.orugasumu.android.sproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PathNsmActivity extends AppCompatActivity {

    private String start;                                                       //String to hold our CHOICE from Room Selection (START)
    private String target;                                                      //String to hold our CHOICE from Room Selection (TARGET)
    private Button mLvl_1r[];                                                   //Button Array to hold our resource IDs to mark our PATH Level 1
    private Button mLvl_2r[];                                                   //Button Array to hold our resource IDs to mark our PATH PLUS Level 2

    private ArrayList<Integer> path             = new ArrayList<>();            //Array to hold path from DIJKSTRA'S ALGORITHM Level 1
    private ArrayList<Integer> pathPlus         = new ArrayList<>();            //Array to hold path from DIJKSTRA'S ALGORITHM Level 2
    private ArrayList<LinkedList> adjList       = new ArrayList<>();            //Array List to hold adjacency list from LEVEL 1
    private ArrayList<LinkedList> adjListPlus   = new ArrayList<>();            //Array List to hold adjacency list from LEVEL 2
    private ArrayList<List<String>> temp        = new ArrayList<>();            //Temporary Array List to hold data from Matrix Level 1 file
    private ArrayList<List<String>> tmp         = new ArrayList<>();            //Temporary Array List to hold data from Matrix Level 2 file
    private String level[];                                                     //this holds the order of level 0 = level 1, 1 = level 2
    private StringBuffer buffer                 = new StringBuffer();           //Buffer to hold data from matrix for manipulation
    private DJK djk;                                                            //Our Dijkstra's Object that will be used to run algorithm

    private String lvl_1[] = {                                                  //Array to hold all the rooms on LEVEL 1, this is referenced to find actual room index on Matrix
            "D-1", "B-1", "C-1",
            "B-102", "B-104", "B-106", "B-110", "B-110", "B-122", "B-122", "B-132", "B-134", "B-136",
            "B-138", "B-140", "B-144", "B-148", "B-154", "F-121", "F-123", "F-127", "F-129", "F-133",
            "C-151", "C-151", "C-139", "C-139", "C-131", "C-121", "C-121", "C-109", "C-109", "C-105",
            "C-101", "restroom-W-1", "restroom-M-1", "Elevator-1"
    };

    private String lvl_2[] = {                                                   //Array to hold all the rooms on LEVEL 2, this is referenced to find actual room index on Matrix
            "D-2", "B-2", "F-2", "C-2", "D-225",
            "B-202", "B-204", "B-206", "B-208", "B-210", "B-212", "B-216", "B-218",
            "B-220", "B-224", "B-234", "B-234", "B-242", "B-242", "B-252", "B-252",
            "C-251", "C-251", "C-243", "C-239", "C-239", "C-235", "C-235", "C-221",
            "C-221", "C-217", "C-213", "C-213", "C-209", "C-207", "C-205", "C-203",
            "C-201", "D-231", "D-229", "restroom-W-2", "restroom-M-2", "Elevator-2"
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            //this launches the application for PATH FINDER
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_nsm);

        //Resources IDs for all buttons on Vector image for Level 1
        mLvl_1r = new Button[]{
                (Button) findViewById(R.id.d1),   (Button) findViewById(R.id.b1), (Button) findViewById(R.id.c1), (Button) findViewById(R.id.b102),
                (Button) findViewById(R.id.b104), (Button) findViewById(R.id.b106), (Button) findViewById(R.id.b110i), (Button) findViewById(R.id.b110),
                (Button) findViewById(R.id.b122i), (Button) findViewById(R.id.b122), (Button) findViewById(R.id.b132), (Button) findViewById(R.id.b134),
                (Button) findViewById(R.id.b136), (Button) findViewById(R.id.b138), (Button) findViewById(R.id.b140), (Button) findViewById(R.id.b144),
                (Button) findViewById(R.id.b148), (Button) findViewById(R.id.b154), (Button) findViewById(R.id.f121), (Button) findViewById(R.id.f123),
                (Button) findViewById(R.id.f127), (Button) findViewById(R.id.f129), (Button) findViewById(R.id.f133), (Button) findViewById(R.id.c151i),
                (Button) findViewById(R.id.c151), (Button) findViewById(R.id.c139i), (Button) findViewById(R.id.c139), (Button) findViewById(R.id.c131),
                (Button) findViewById(R.id.c121i), (Button) findViewById(R.id.c121), (Button) findViewById(R.id.c109i), (Button) findViewById(R.id.c109),
                (Button) findViewById(R.id.c105), (Button) findViewById(R.id.c101), (Button) findViewById(R.id.rrW1), (Button) findViewById(R.id.rrM1),
                (Button) findViewById(R.id.elevator1)
        };

        //Resources IDs for all buttons on Vector image for Level 2
        mLvl_2r = new Button[]{
                (Button) findViewById(R.id.d2), (Button) findViewById(R.id.b2), (Button) findViewById(R.id.f2), (Button) findViewById(R.id.c2),
                (Button) findViewById(R.id.d225), (Button) findViewById(R.id.b202), (Button) findViewById(R.id.b204), (Button) findViewById(R.id.b206),
                (Button) findViewById(R.id.b208), (Button) findViewById(R.id.b210), (Button) findViewById(R.id.b212), (Button) findViewById(R.id.b216),
                (Button) findViewById(R.id.b218), (Button) findViewById(R.id.b220), (Button) findViewById(R.id.b224), (Button) findViewById(R.id.b234),
                (Button) findViewById(R.id.b234i), (Button) findViewById(R.id.b242), (Button) findViewById(R.id.b242i), (Button) findViewById(R.id.b252),

                (Button) findViewById(R.id.b252i), (Button) findViewById(R.id.c251), (Button) findViewById(R.id.c251i), (Button) findViewById(R.id.c243),
                (Button) findViewById(R.id.c239), (Button) findViewById(R.id.c239i), (Button) findViewById(R.id.c235), (Button) findViewById(R.id.c235i),
                (Button) findViewById(R.id.c221), (Button) findViewById(R.id.c221i), (Button) findViewById(R.id.c217), (Button) findViewById(R.id.c213),
                (Button) findViewById(R.id.c213i), (Button) findViewById(R.id.c209), (Button) findViewById(R.id.c207), (Button) findViewById(R.id.c205),
                (Button) findViewById(R.id.c203), (Button) findViewById(R.id.c201), (Button) findViewById(R.id.d231), (Button) findViewById(R.id.d229),
                (Button) findViewById(R.id.rrW2), (Button) findViewById(R.id.rrM2), (Button) findViewById(R.id.elevator2)
        };

        TextView textViewS = (TextView) findViewById(R.id.start_rm);                //Initialize Text View for modification (START)
        TextView textViewT = (TextView) findViewById(R.id.target_rm);               //Initialize Text View for modification (TARGET)

        Intent intent = getIntent();                                                //Get information from RoomSelectActivity Intent
        start = intent.getStringExtra("start");                               //Get the choice from RoomSelectActivity as a String (START)
        target = intent.getStringExtra("target");                             //Get the choice from RoomSelectActivity as a String (TARGET)

        textViewS.setText(start);                                                   //Change the TextView "Text" to display actual Room Start
        textViewT.setText(target);                                                  //Change the TextView "Text" to display actual Room Target

        int sIndex = 0;                                                             //Index to Start
        int tIndex = 0;                                                             //Index to Target
        for(int i = 0; i < lvl_1.length; i++) {                                     //loop through to get Start Index or Target Index for LEVEL 1
            if(start.equals(lvl_1[i]))                                              //Start is level 1
                sIndex = i;
            if(target.equals(lvl_1[i]))                                             //Target is level 1
                tIndex = i;
        }
        for(int i = 0; i < lvl_2.length; i++) {                                     //loop through to get Start Index or Target Index for LEVEL 2
            if(start.equals(lvl_2[i]))
                sIndex = i;
            if(target.equals(lvl_2[i]))
                tIndex = i;
        }

        level = new String[2];                                                              //there are only 2 levels available
        level[0] = start.replaceAll("[^0-9]" , "");                       //remove all letters and hyphens to get room numbers START/TARGET
        level[1] = target.replaceAll("[^0-9]" , "");

        InputStream is1 = this.getResources().openRawResource((R.raw.level_1));             //get file for LEVEL 1 matrix
        InputStream is2 = this.getResources().openRawResource(R.raw.level_2);               //get file for LEVEL 2 matrix

        Scanner read1 = new Scanner(new InputStreamReader(is1));                            //read matrix for LEVEL 1
        while(read1.hasNext()) {                                                            //pull maxtrix data
            buffer.append(read1.nextLine());
            temp.add(new ArrayList<>(Arrays.asList(buffer.toString().split("[\\s]+"))));
            buffer.delete(0, buffer.length());
        }

        Scanner read2 = new Scanner(new InputStreamReader(is2));                             //read matrix for LEVEL 2
        while(read2.hasNext()) {                                                             //pull matrix data
            buffer.append(read2.nextLine());
            tmp.add(new ArrayList<>(Arrays.asList(buffer.toString().split("[\\s]+"))));
            buffer.delete(0, buffer.length());
        }

        read1.close();
        read2.close();

        for(int i = 0; i < temp.size(); i++) {										        //CREATE ADJACENCY LIST LEVEL 1
            adjList.add(new LinkedList());											        //initialize adjacency list
            for(int k = 0; k < temp.get(i).size(); k++) {
                if(Integer.parseInt(String.valueOf(temp.get(i).get(k))) != 0)
                    adjList.get(i).pushBack(k, Integer.parseInt(temp.get(i).get(k)));

            }//END for loop INNER
        }//END for loop OUTER

        for(int i = 0; i < tmp.size(); i++) {										        //CREATE ADJACENCY LIST LEVEL 2
            adjListPlus.add(new LinkedList());											    //initialize adjacency list
            for(int k = 0; k < tmp.get(i).size(); k++) {
                if(Integer.parseInt(String.valueOf(tmp.get(i).get(k))) != 0)
                    adjListPlus.get(i).pushBack(k, Integer.parseInt(tmp.get(i).get(k)));

            }//END for loop INNER
        }//END for loop OUTER

        //adjList = level 1, adjListPLus = level 2
        if(level[0].startsWith("1")) {											            //start is on level 1
            if(level[1].startsWith("2")) {                                                  //target is on level 2

                djk = new DJK(adjList, sIndex, -1);									        //tell constructor Start doesn't have a target at Level 1
                path.addAll(djk.getShortestPath());								            //store the path with the new target (Stairs or Elevator)

                if(path.get(path.size()-1) == 0) {									        //Last index of PATH ended at D-1(stairs)
                    djk = new DJK(adjListPlus, 0, tIndex);								    //create a new instance to get the modified START D-2(index 0) with ACTUAL TARGET on LEVEL 2
                    pathPlus.addAll(djk.getShortestPath());							        //store the path from D-2(stairs) to ACTUAL TARGET
                }
                else {
                    djk = new DJK(adjListPlus, 42, tIndex);								    //else it ended at the elevator at level 1, 42 is elevator at level 2
                    pathPlus.addAll(djk.getShortestPath());                                 //store path from Elevator 2 to ACTUAL TARGET
                }
            }
            else {																	        //START and TARGET are on same level 1
                djk = new DJK(adjList, sIndex, tIndex);
                path.addAll(djk.getShortestPath());
            }
        }
        else {																		        //start is on level 2
            if(level[1].startsWith("1")) {                                                  //target is on Level 1
                //LEFT OFF RIGHT HERE!!!!
                djk = new DJK(adjListPlus, sIndex, -2);							    //tell constructor Start doesn't have the target on level 2
                pathPlus.addAll(djk.getShortestPath());                                         //store the path with the new target (Stairs or Elevator)

                if(pathPlus.get(pathPlus.size()-1) == 0) {									        //Last index of PATH ended at D-2(stairs) Level 2

                    djk = new DJK(adjList, 0, tIndex);								        //create a new instance to get the modified start D-1 with ACTUAL TARGET on LEVEL 1
                    path.addAll(djk.getShortestPath());							        //store the path from D-1(stairs) to ACTUAL TARGET
                }
                else {

                    djk = new DJK(adjList, 36, tIndex);								        //else it ended at the elevator on level 2, and 36 is the elevator at level 1
                    path.addAll(djk.getShortestPath());                                 //store the patch from elevator 2 to ACTUAL TARGET
                }
            }
            else {																	        //START and TARGET are on same level 2
                djk = new DJK(adjListPlus, sIndex, tIndex);
                pathPlus.addAll(djk.getShortestPath());
            }
        }

        //Check the results of the PATHS
        if(!path.isEmpty() && !pathPlus.isEmpty()){                                         //If both path were used display both paths
            if(level[0].startsWith("1")){                                                   //Start was at Level 1
                //lets label the start
                mLvl_1r[path.get(0)].setForeground(getDrawable(R.drawable.round));
                mLvl_1r[path.get(0)].setScaleX(1.6f);
                mLvl_1r[path.get(0)].setScaleY(0.7f);

                for(int i = 1; i < path.size(); i++){                                       //Display Level 1 path
                    mLvl_1r[path.get(i)].setBackgroundColor(Color.GREEN);
                }

                for(int i = 0; i < pathPlus.size()-1; i++){                                   //Then display Level 2 path
                    mLvl_2r[pathPlus.get(i)].setBackgroundColor(Color.GREEN);
                }
                mLvl_2r[pathPlus.get(pathPlus.size()-1)].setForeground(getDrawable(R.drawable.roundtarget));    //lets label TARGET
                mLvl_2r[pathPlus.get(pathPlus.size()-1)].setScaleX(1.6f);
                mLvl_2r[pathPlus.get(pathPlus.size()-1)].setScaleY(0.7f);
            }
            else{                                                                           //ELSE, Start was at Level 2
                //lets label the start
                mLvl_2r[pathPlus.get(0)].setForeground(getDrawable(R.drawable.round));          //change shape of Start
                mLvl_2r[pathPlus.get(0)].setScaleX(1.6f);
                mLvl_2r[pathPlus.get(0)].setScaleY(0.7f);//lets label Start

                for(int i = 1; i < pathPlus.size(); i++){                                   //Display Level 2 Path
                    mLvl_2r[pathPlus.get(i)].setBackgroundColor(Color.GREEN);
                }
                                                                    //Display Level 1 Path
                for(int i = 0; i < path.size()-1; i++){
                    mLvl_1r[path.get(i)].setBackgroundColor(Color.GREEN);
                }

                mLvl_1r[path.get(path.size()-1)].setForeground(getDrawable(R.drawable.roundtarget));    //lets label TARGET
                mLvl_1r[path.get(path.size()-1)].setScaleX(1.6f);
                mLvl_1r[path.get(path.size()-1)].setScaleY(0.7f);
            }
        }
        else if(path.isEmpty()){                                                            //Start AND Target were not in LEVEL 1 then display Level 2 PATH

            mLvl_2r[pathPlus.get(0)].setForeground(getDrawable(R.drawable.round));          //change shape of Start
            mLvl_2r[pathPlus.get(0)].setScaleX(1.6f);
            mLvl_2r[pathPlus.get(0)].setScaleY(0.7f);
            for(int i = 1; i < pathPlus.size()-1; i++) {                                       //Color path Green
                mLvl_2r[pathPlus.get(i)].setBackgroundColor(Color.GREEN);
                //mLvl_2r[pathPlus.get(i)].setScaleX(1.5f);
                //mLvl_2r[pathPlus.get(i)].setScaleY(0.6f);
            }
            mLvl_2r[pathPlus.get(pathPlus.size()-1)].setForeground(getDrawable(R.drawable.roundtarget));
            mLvl_2r[pathPlus.get(pathPlus.size()-1)].setScaleX(1.6f);
            mLvl_2r[pathPlus.get(pathPlus.size()-1)].setScaleY(0.7f);
        }
        else{                                                                               //Start AND Target were not in Level 2, then display level 1 Path
            mLvl_1r[path.get(0)].setForeground(getDrawable(R.drawable.round));
            mLvl_1r[path.get(0)].setScaleX(1.6f);
            mLvl_1r[path.get(0)].setScaleY(0.7f);

            for(int i = 0; i < path.size()-1; i++){
                mLvl_1r[path.get(i)].setBackgroundColor(Color.GREEN);
            }

            mLvl_1r[path.get(path.size()-1)].setForeground(getDrawable(R.drawable.roundtarget));    //lets label TARGET
            mLvl_1r[path.get(path.size()-1)].setScaleX(1.6f);
            mLvl_1r[path.get(path.size()-1)].setScaleY(0.7f);
        }

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.csudhRed)));  //change color of Action Bar

    }//END onCREATE()

    @Override
    public void onBackPressed() {                                                                   //This restarts RoomSelectActivity when clicking back ADDS TO STACK!!
        startActivity(new Intent(PathNsmActivity.this, RoomSelectActivity.class));
    }

}