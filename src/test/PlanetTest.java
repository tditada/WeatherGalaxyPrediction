package test;

import model.Galaxy.Planet.Planet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanetTest {
    //TODO: getAngularPosition
    //TODO: getXY

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void generat360DaysInYearFerengui() {
        Planet Ferengui = new Planet("Ferengui", 1, 500);
        assertEquals((int)Ferengui.getDaysInYear(), 360);
    }

    @Test
    public void generat120DaysInYearBetasoide() {
        Planet Betasoide = new Planet("Betasoide", 3, 2000);
        assertEquals((int)Betasoide.getDaysInYear(), 120);
    }

    @Test
    public void generat72DaysInYearVulcano() {
        Planet Vulcano = new Planet("Vulcano", -5, 1000);
        assertEquals((int)Vulcano.getDaysInYear(), 72);
    }
}