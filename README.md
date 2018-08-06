Weather Galaxy Forecast
==================

This is a project that infer the weather in a particular solar system for the next 10 years using some geometrical rules.

The "weather solar system" is composed by three planets and a sun.
In this project I am assuming the sun is always in the middle (0,0), days are discrete and planets are points in the space.

Rules:

1 - When planets are all align including the sun, then the weather is drought.

2 - When planets are all align without the sun, there are optimal pressure and temperature conditions.

3 - When planets are not align they form a triangle. If the sun is inside this triangle then it rains.
When the perimeter of this triangle is the max one, then that day they have the most rain.

## How rules are checked

1 - When all four are align the result of ANGLE % 180° is equal for all planets.

2 - There are a number of ways to check these (area of the triangle equals 0, with slopes). But as days are discrete and we want to know if planets were align in any moment of a particular day, we draw a line between the closest and farthest planet for today and another line for tomorrow. If the middle planet change sides from this line, then they were align.

3 - To calculate if the sun is in the middle of the triangle we use barometric coordinates. Basically checking if the sun position can be expressed with two of the vectors from the triangle sides.

## Build With

* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven](https://maven.apache.org/download.cgi) 
* [Google Cloud SDK](https://cloud.google.com/sdk/) 
* [Objectify](https://github.com/objectify/) 
* [Java Spark](http://sparkjava.com/) 

## API
App Engine API of the project with DataStore to save entities.

URL: https://weathergalaxyforecast.appspot.com/

Methods

  - GET /weather?day=[day]

Retrieves the weather of a specific number day. 


Example Response:

    {"weather": "drought", "day": "0"}
    

Example Error Response:

    {"error": "Parameter day is mandatory and needs to be a positive day number in the next 10 years"}
    


- GET /weatherforecast




