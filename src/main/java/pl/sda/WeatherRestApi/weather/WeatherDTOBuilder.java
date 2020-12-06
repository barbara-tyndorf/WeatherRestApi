package pl.sda.WeatherRestApi.weather;

import pl.sda.WeatherRestApi.location.Location;
import pl.sda.WeatherRestApi.location.LocationRepository;


public class WeatherDTOBuilder {

    private long id;
    private double temperature;
    private String windDirection;
    private double windSpeed;
    private double pressure;
    private double humidity;
    private String date;
    private String locationId;


    public WeatherDTOBuilder setId (long id){
        this.id = id;
        return this;
    }

    public WeatherDTOBuilder setTemperature (double temperature){
        this.temperature = temperature;
        return this;
    }

    public WeatherDTOBuilder setWindDirection (String windDirection){
        this.windDirection = windDirection;
        return this;
    }

    public WeatherDTOBuilder setWindSpeed (double windSpeed){
        this.windSpeed = windSpeed;
        return this;
    }

    public WeatherDTOBuilder setPressure (double pressure){
        this.pressure = pressure;
        return this;
    }

    public WeatherDTOBuilder setHumidity (double humidity){
        this.humidity = humidity;
        return this;
    }

    public WeatherDTOBuilder setDate (String date){
        this.date = date;
        return this;
    }

    public WeatherDTOBuilder setLocationId (String locationId){
        this.locationId = locationId;
        return this;
    }

    public WeatherDTO build(Weather weather) {
        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setId(weather.getId());
        weatherDTO.setDate(weather.getDate());
        weatherDTO.setTemperature(weather.getTemperature());
        weatherDTO.setPressure(weather.getPressure());
        weatherDTO.setHumidity(weather.getHumidity());
        weatherDTO.setWindDirection(weather.getWindDirection());
        weatherDTO.setWindSpeed(weather.getWindSpeed());
//        weatherDTO.setLocationId(weather.getLocation().getId());
        return weatherDTO;
    }

    public Weather build(WeatherDTO weatherDTO) {
        Weather weather = new Weather();
        weather.setId(weatherDTO.getId());
        weather.setDate(weatherDTO.getDate());
        weather.setHumidity(weatherDTO.getHumidity());
        weather.setPressure(weatherDTO.getPressure());
        weather.setTemperature(weatherDTO.getTemperature());
        weather.setWindDirection(weatherDTO.getWindDirection());
        weather.setWindSpeed(weatherDTO.getWindSpeed());
//        locationRepository.findById(weatherDTO.getLocationId())
//                .ifPresent((Location l) -> weather.setLocation(l));
        return weather;
    }
}
