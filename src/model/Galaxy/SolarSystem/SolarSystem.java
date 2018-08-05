package model.Galaxy.SolarSystem;

import model.Galaxy.Planet.Planet;
import model.Geometry.Angle;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolarSystem {

    private List<Planet> planets;
    private String name;
    private Point2D sunPosition;

    SolarSystem(String name, List<Planet> planets, Point2D sunPosition) {
        this.name = name;
        this.planets = planets;
        Collections.sort(this.planets);
        this.sunPosition = sunPosition;
    }

    List<Planet> getPlanets() {
        return planets;
    }

    Point2D getSunPosition() {
        return sunPosition;
    }

    List<Angle> getAngles (int day) {
        List<Angle> angles = new ArrayList<>();
        for (Planet planet : planets) {
            angles.add(planet.getAngularPosition(day));
        }
        return angles;
    }

    List<Point2D> getCoordinates (int day) {
        List<Point2D> coordinates = new ArrayList<>();
        for (Planet planet : planets) {
            coordinates.add(planet.getXYCoordinates(day));
        }
        return coordinates;
    }


}
