package local.ar.com.tere.model.Weather;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class WeatherDay {
    @Id String day;
    @Index String weather;

    private WeatherDay() {}

    public WeatherDay(int day, WeatherType weather) {
        this.day = String.valueOf(day);
        this.weather = weather.toString();
    }

    public String getWeather() {
        return weather;
    }
}