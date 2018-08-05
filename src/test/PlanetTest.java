package test;

import model.Galaxy.Planet.Planet;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class PlanetTest {
    //TODO: getXY
    private Planet ferengui;
    private Planet betasoide;
    private Planet vulcano;

    @Before
    public void setUp() throws Exception {
        ferengui = new Planet("Ferengui", 1, 500);
        betasoide = new Planet("Betasoide", 3, 2000);
        vulcano = new Planet("Vulcano", -5, 1000);
    }

    @Test
    public void generate360DaysInYearWhenAngularVelocityIs1() {
        assertEquals(ferengui.getDaysInYear(), 360);
    }

    @Test
    public void generate120DaysInYearWhenAngularVelocityIs3() {
        assertEquals(betasoide.getDaysInYear(), 120);
    }

    @Test
    public void generate72DaysInYearWhenAngularVelocityIsNegative5() {
        assertEquals(vulcano.getDaysInYear(), 72);
    }

    @Test
    public void getAngularPositionDay90AngularVelocity1Is90(){
        assertEquals((int)ferengui.getAngularPosition(90).getAngle(), 90);
    }

    @Test
    public void getAngularPositionDay90AngularVelocity3Is270(){
        assertEquals((int)betasoide.getAngularPosition(90).getAngle(), 270);
    }

    @Test
    public void getAngularPositionDay10AngularVelocityNegative5Is310(){
        assertEquals((int)vulcano.getAngularPosition(10).getAngle(), 310);
    }

    @Test
    public void getXYCoordinateInStartDayShouldBe0AndDistanceToSun(){
        Point2D coordinate = ferengui.getXYCoordinates(0);
        assertEquals(coordinate.getX(), 0, 0.001);
        assertEquals(coordinate.getY(), ferengui.getDistanceSunInKm(), 0.001);
    }

    @Test
    public void getXYCoordinateInAYearShouldBe0AndDistanceToSun(){
        Point2D coordinate = vulcano.getXYCoordinates(vulcano.getDaysInYear());
        assertEquals(coordinate.getX(), 0, 0.001);
        assertEquals(coordinate.getY(), vulcano.getDistanceSunInKm(), 0.001);
    }

}