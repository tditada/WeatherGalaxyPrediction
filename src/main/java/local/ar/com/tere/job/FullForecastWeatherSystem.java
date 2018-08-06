package local.ar.com.tere.job;

import com.googlecode.objectify.ObjectifyService;
import local.ar.com.tere.model.Galaxy.Planet.Planet;
import local.ar.com.tere.model.Galaxy.SolarSystem.WeatherSolarSystem;
import local.ar.com.tere.model.Weather.WeatherDay;
import local.ar.com.tere.model.Weather.WeatherType;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class FullForecastWeatherSystem {
    static WeatherSolarSystem weatherAliens;

    private static void generateSystem() {
        if (weatherAliens == null) {
            List<Planet> planets = new ArrayList<>();
            planets.add(new Planet("Ferengui", 1, 500));
            planets.add(new Planet("Betasoide", 3, 2000));
            planets.add(new Planet("Vulcano", -5, 1000));
            weatherAliens = new WeatherSolarSystem("WeatherAliens", planets);
        }
   }

    /**
     * Job that generates the model objects for the weather in {@param years}
     * Days in year are assumed as the longest year from system planets
     */
    public static void calculateFullForecast(int years) {
        generateSystem();
        int daysInYear = weatherAliens.getMaxDaysInYear();
        int lastDay =  daysInYear* years;
        List<WeatherDay> weathers = new ArrayList<>();

        for (int day = 0; day < lastDay; day ++){
            WeatherType dayWeatherType = weatherAliens.getDayWeather(day);
            WeatherDay dayWeather = new WeatherDay(day, dayWeatherType);
            weathers.add(dayWeather);

            if (weathers.size() > daysInYear) {
                ofy().save().entities(weathers);
                weathers = new ArrayList<>();
            }
        }
        ofy().save().entities(weathers);
    }

    public static WeatherSolarSystem getWeatherAliens() {
        return weatherAliens;
    }
}
