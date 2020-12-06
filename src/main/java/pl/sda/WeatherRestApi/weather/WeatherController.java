package pl.sda.WeatherRestApi.weather;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/weather")
@RestController
public class WeatherController {

    public final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api")
    public WeatherDTO getFromAPI(@RequestParam String city) {
        return weatherService.getFromAPI(city);
    }

    @GetMapping("/api/coord")
    public WeatherDTO getFromAPI(@RequestParam String lon, @RequestParam String lat) {
        return weatherService.getFromAPI(lon, lat);
    }

    @GetMapping
    public List<WeatherDTO> getAll() {
        return weatherService.getAll();
    }

    @PostMapping
    public WeatherDTO add(@Valid @RequestBody WeatherDTO weatherDTO) {
        return weatherService.add(weatherDTO);
    }

    @DeleteMapping("/{id}")
    public Weather delete(@PathVariable long id) {
        return weatherService.remove(id);
    }
}
