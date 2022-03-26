package com.orugasumu.android.sproject;

import java.util.ArrayList;
import java.util.HashSet;

public class DJK {

    private ArrayList<Integer> sPath = new ArrayList<>();	//Shortest Distance
    private int d[];										//current distances
    private int p[];										//previous vertex
    private HashSet<Integer> v_s;							//vertices being processed
    private int vertices = 0;								//amount of vertices from adjacency list
    private int tDistance = 0;                              //NEW!!!

    public DJK(ArrayList<LinkedList> adj, int start, int target) {

        vertices = adj.size();							//get amount of vertices
        d = new int[vertices];							//initialize DISTANCE ( d[v] )
        p = new int[vertices];							//initialize PREDESSOR ( p[v] )
        v_s = new HashSet<>(vertices);					//initialize v_s to amount of vertices

        for(int i = 0; i < vertices; i++) {				//add all vertices except for our start vertex
            if(i != start)
                v_s.add(i);
        }

        for(int v : v_s) {								//set all p[v] indexes to start index, and get weights of all vertices into d[v]
            p[v] = start;
            d[v] = adj.get(v).getWeight(start);
        }

        while(v_s.size() != 0) {
            int min = Integer.MAX_VALUE;				//we'll start referencing all weights to infinity
            int u = -1;									//vertex u in v_s
            for(int v : v_s) {							//compare each DISTANCE to the Minimum
                if(d[v] < min) {
                    min = d[v];
                    u = v;
                }
            }

            v_s.remove(u);								//u in v_s has been updated, now remove it

            for(int v : v_s) {							//relax distances
                if(adj.get(u).contains(v)) {
                    int weight = adj.get(u).getWeight(v);
                    if(d[u] + weight < d[v]) {
                        d[v] = d[u] + weight;
                        p[v] = u;
                    }
                }
            }

        }//END v_s.size() != 0 while loop

        if(target == -1) {                                              //THIS IS NEW!!!!!!!
            setShortestPath(start, 36);								//index 36 = elevator 1
            int tempDist = tDistance;									//temporary distance to figure out which path is shortest
            ArrayList<Integer> tempPath = new ArrayList<>(sPath);		//temporary array list to hold path to elevator
            sPath.clear();												//remove all entries made by function to get new PATH
            tDistance = 0; 												//reset the current distance for new DISTANCE
            setShortestPath(start, 0);								//index 0 = D-1(stairs)

            if(tempDist < tDistance) {									//send the path with elevator as TARGET
                sPath.clear();											//clear the current path
                sPath.addAll(0, tempPath);						//set the Path to using elements from temporary path Elevavor
            }															//else the current sPath is the good one (D-1)

        }
        else if(target == -2) {                                         //this means it came from a level 2
            //we must choose the new target to be the shortest path from the ELEVATOR || D-2
            setShortestPath(start, 42);								//index 42 = elevator-2, send elevator as target to function
            int tempDist = tDistance;									//temporary distance to figure out which path is shortest
            ArrayList<Integer> tempPath = new ArrayList<>(sPath);		//temporary array list to hold path to elevator
            sPath.clear();												//remove all entries made by function to get new PATH
            tDistance = 0; 												//reset the current distance for new DISTANCE
            setShortestPath(start, 0);								//index 0 = D-2(it contains stairs), send D-2 as target to function

            if(tempDist < tDistance) {									//send the path with elevator as TARGET
                sPath.clear();											//clear the current path
                sPath.addAll(0, tempPath);						//set the Path to using elements from temporary path
            }															//else the current sPath is the good one
        }
        else															//just do the regular function call
            setShortestPath(start, target);                             //lets get the path from START to TARGET

        //setShortestPath(start, target);                 //lets get the path from START to TARGET
    }//END DJK()

    private void setShortestPath(int s, int t) {
        if(s == t)
            sPath.add(s);
        else {
            setShortestPath(s, p[t]);
            tDistance += d[t];                          //add the distance of that target
            sPath.add(t);
        }
    }

    public ArrayList<Integer> getShortestPath(){
        return sPath;
    }
}
