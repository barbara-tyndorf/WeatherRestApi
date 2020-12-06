package pl.sda.WeatherRestApi.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.WeatherRestApi.location.LocationRepository;

@Component
public class WeatherTransformer {

    private final LocationRepository locationRepository;

    @Autowired
    public WeatherTransformer(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public WeatherDTO toDTO(Weather weather) {
        WeatherDTOBuilder weatherDTOBuilder = new WeatherDTOBuilder();
        return weatherDTOBuilder.build(weather);
    }

    public Weather toEntity(WeatherDTO weatherDTO) {
        WeatherDTOBuilder weatherDTOBuilder = new WeatherDTOBuilder();
        return weatherDTOBuilder.build(weatherDTO);
    }
}