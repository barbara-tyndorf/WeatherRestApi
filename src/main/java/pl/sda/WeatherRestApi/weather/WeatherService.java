package pl.sda.WeatherRestApi.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.WeatherRestApi.location.LocationRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class WeatherService {

    private final WeatherTransformer weatherTransformer;
    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;
    private final ExternalWeatherAPIAdapter externalWeatherAPIAdapter;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository,
                          LocationRepository locationRepository,
                          WeatherTransformer weatherTransformer,
                          ExternalWeatherAPIAdapter externalWeatherAPIAdapter) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
        this.weatherTransformer = weatherTransformer;
        this.externalWeatherAPIAdapter = externalWeatherAPIAdapter;
    }

    public WeatherDTO getFromAPI(String city) {
        return weatherTransformer.toDTO(
                externalWeatherAPIAdapter.provideWeather(city));
    }

    public WeatherDTO getFromAPI(String lon, String lat) {
        return weatherTransformer.toDTO(
                externalWeatherAPIAdapter.provideWeather(lon, lat));
    }

    public List<WeatherDTO> getAll() {
        return weatherRepository.findAll()
                .stream()
                .map((Weather w) -> weatherTransformer.toDTO(w))
                .collect(Collectors.toList());
    }

    public WeatherDTO add(WeatherDTO weatherDTO) {
        Weather weatherEntity = weatherTransformer.toEntity(weatherDTO);
        Weather weatherFromDB = weatherRepository.save(weatherEntity);
        weatherEntity.getLocation().getWeathers().add(weatherFromDB);
        locationRepository.save(weatherEntity.getLocation());
        return weatherTransformer.toDTO(weatherEntity);
    }

    public Weather remove(long id) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoSuchElementException();
                });
        weatherRepository.delete(weather);
        return weather;
    }
}
