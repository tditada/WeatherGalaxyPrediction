package local.ar.com.tere.Main;

import local.ar.com.tere.model.Galaxy.Planet.Planet;
import local.ar.com.tere.model.Galaxy.SolarSystem.WeatherSolarSystem;
import local.ar.com.tere.model.Weather.WeatherType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minidev.json.JSONObject;
import org.apache.log4j.BasicConfigurator;

import static spark.Spark.*;

/*
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

    }
}*/

public class Main {
    /*public static void main(String[] args) {
        BasicConfigurator.configure();

        List<Planet> planets = new ArrayList<>();
        planets.add(new Planet("Ferengui", 1, 500));
        planets.add(new Planet("Betasoide", 3, 2000));
        planets.add(new Planet("Vulcano", -5, 1000));
        WeatherSolarSystem weatherAliens = new WeatherSolarSystem("WeatherAliens", planets);

        get("/weather", (req, res) -> {
            int day = Integer.parseInt(req.queryParams("day"));
            JSONObject response = new JSONObject();
            WeatherType result = weatherAliens.getDayWeather(day);
            response.put("weather", result.toString());
            response.put("day", day);
            return response;
        });

        get("/weatherforecast", (req, res) -> {
            int firstDay = Integer.parseInt(req.queryParams("firstDay"));
            int lastDay = Integer.parseInt(req.queryParams("lastDay"));
            Map<WeatherType, Integer> result = weatherAliens.getWeatherPrediction(firstDay, lastDay);

            JSONObject response = new JSONObject();
            result.forEach((type,count)->response.put(type.toString(), count));
            response.put("max rain day", weatherAliens.getMaxRainDay());
            return response;
        });
    }*/
}
