package pl.sda.WeatherRestApi.weather.open_weather_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.WeatherRestApi.weather.ExternalWeatherAPIAdapter;
import pl.sda.WeatherRestApi.weather.Weather;

import java.time.LocalDate;

@Component
public class OpenWeatherAPIAdapter implements ExternalWeatherAPIAdapter {

    private final OpenWeatherAPIExecutor openWeatherAPIExecutor;

    @Autowired
    public OpenWeatherAPIAdapter(OpenWeatherAPIExecutor openWeatherAPIExecutor) {
        this.openWeatherAPIExecutor = openWeatherAPIExecutor;
    }

    public Weather convertToWeather(OpenWeatherResponse response) {
        Weather weather = new Weather();
        weather.setTemperature(response.getWeatherBaseParams().getTemp());
        weather.setPressure(response.getWeatherBaseParams().getPressure());
        weather.setHumidity(response.getWeatherBaseParams().getHumidity());
        weather.setWindSpeed(response.getWind().getSpeed());
        weather.setWindDirection(String.valueOf(response.getWind().getDeg()));
        weather.setDate(LocalDate.now().toString());
        return weather;
    }

    @Override
    public Weather provideWeather(String city) {
        OpenWeatherResponse response = openWeatherAPIExecutor.fetchWeather(city);
        return convertToWeather(response);
    }

    @Override
    public Weather provideWeather(String lon, String lat) {
        OpenWeatherResponse response = openWeatherAPIExecutor.fetchWeather(lon, lat);
        return convertToWeather(response);
    }
}
