package pl.sda.WeatherRestApi.location;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class LocationRepository {

    private final List<Location> locations = new ArrayList<>();

    public Location add(Location location) {
        locations.add(location);
        return location;
    }

    public List<Location> getAll() {
        return locations;
    }

    public Location update(Location location) {
        locations.stream()
                .filter((l) -> l.getId().equals(location.getId()))
                .findAny()
//                .ifPresentOrElse((l) -> {
//                    l.setLongitude(location.getLongitude());
//                    l.setLatitude(location.getLatitude());
//                    l.setName(location.getName());
//                    l.setRegion(location.getRegion());
//                    l.setCountry(location.getCountry());
//                })
                .map((l) -> l = location);
        return location;
    }

    public void delete(Location location) {
        locations.remove(location);
    }
}
