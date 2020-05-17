package pl.sda.WeatherRestApi.location.errors;

import pl.sda.WeatherRestApi.location.Location;

public class LocationExistException extends RuntimeException {

    public LocationExistException(Location location) {
        super("Location already exist!");
    }
}
