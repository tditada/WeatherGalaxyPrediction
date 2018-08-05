package model.Galaxy.Planet;
import model.Geometry.Angle;

import java.awt.*;
import java.awt.geom.Point2D;

public class Planet implements Comparable<Planet>{
    private Integer angularVelocity;
    private Integer distanceSunInKm;
    private String name;
    private Integer yearDurationInDays;

    public Planet(String name, Integer angularVelocity, Integer distanceSunInKm) {
        this.angularVelocity = angularVelocity;
        this.distanceSunInKm = distanceSunInKm;
        this.name = name;
    }

    /**
     * Get angle in degrees for a specific day
     * @param day number of day from start day
     * @return Angle object
     */
    public Angle getAngularPosition(int day) {
        return new Angle(angularVelocity * day);
    }

    /**
     * Get XY Coordinates for a specific day with sin and cos
     * @param day number of day from start day
     * @return {@link Point2D} with X and Y coordinates of the day
     */
    public Point2D getXYCoordinates(int day) {
        double angle = 90 - getAngularPosition(day).getAngle();
        double y = Math.sin(Math.toRadians(angle)) * this.distanceSunInKm;
        double x =  Math.cos(Math.toRadians(angle)) * this.distanceSunInKm;
        return new Point2D.Double(x, y);
    }

    /**
     * Get number of days for the planet to be again in the start position
     * @return number of days in a year
     */
    public int getDaysInYear() {
        int day = 0;
        if (this.yearDurationInDays == null) {
            Angle angle = new Angle(day + 1);
            while (angle.getAngle() != 0) {
                day++;
                angle = getAngularPosition(day);
            }
            this.yearDurationInDays = day;
        }
        return yearDurationInDays;
    }

    /**
     * Get distance to sun in Km
     * @return distance to sun
     */
    public Integer getDistanceSunInKm() {
        return distanceSunInKm;
    }

    /**
     * Get the planet name
     * @return planet name
     */
    public String getName() { return name; }

    @Override
    public int compareTo(Planet planet) {
        return distanceSunInKm.compareTo(planet.getDistanceSunInKm());
    }
}
