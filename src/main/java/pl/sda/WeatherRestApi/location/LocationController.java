package pl.sda.WeatherRestApi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/location")
@RestController
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController (LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @PostMapping
    public Location add(@Valid @RequestBody Location location) {
        return locationService.add(location);
    }

    @PutMapping
    public Location update (@Valid @RequestBody Location location) {
        locationService.updateLocation(location);
        return location;
    }

    @DeleteMapping
    public String delete (Location location) {
        locationService.deleteLocation(location);
        return "Location removed successfully!";
    }
}
