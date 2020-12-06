package pl.sda.WeatherRestApi.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.WeatherRestApi.location.LocationRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class WeatherServiceTest {
    @TestConfiguration
    static class WeatherServiceTestConfiguration {
        @Bean
        public WeatherService weatherService(WeatherRepository weatherRepository,
                                             LocationRepository locationRepository,
                                             WeatherTransformer weatherTransformer,
                                             ExternalWeatherAPIAdapter externalWeatherAPIAdapter) {
            return new WeatherService(weatherRepository, locationRepository, weatherTransformer, externalWeatherAPIAdapter);
        }
    }

    @MockBean
    private WeatherRepository weatherRepository;
    @MockBean
    private LocationRepository locationRepository;
    @MockBean
    private WeatherTransformer weatherTransformer;
    @MockBean
    private ExternalWeatherAPIAdapter externalWeatherAPIAdapter;
    @Autowired
    private WeatherService weatherService;

    @Test
    void when_get_weather_from_external_api_by_city_then_weather_dto_should_be_returned() {
        //given
        Weather weather = new Weather();
        weather.setId(1);
        weather.setTemperature(30);
        weather.setPressure(1000);
        weather.setHumidity(10);

        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setId(1);
        weatherDTO.setTemperature(30);
        weatherDTO.setPressure(1000);
        weatherDTO.setHumidity(10);

        Mockito.when(externalWeatherAPIAdapter.provideWeather("Szczecin")).thenReturn(weather);
        Mockito.when(weatherTransformer.toDTO(weather)).thenReturn(weatherDTO);
        //when
        WeatherDTO weatherDTOResult = weatherService.getFromAPI("Szczecin");
        //then
        assertEquals(1, weatherDTOResult.getId());
        assertEquals(1000, weatherDTOResult.getPressure());
        assertEquals(30, weatherDTOResult.getTemperature());
    }

    @Test
    void when_get_weather_from_external_api_by_coordinates_then_weather_dto_should_be_returned() {
        //given
        Weather weather = new Weather();
        weather.setId(1);
        weather.setTemperature(30);
        weather.setPressure(1000);
        weather.setHumidity(10);

        WeatherDTO weatherDTO = new WeatherDTO();
        weatherDTO.setId(1);
        weatherDTO.setTemperature(30);
        weatherDTO.setPressure(1000);
        weatherDTO.setHumidity(10);

        Mockito.when(externalWeatherAPIAdapter.provideWeather("15","30")).thenReturn(weather);
        Mockito.when(weatherTransformer.toDTO(weather)).thenReturn(weatherDTO);
        //when
        WeatherDTO weatherDTOResult = weatherService.getFromAPI("15","30");
        //then
        assertEquals(1, weatherDTOResult.getId());
        assertEquals(1000, weatherDTOResult.getPressure());
        assertEquals(30, weatherDTOResult.getTemperature());
    }
}