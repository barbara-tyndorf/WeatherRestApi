package pl.sda.WeatherRestApi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.WeatherRestApi.weather.WeatherRepository;

import java.util.stream.Collectors;

@Component
public class LocationTransformer {

    private final WeatherRepository weatherRepository;

    @Autowired
    public LocationTransformer(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public LocationDTO toDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setRegion(location.getRegion());
        locationDTO.setCountry(location.getCountry());
        locationDTO.setWeathersIds(
                weatherRepository.findAll().stream()
                .map((w) -> w.getId())
                .collect(Collectors.toList()));

        return locationDTO;
    }

    public Location toEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setName(locationDTO.getName());
        location.setLongitude(locationDTO.getLongitude());
        location.setLatitude(locationDTO.getLatitude());
        location.setRegion(locationDTO.getRegion());
        location.setCountry(locationDTO.getCountry());
        location.setWeathers(weatherRepository.findAllById(locationDTO.getWeathersIds()));

//        locationDTO.getWeathersIds().stream()
//                .forEach((Long id) -> {
//                    weatherRepository.findById(id).ifPresent((Weather w) -> {
//                        location.getWeathers().add(w);
//                    });
//                });
//
        return location;
    }
}
