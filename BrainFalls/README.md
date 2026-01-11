 
# Brain SHapes Game

A reflex-based desktop game developed using **Java Swing** and **AWT**. The primary objective is to catch falling geometric shapes before they disappear from the screen. This project demonstrates object-oriented programming principles, custom 2D rendering, and thread-safe collection handling in Java.

## Project Overview

* **Language:** Java
* **Libraries:** Java Swing, AWT
* **Type:**  Game
* **IDE:** NetBeans

## Features

* **Dynamic Rendering:** Random generation of squares, circles, triangles, pentagons, and hexagons.
* **High Score System:** Stores the maximum round reached during the session.
* **Memory Management:** Automatic removal of off-screen objects to prevent memory leaks.
* **Game Loop Architecture:** Managed via `javax.swing.Timer` for thread-safe UI updates.

## Technical Implementation

This project addresses specific challenges regarding concurrency and graphics rendering.

### 1. Concurrency and Collection Management
To prevent `ConcurrentModificationException` during the rendering loop, an explicit `Iterator` was implemented instead of a standard for-each loop. This ensures safe removal of objects from the `ArrayList` while iterating.

 

Iterator<Shape> iterator = shapes.iterator();
while (iterator.hasNext()) {
    Shape s = iterator.next();
    if (s.y > getHeight()) {
        iterator.remove();  
    }
}
2. Custom Geometry Rendering
Since java.awt.Graphics does not provide direct methods for regular pentagons or hexagons, a custom vertex calculation algorithm was developed.



 
double angle = Math.toRadians(-90 + (i * 360.0 / sides));
xPoints[i] = centerX + (int) (radius * Math.cos(angle));
yPoints[i] = centerY + (int) (radius * Math.sin(angle));


## Installation and Execution
 
Clone this repostory, open your IDE and import the folder as a Java project. Locate Main.java in the src folder and execute the file.

Future Improvements
Adaptive Difficulty: Implementing a system where game speed and spawn rates increase incrementally as the player progresses.

Mobile Porting: Adapting the game logic for Android devices.

Database Integration: Implementing database support for persistent global leaderboards.

Audio Feedback: Adding sound effects for user interactions.
Author:github.com/canbolathayrettintarik
