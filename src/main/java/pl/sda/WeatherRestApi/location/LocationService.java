package pl.sda.WeatherRestApi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Location add(Location location) {
//        locationRepository.getAll().stream()
//                .filter((Location l) -> l.getId() == location.getId())
//                .findAny()
//                .ifPresent((l) -> {
//                    throw new IllegalArgumentException("Location already exist!");
//                });
        locationRepository.add(location);
        return location;
    }

    public List<Location> getAll() {
        return locationRepository.getAll();
    }

    public Location findByLongAndLat(double longi, double lat) {
        return locationRepository.getAll().stream()
                .filter((l) -> l.getLongitude() == longi)
                .filter((l) -> l.getLatitude() == lat)
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoSuchElementException("There is no location with provided geographic coordinates");
                });
    }

    public Location updateLocation(Location location) {
        return locationRepository.update(location);
    }

    public void deleteLocation(Location location) {
        locationRepository.delete(location);
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

}
