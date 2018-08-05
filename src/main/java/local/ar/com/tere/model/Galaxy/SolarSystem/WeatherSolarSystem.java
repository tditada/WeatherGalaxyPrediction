package local.ar.com.tere.model.Galaxy.SolarSystem;

import local.ar.com.tere.model.Galaxy.Planet.Planet;
import local.ar.com.tere.model.Geometry.Angle;
import local.ar.com.tere.model.Geometry.Triangle2D;
import local.ar.com.tere.model.Weather.WeatherType;

import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.Map;

public class WeatherSolarSystem  extends SolarSystem {

    private double maxPerimeter = 0;
    private int maxRainDay = 0;

    public WeatherSolarSystem(String name, List<Planet> planets) {
        super(name, planets, new Point2D.Double(0,0));
    }

    public int getMaxRainDay() {
        return maxRainDay;
    }

    /**
     * Checks if the planets collinear between them and the sun
     * @param angles List of {@link Angle} in degrees of the system in specific day
     * @return true if planets are align with sun, false otherwise
     */
    private boolean arePlanetsAlignWithSun(List<Angle> angles) {
        for (int i = 0; i < angles.size() - 1; i++) {
            if (!angles.get(i).isAlign(angles.get(i+1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if planets coordinates are collinear in specific system status.
     *
     * As days are discrete and planets are points in space, this method generates
     * a line {@link Line2D} between the planet closest to the sun and the furthest
     * one for actual day and next day.
     * If the middle planet change side from this lines, then planets were align
     * See {@link Line2D#relativeCCW(Point2D)}.
     *
     * @param coordinatesToday Planets position for first day
     * @param coordinatesTomorrow Planets position for next day
     * @return true if planets are align without sun, false otherwise
     */
    private boolean arePlanetsAlignWithoutSun(List<Point2D> coordinatesToday, List<Point2D> coordinatesTomorrow) {
        Line2D today = new Line2D.Double(coordinatesToday.get(0), coordinatesToday.get(2));
        int signToday = today.relativeCCW(coordinatesToday.get(1));

        Line2D tomorrow = new Line2D.Double(coordinatesTomorrow.get(0), coordinatesTomorrow.get(2));
        int signTomorrow = tomorrow.relativeCCW(coordinatesToday.get(1));

        return signTomorrow == -1 * signToday;
    }

    /**
     * Get triangle formed by the 3 planets coordinates on a specific day
     * @param day number of days since start day
     * @return triangle formed. See {@link Triangle2D}
     */
    private Triangle2D getTriangle(int day) {
        return  new Triangle2D(getCoordinates(day));
    }

    /**
     * Checks if triangle formed by the planets coordinates has the sun inside.
     * Check @Triangle2D for specific method.
     *
     * @param triangle {@link Triangle2D} formed by the planets in the specific day
     * @return true if sun is in the middle of the triangle, false otherwise
     */
    private boolean arePlanetsInATringleWithSun(Triangle2D triangle) {
        return triangle.isPointInTriangle(getSunPosition());
    }

    /**
     * Return WeatherType for a specific day
     *
     * @param day number of day from start day
     * @return WeatherType for the day
     */
    public WeatherType getDayWeather(int day) {
        List<Angle> angles = getAngles(day);
        if (arePlanetsAlignWithSun(angles)) {
            return WeatherType.DROUGHT;
        }

        List<Point2D> coordinates = getCoordinates(day);
        List<Point2D> coordinatesTomorrow = getCoordinates(day +1);
        if (arePlanetsAlignWithoutSun(coordinates, coordinatesTomorrow)) {
            return WeatherType.OPTIMAL;
        }

        Triangle2D triangle = getTriangle(day);
        if (arePlanetsInATringleWithSun(triangle)) {
            double perimeter = triangle.getTrianglePerimeter();
            if (perimeter > maxPerimeter) {
                maxPerimeter = perimeter;
                maxRainDay = day;
            }
            return WeatherType.RAIN;
        }

        return WeatherType.NORMAL;
    }

    /**
     * Predicts to weather from initialDay to finalDay (without finalDay) for the system.
     *
     * @param initialDay number of day after start day when we want to start prediction
     * @param finalDay number of day after start day when we want to stop prediction
     * @return Map with: number of days for each WeatherType
     */
    public Map<WeatherType, Integer> getWeatherPrediction(int initialDay, int finalDay) {
        Map<WeatherType, Integer> countPredictions = new HashMap<>();

        for (int i = initialDay; i < finalDay; i++) {
            WeatherType today = getDayWeather(i);
            Integer count =  countPredictions.getOrDefault(today, 0);
            countPredictions.put(today, count + 1);
        }

        return countPredictions;
    }


}
