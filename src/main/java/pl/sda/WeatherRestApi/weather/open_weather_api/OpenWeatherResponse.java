package pl.sda.WeatherRestApi.weather.open_weather_api;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherResponse {

    @JsonProperty("coord")
    private Coordinates coordinates;

    @JsonProperty("main")
    private WeatherBaseParams weatherBaseParams;

    private Wind wind;
    private String name;
    private String country;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public WeatherBaseParams getWeatherBaseParams() {
        return weatherBaseParams;
    }

    public void setWeatherBaseParams(WeatherBaseParams weatherBaseParams) {
        this.weatherBaseParams = weatherBaseParams;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
