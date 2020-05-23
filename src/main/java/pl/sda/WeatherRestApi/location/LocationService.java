package pl.sda.WeatherRestApi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.WeatherRestApi.location.errors.LocationExistException;
import pl.sda.WeatherRestApi.location.errors.NoLocationsFoundException;

import java.util.*;


@Service
public class LocationService {

    private final LocationDBRepository locationRepository;

    @Autowired
    public LocationService(LocationDBRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location add(Location location) {
        locationRepository.findAll().stream()
                .filter((Location l) -> l.getLatitude() == location.getLatitude())
                .filter((Location l) -> l.getLongitude() == location.getLongitude())
                .findAny()
                .ifPresent((l) -> {
                    throw new LocationExistException(location);
                });
        return locationRepository.save(location);
    }

    public Location updateLocation(String id, Map<String, String> params) {
        Location location = locationRepository.findById(id).orElseThrow(() -> {
            throw new NoLocationsFoundException();
        });
        if (params.containsKey("name")) {
            location.setName(params.get("name"));
        }
        if (params.containsKey("region")) {
            location.setRegion(params.get("region"));
        }
        if (params.containsKey("country")) {
            location.setCountry(params.get("country"));
        }
        if (params.containsKey("longitude")) {
            location.setLongitude(Double.parseDouble(params.get("longitude")));
        }
        if (params.containsKey("latitude")) {
            location.setLatitude(Double.parseDouble(params.get("latitude")));
        }
        return locationRepository.save(location);
    }

    public List<Location> findBy(Map<String, String> params) {
        List<Location> foundLocations = new ArrayList<>();

        if (params.containsKey("id")) {
            String id = params.get("id");
            locationRepository.findById(id).ifPresent(foundLocations::add);
        }
        if (params.containsKey("longitude") && params.containsKey("latitude")) {
            double longi = Double.parseDouble(params.get("longitude"));
            double lat = Double.parseDouble(params.get("latitude"));
            locationRepository.findByLongitudeAndLatitude(longi, lat).ifPresent(foundLocations::add);
        }
        if (params.containsKey("name")) {
            foundLocations.addAll(locationRepository.findByName(params.get("name")));
        }
        if (params.containsKey("region")) {
            foundLocations.addAll(locationRepository.findByRegion(params.get("region")));
        }
        if (params.containsKey("country")) {
            foundLocations.addAll(locationRepository.findByCountry(params.get("country")));
        }
        return foundLocations;
    }

    public List<Location> findAllByName(String name, int start, int size) {
        Pageable pageable = PageRequest.of(start, size);
        return locationRepository.findAllByName(name, pageable);
    }

    public Location findById(String id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoLocationsFoundException();
                });
    }

    public void deleteLocation(Location location) {
        locationRepository.delete(location);
    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public List<Location> sortBy(String sort) {

        if (sort.equals("desc")) {
            return locationRepository.findByOrderByCountryDescNameDesc();
        } else {
            return locationRepository.findByOrderByCountryAscNameAsc();
        }
    }
}
