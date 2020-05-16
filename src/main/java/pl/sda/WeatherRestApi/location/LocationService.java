package pl.sda.WeatherRestApi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAll() {
        return locationRepository.getAll();
    }


    public Location add(Location location) {
        locationRepository.getAll().stream()
                .filter((Location l) -> l.getId() == location.getId())
                .findAny()
                .ifPresent((l) -> {
                    throw new IllegalArgumentException("Location already exist!");
                });
        locationRepository.add(location);
        return location;
    }

    @GetMapping
    public Location findByLongAndLat(double longi, double lat) {
        return locationRepository.getAll().stream()
                .filter((l) -> l.getLongitude() == longi)
                .filter((l) -> l.getLatitude() == lat)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("There is no location with provided geographic coordinates");
                });
    }

    public List<Location> findByName(String name) {
        return locationRepository.getAll().stream()
                .filter((l) -> l.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Location findById(String id) {
        return locationRepository.getAll().stream()
                .filter((l) -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("There is no location with provided id");
                });
    }

    public Location updateLocation(Location location) {
        LocationService locationService = new LocationService(locationRepository);
        Location locationToUpdate =
                locationService.findByLongAndLat(location.getLongitude(), location.getLatitude());
        locationToUpdate = location;
        return location;
    }

    public void deleteLocation(Location location) {
        LocationService locationService = new LocationService(locationRepository);
        Location locationToDelete =
                locationService.findByLongAndLat(location.getLongitude(), location.getLatitude());
        locationRepository.delete(locationToDelete);
    }
}
