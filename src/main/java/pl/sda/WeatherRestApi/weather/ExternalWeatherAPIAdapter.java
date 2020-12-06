package pl.sda.WeatherRestApi.weather;

import org.springframework.stereotype.Component;
import pl.sda.WeatherRestApi.weather.Weather;

@Component
public interface ExternalWeatherAPIAdapter {

    Weather provideWeather(String city);

    Weather provideWeather(String lon, String lat);

}
