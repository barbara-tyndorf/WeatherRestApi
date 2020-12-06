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
    public LocationDTO add(@Valid @RequestBody LocationDTO locationDTO) {
        return locationService.add(locationDTO);
    }

    @GetMapping
    public List<LocationDTO> getAll() {
        return locationService.getAll();
    }

    @GetMapping("/{id}")
    public LocationDTO getLocationById(@PathVariable String id) {
        return locationService.findById(id);
    }

    @GetMapping("/find")
    public List<LocationDTO> getLocations(@RequestParam(required = false) Map<String, String> params) {
        return locationService.findBy(params);
    }

    @GetMapping("/name")
    public List<LocationDTO> getLocationByName(@RequestParam String name, @RequestParam int start, @RequestParam int size) {
        return locationService.findAllByName(name, start, size);
    }

    @GetMapping("/sort")
    public List<LocationDTO> getLocationSortedBy(@RequestParam (required = false) String sort)
//                                              ,@RequestParam int page, @RequestParam int size)
    {
        return locationService.sortBy(sort);
//                , page, size);
    }

    @PutMapping
    public LocationDTO update(@RequestParam String id, @Valid @RequestParam Map<String, String> params) {
        return locationService.updateLocation(id, params);
    }

    @DeleteMapping
    public String delete(LocationDTO locationDTO) {
        locationService.deleteLocation(locationDTO);
        return "Location removed successfully!";
    }
}
