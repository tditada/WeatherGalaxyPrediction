package local.ar.com.tere.model.Galaxy.SolarSystem;

import local.ar.com.tere.model.Galaxy.Planet.Planet;
import local.ar.com.tere.model.Geometry.Angle;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolarSystem {

    private List<Planet> planets;
    private String name;
    private Point2D sunPosition;

    public SolarSystem(String name, List<Planet> planets, Point2D sunPosition) {
        this.name = name;
        this.planets = planets;
        Collections.sort(this.planets);
        this.sunPosition = sunPosition;
    }

    /**
     * Get the list of planets from this solar system
     * @return the list of planets {@link Planet}
     */
    public List<Planet> getPlanets() {
        return planets;
    }

    /**
     * Gets the sun coordinates in the solar system
     * @return {@link Point2D} with sun coordinates
     */
    Point2D getSunPosition() {
        return sunPosition;
    }

    /**
     * Get Angles for every planet in the solar system at a specific day
     * @param day number of days since start day
     * @return list of {@link Angle} with the degrees for every planet that day
     */
    public List<Angle> getAngles (int day) {
        List<Angle> angles = new ArrayList<>();
        for (Planet planet : planets) {
            angles.add(planet.getAngularPosition(day));
        }
        return angles;
    }

    /**
     * Get XY coordinates for every planet in the solar system at a specific day
     * @param day number of days since start day
     * @return list of {@link Point2D} with XY Coordinates for every planet that day
     */
    public List<Point2D> getCoordinates (int day) {
        List<Point2D> coordinates = new ArrayList<>();
        for (Planet planet : planets) {
            coordinates.add(planet.getXYCoordinates(day));
        }
        return coordinates;
    }

    /**
     * Get days in a year from the slowest planet in the system
     * @return days in a year for slowest planet
     */
    public int getMaxDaysInYear() {
        int maxDays = 0;
        for (Planet planet : planets) {
            if (planet.getDaysInYear() > maxDays) {
                maxDays = planet.getDaysInYear();
            }
        }
        return maxDays;
    }


}
