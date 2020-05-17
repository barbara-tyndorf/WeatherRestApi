package pl.sda.WeatherRestApi.location.errors;

public class NoLocationsFoundException  extends RuntimeException {

    public NoLocationsFoundException() {
        super("Location not found!");
    }
}
