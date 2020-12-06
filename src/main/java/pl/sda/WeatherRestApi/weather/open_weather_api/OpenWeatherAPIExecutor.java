package pl.sda.WeatherRestApi.weather.open_weather_api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherAPIExecutor {

    public OpenWeatherResponse fetchWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = new OpenWeatherApiBuilder().setCityName(city).build();
        OpenWeatherResponse response = restTemplate.getForObject(url,
                OpenWeatherResponse.class);
        return response;
    }

    public OpenWeatherResponse fetchWeather(String latitude, String longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String url = new OpenWeatherApiBuilder().setCoordinates(latitude, longitude).build();
        OpenWeatherResponse response = restTemplate.getForObject(url,
                OpenWeatherResponse.class);
        return response;
    }
}
