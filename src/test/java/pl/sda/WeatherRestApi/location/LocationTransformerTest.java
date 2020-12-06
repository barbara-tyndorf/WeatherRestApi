package pl.sda.WeatherRestApi.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sda.WeatherRestApi.weather.Weather;
import pl.sda.WeatherRestApi.weather.WeatherRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//FIXME
@ExtendWith(SpringExtension.class)
class LocationTransformerTest {

    @TestConfiguration
    static class LocationTransformerTestConfiguration{

        @Bean
        public LocationTransformer locationTransformer (WeatherRepository weatherRepository) {
            return new LocationTransformer(weatherRepository);
        }
    }

    @MockBean
    private WeatherRepository weatherRepository;

    @Autowired
    private LocationTransformer locationTransformer;

    @Test
    void toDTO_should_convert_object_Location_to_LocationDTO() {
        //given
        Weather weather = new Weather();
        Location location = new Location("1",15,30,"Szczecin", "zachodniopomorskie", "Polska");
        location.setWeathers(Arrays.asList(weather));
        //when
        LocationDTO locationDTO = locationTransformer.toDTO(location);
        //then
        assertEquals(locationDTO,locationTransformer.toDTO(location));
        assertArrayEquals(Arrays.asList(10L).toArray(),locationDTO.getWeathersIds().toArray());
    }

    @Test
    void toEntity_should_convert_object_LocationDTO_to_Location() {
        //given
        Weather weather = new Weather();
        weather.setId(10);

        Mockito.when(weatherRepository.findById(10L)).thenReturn(Optional.of(weather));
        LocationDTO locationDTO = new LocationDTO("1",15,30,"Szczecin", "zachodniopomorskie",
                "Polska", new ArrayList(Arrays.asList("1","2","3")));
        //when
        Location location = locationTransformer.toEntity(locationDTO);
        //then
        assertEquals(location,locationTransformer.toEntity(locationDTO));
        assertEquals(weather, location.getWeathers().get(0));
    }
}