package test.java;

import model.Galaxy.Planet.Planet;
import model.Galaxy.SolarSystem.SolarSystem;
import model.Geometry.Angle;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SolarSystemTest {
    SolarSystem weatherAliens;

    @Before
    public void setUp() throws Exception {
        List<Planet> planets = new ArrayList<>();
        Planet Ferengui = new Planet("Ferengui", 1, 500);
        Planet Betasoide = new Planet("Betasoide", 3, 2000);
        Planet Vulcano = new Planet("Vulcano", -5, 1000);
        planets.add(Ferengui); planets.add(Betasoide); planets.add(Vulcano);

        this.weatherAliens = new SolarSystem("WeatherAliens", planets, new Point2D.Double(0,0));
    }

    @Test
    public void checkPlanetsInOrderFromClosestToTheSun() {
        assertEquals(weatherAliens.getPlanets().get(0).getName(), "Ferengui");
        assertEquals(weatherAliens.getPlanets().get(1).getName(), "Vulcano");
        assertEquals(weatherAliens.getPlanets().get(2).getName(), "Betasoide");
    }

    @Test
    public void getAnglesStartDayAll0() {
        List<Angle> angles = weatherAliens.getAngles(0);
        for (Angle angle: angles) {
            assertEquals((int)angle.getAngle(), 0);
        }
    }

    @Test
    public void getAnglesDay10PlanetAngularVelocity1() {
        List<Angle> angles = weatherAliens.getAngles(10);
        assertEquals((int)angles.get(0).getAngle(), 10);
    }

    @Test
    public void getAnglesDay10PlanetAngularVelocityNegative5() {
        List<Angle> angles = weatherAliens.getAngles(10);
        assertEquals((int)angles.get(1).getAngle(), 310);
    }

    @Test
    public void initialCoordinatesInXAreZero() {
        List<Point2D> coordinates = weatherAliens.getCoordinates(0);
        for (Point2D coordinate: coordinates) {
            assertEquals((int)coordinate.getX(), 0);
        }
    }


}
