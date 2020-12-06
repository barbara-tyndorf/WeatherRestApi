package pl.sda.WeatherRestApi.weather.open_weather_api;

public class OpenWeatherApiUrl {

     String host;
     String cityName;
     String units;
     String appId;
     String latitude;
     String longitude;

     OpenWeatherApiUrl(String host, String cityName, String units, String appId, String latitude, String longitude) {
        this.host = host;
        this.cityName = cityName;
        this.units = units;
        this.appId = appId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
