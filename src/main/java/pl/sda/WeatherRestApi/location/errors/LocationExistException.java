package pl.sda.WeatherRestApi.location.errors;

import pl.sda.WeatherRestApi.location.LocationDTO;

public class LocationExistException extends RuntimeException {

    public LocationExistException(LocationDTO locationDTO) {
        super("Location already exist!");
    }
}
