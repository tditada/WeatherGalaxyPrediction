package local.ar.com.tere.Main;

import static spark.Spark.get;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.*;
import local.ar.com.tere.job.FullForecastWeatherSystem;
import local.ar.com.tere.model.Galaxy.SolarSystem.WeatherSolarSystem;
import local.ar.com.tere.model.Weather.WeatherDay;
import local.ar.com.tere.model.Weather.WeatherType;
import net.minidev.json.JSONObject;
import spark.servlet.SparkApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import static com.googlecode.objectify.ObjectifyService.ofy;


public class HelloWeatherForecast implements SparkApplication {

    private static final int YEARS = 10;

    public static void main(String[] args) {
        new HelloWeatherForecast().init();
    }

    @Override
    public void init() {

        //Generates model objects for WeatherDay
        ObjectifyService.init(new ObjectifyFactory(
                DatastoreOptions.newBuilder()
                        .setProjectId("weathergalaxyforecast")
                        .build()
                        .getService()
        ));
        ObjectifyService.register(WeatherDay.class);

        //Calculate Ten Years Forecast
        ObjectifyService.run(() -> {
            FullForecastWeatherSystem.calculateFullForecast(YEARS);
            return "";
        });

        // Gets the weather for a specific day in YEARS years
        get("/weather", (req, res) -> {
            try {
                JSONObject response = new JSONObject();
                String day = req.queryParams("day");

                try {
                    int number = Integer.parseInt(day);
                    int daysInYear = FullForecastWeatherSystem.getWeatherAliens().getMaxDaysInYear();
                    if ( day.equals("") || number < 0 || number > YEARS * daysInYear) {
                        response.put("error", "Parameter day is mandatory and needs to be a positive day number in the next 10 years");
                        res.status(400);
                        return response;
                    }
                } catch (NumberFormatException e) {
                    response.put("error", "Parameter day needs to be a number");
                    res.status(400);
                    return response;
                }

                WeatherDay result = ofy().load().type(WeatherDay.class).id(day).now();
                response.put("weather", result.getWeather());
                response.put("day", day);
                return response;
            } catch (Exception e) {
                return getStackTrace(e);
            }
        });

        // Gets number of every weather type in 10 years and max rain day
        get("/weatherforecast", (req, res) -> {
            WeatherSolarSystem weatherAliens = FullForecastWeatherSystem.getWeatherAliens();

            JSONObject response = new JSONObject();
            Arrays.stream(WeatherType.values()).forEach((weatherType) -> {
                int count = ofy().load().type(WeatherDay.class).filter("weather", weatherType.toString()).list().size();
                response.put(weatherType.toString(), count);
            });

            response.put("max_rain_day", weatherAliens.getMaxRainDay());
            return response;
        });

    }

    private String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    // Use Servlet annotation to define the Spark filter without web.xml:
    @WebFilter(
        filterName = "SparkInitFilter", urlPatterns = {"/*"},
        initParams = {
            @WebInitParam(name = "applicationClass", value = "local.ar.com.tere.Main.HelloWeatherForecast")
        })
    public static class SparkInitFilter extends spark.servlet.SparkFilter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            super.doFilter(request, response, chain);
        }
    }

    @WebFilter(urlPatterns = {"/*"})
    public static class ObjectifyWebFilter extends ObjectifyFilter{
    }

}
