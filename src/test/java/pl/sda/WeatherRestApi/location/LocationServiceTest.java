package pl.sda.WeatherRestApi.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class LocationServiceTest {

    @TestConfiguration
    static class LocationServiceTestConfiguration {

        @Bean
        public LocationService locationService(LocationRepository locationRepository, LocationTransformer locationTransformer) {
            return new LocationService(locationRepository, locationTransformer);
        }
    }

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private LocationTransformer locationTransformer;

    @Autowired
    private LocationService locationService;

    //FIXME
    @Test
    void getAll_should_return_expected_amount_of_results(){
        //given
        Location location1 = new Location("0",15,30,"Szczecin",
                "zachodniopomorskie", "Polska");
        Location location2 = new Location("1",25,50,"Wrocław",
                "dolnośląskie", "Polska");
        LocationDTO locationDTO1 = locationTransformer.toDTO(location1);
        LocationDTO locationDTO2 = locationTransformer.toDTO(location2);
        //when
        locationService.add(locationDTO1);
        locationService.add(locationDTO2);
        //then
        assertEquals(locationService.getAll().size(),2);
    }
}