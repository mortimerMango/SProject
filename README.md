# SProject <img src="https://github.com/devicons/devicon/blob/master/icons/android/android-plain-wordmark.svg" title="android" alt="android" width="40" height="40"/> <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="java" alt="java" width="40" height="40"/>
## Natural Science and Mathematics Building Path Finder
This project is an Android application that uses Djskrta's Algorithm with a weighted graph to find the shortest path in the Natural Science and Matehemics (NSM) building at CSUDH 
 
### PURPOSE
#### This project is to provide students a way to locate class rooms, offices, or bathrooms at any location in the NSM building on campaus. This application requires input from the user using a drop down menu to select the room, elevator, bathroom, or nearby passage way and another drop down menu for the target location. Once the the source and destination are chosen, the user is allowed to run the application to produce the shortest path using Djkstra's Algorithm.

### Challenges
#### This project was hindered by the on going corona-virus pandamic. For instance, this project requried real world data measurements to be of any use. The pandemic caused the whole campus to be closed. Buildings were closed and accessibility was limited. Access to the full building (NSM) was not allowed except for the 1st and 2nd floor. Data was only gather on those floors. The method of measurement was paces between rooms using left or right manuevers around the building, steps taken on the stairs between floors, seconds for ascending and descending times via elevator. The paces, stair count, and evelvator seconds were incorporated into whole numbers for weights in a matrix file.
 
 ## Table of Contents
 * [MainActivity](#mainActivity)
 * [BuildingNsmActivity](#BuildingNsmActivity)
 * [PathNsmActivity](#PathNsmActivity)
 * [RoomSelectActivity](#RoomSelectActivity)
 * [DJK](#DJK)
 * [LinkedList](#LinkedList)
 * [Node](#Node)
 * [activity_building_nsm](#activity_building_nsm)
 * [activity_main](#activity_main)
 * [activity_path_nsm](#activity_path_nsm)
 * [activity_room_select](#activity_room_select)
 * [level_1](#level_1)
 * [level_2](#level_2)
 

#### Java File Location: SProject/app/src/main/java/com/orugasumu/android/sproject/
### MainActivity
```
This is the file that initiates the first window.
- Uses the acitivity_main.xml LAYOUT
```

### BuildingNsmActivity
```
This class initiates the mascot 
window when pressing on the "Meet Teddy"
button on the main window.
- Uses the activity_building_nsm.xml LAYOUT
```

### PathNsmActivity
```
This class is initiated when the user presses the
lower button in Room Selection screen. PathNsmActivity
builds the map outlining the shortest path using 
Djkstra's Algorithm. This reads 2 weighted matrix files labeled
level_1.txt representing the first floor, and level_2.txt representing 
the second floor. Maxtrix is fed to the DJK file. This class
also resets the process if the users pressed back
on their mobile device to prevent errors with the algorithm.
- Uses the activity_path_nsm.xml LAYOUT
```

### RoomSelectActivity
```
This class is run after pressing the "Room Selection"
button. This is responsible for providing the logic
in choosing a destination and source. This echos the users selections, 
and provides Spinner classes for user input.The current location
of the person is the Start Icon, and the Flag icon is the 
Source. Users cannot run Djkstra's Algorithm without
providing full input. When the user's input has been
verified. The lower screen button is accessible and allowed
to run Djkstra's Algorithm.
- This uses the activity_room_select.xml LAYOUT
```

### DJK
```
This uses Djkstra's Algorithm to find the shortest 
path in the Natural Sciences and Mathematics building.
The algorithm is fed an adjancey list from RoomSelectActivity.java
file, and composes the shortes path. The Algortithm is modfied
to account for 2 shortest paths if the locations are on 
different floors.
```

### LinkedList
```
This class defines the Linked List to be used 
for Digskra's Algorithm
```

### Node
```
This class defines the node structure 
for the Linked List. 
```
#### XML File Location: SProject/app/src/main/res/layout/
### activity_building_nsm
```
Layout to style the "Meet Teddy" page
```

### activity_main
```
Layout to style the home screen
```

### activity_path_nsm
```
Layout to draw map on screen for the
shortest path
```

### activity_room_select
```
Layout to style to the Room selection process
```

#### File Location: SProject/app/src/main/res/raw/
### level_1
```
first floor of Natural Science and Mathematics building 
matrix
```

### level_2
```
Second floor of Natural Science and Mathematics building 
matrix
```
