package pl.sda.WeatherRestApi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.WeatherRestApi.location.errors.LocationExistException;
import pl.sda.WeatherRestApi.location.errors.NoLocationsFoundException;


import java.util.*;
import java.util.stream.Collectors;


@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationTransformer locationTransformer;

    @Autowired
    public LocationService(LocationRepository locationRepository, LocationTransformer locationTransformer) {
        this.locationRepository = locationRepository;
        this.locationTransformer = locationTransformer;
    }

    public List<LocationDTO> getAll() {

        return locationRepository.findAll()
                .stream()
                .map(locationTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public LocationDTO add(LocationDTO locationDTO) {
        locationRepository.findAll().stream()
                .filter((Location l) -> l.getLatitude() == locationDTO.getLatitude())
                .filter((Location l) -> l.getLongitude() == locationDTO.getLongitude())
                .findAny()
                .ifPresent((l) -> {
                    throw new LocationExistException(locationDTO);
                });
        Location locationEntity = locationTransformer.toEntity(locationDTO);
        Location locationFromDB = locationRepository.save(locationEntity);
        return locationTransformer.toDTO(locationFromDB);
    }

    public LocationDTO updateLocation(String id, Map<String, String> params) {
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
        locationRepository.save(location);
        return locationTransformer.toDTO(location);
    }

    public List<LocationDTO> findBy(Map<String, String> params) {
        List<LocationDTO> foundLocations = new ArrayList<>();

        if (params.containsKey("id")) {
            String id = params.get("id");
            locationRepository.findById(id)
                    .ifPresent(l -> foundLocations.add(locationTransformer.toDTO(l)));
        }
        if (params.containsKey("longitude") && params.containsKey("latitude")) {
            double longi = Double.parseDouble(params.get("longitude"));
            double lat = Double.parseDouble(params.get("latitude"));
            locationRepository.findByLongitudeAndLatitude(longi, lat)
                    .ifPresent(l -> foundLocations.add(locationTransformer.toDTO(l)));
        }
        if (params.containsKey("name")) {
            foundLocations.addAll(locationRepository.findByName(params.get("name")).stream()
                    .map(locationTransformer::toDTO)
                    .collect(Collectors.toList()));
        }
        if (params.containsKey("region")) {
            foundLocations.addAll(locationRepository.findByRegion(params.get("region")).stream()
                    .map(locationTransformer::toDTO)
                    .collect(Collectors.toList()));
        }
        if (params.containsKey("country")) {
            foundLocations.addAll(locationRepository.findByCountry(params.get("country")).stream()
                    .map(locationTransformer::toDTO)
                    .collect(Collectors.toList()));
        }
        return foundLocations;
    }

    public List<LocationDTO> findAllByName(String name, int start, int size) {
        Pageable pageable = PageRequest.of(start, size);
        return locationRepository.findAllByName(name, pageable).stream()
                .map(locationTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public LocationDTO findById(String id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoLocationsFoundException();
                });
        return locationTransformer.toDTO(location);
    }

    public void deleteLocation(LocationDTO locationDTO) {
        Location location = locationTransformer.toEntity(locationDTO);
        locationRepository.delete(location);
    }

    public List<LocationDTO> sortBy(String sort) {

        if (sort.equals("desc")) {
            return locationRepository.findByOrderByCountryDescNameDesc()
                    .stream()
                    .map(locationTransformer::toDTO)
                    .collect(Collectors.toList());
        } else {
            return locationRepository.findByOrderByCountryAscNameAsc()
                    .stream()
                    .map(locationTransformer::toDTO)
                    .collect(Collectors.toList());
        }
    }
}
