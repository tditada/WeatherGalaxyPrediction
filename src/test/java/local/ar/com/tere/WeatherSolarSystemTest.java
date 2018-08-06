package local.ar.com.tere;

import local.ar.com.tere.model.Galaxy.Planet.Planet;
import local.ar.com.tere.model.Galaxy.SolarSystem.WeatherSolarSystem;
import local.ar.com.tere.model.Weather.WeatherType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WeatherSolarSystemTest {
    private WeatherSolarSystem weatherAliens;

    @Before
    public void setUp() throws Exception {
        List<Planet> planets = new ArrayList<>();
        Planet Ferengui = new Planet("Ferengui", 1, 500);
        Planet Betasoide = new Planet("Betasoide", 3, 2000);
        Planet Vulcano = new Planet("Vulcano", -5, 1000);
        planets.add(Ferengui); planets.add(Betasoide); planets.add(Vulcano);

        this.weatherAliens = new WeatherSolarSystem("WeatherAliens", planets);
    }

    @Test
    public void initialWeatherIsDROUGHT() {
        assertEquals(WeatherType.DROUGHT, weatherAliens.getDayWeather(0));
    }

    @Test
    public void day10WeatherNORMAL() {
        assertEquals(WeatherType.NORMAL, weatherAliens.getDayWeather(10));
    }
}
