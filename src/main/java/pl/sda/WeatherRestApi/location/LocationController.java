package pl.sda.WeatherRestApi.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/location")
@RestController
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location add(@Valid @RequestBody Location location) {
        return locationService.add(location);
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable String id) {
        return locationService.findById(id);
    }

    @GetMapping("/find")
    public List<Location> getLocations(@RequestParam(required = false) Map<String, String> params) {
        return locationService.findBy(params);
    }

    @GetMapping("/name")
    public List<Location> getLocationByName(@RequestParam String name, @RequestParam int start, @RequestParam int size) {
        return locationService.findAllByName(name, start, size);
    }

    @GetMapping("/sort")
    public List<Location> getLocationSortedBy(@RequestParam (required = false) String sort)
//                                              ,@RequestParam int page, @RequestParam int size)
    {
        return locationService.sortBy(sort);
//                , page, size);
    }

    @PutMapping
    public Location update(@RequestParam String id, @Valid @RequestParam Map<String, String> params) {
        return locationService.updateLocation(id, params);
    }

    @DeleteMapping
    public String delete(Location location) {
        locationService.deleteLocation(location);
        return "Location removed successfully!";
    }
}
