import model.Galaxy.Planet.Planet;
import model.Galaxy.SolarSystem.SolarSystem;
import model.Galaxy.SolarSystem.WeatherSolarSystem;
import model.Weather.WeatherType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        List<Planet> planets = new ArrayList<>();
        Planet Ferengui = new Planet("Ferengui", 1, 500);
        Planet Betasoide = new Planet("Betasoide", 3, 2000);
        Planet Vulcano = new Planet("Vulcano", -5, 1000);
        planets.add(Ferengui); planets.add(Betasoide); planets.add(Vulcano);

        WeatherSolarSystem weatherAliens = new WeatherSolarSystem("WeatherAliens", planets);
        Map<WeatherType, Integer> results = weatherAliens.getWeatherPrediction(0, 10*360);
        for (Map.Entry entry : results.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
       /* System.out.print(Math.sin(Math.toRadians(3)) + "\n");
        System.out.print(Math.cos(Math.toRadians(3)));*/

    }
}
