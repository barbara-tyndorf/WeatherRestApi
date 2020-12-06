package pl.sda.WeatherRestApi.location;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.WeatherRestApi.WeatherRestApiApplication;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = WeatherRestApiApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class LocationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LocationRepository locationRepository;

    @AfterEach
    void tearDown() {
        locationRepository.deleteAll();
    }

    @Test
    void when_get_location_by_name_then_stored_location_should_be_returned() throws Exception {
        //given
        Location location = new Location();
        location.setName("Szczecin");
        location.setLongitude(15);
        location.setLatitude(53);
        location.setRegion("Zachodniopomorskie");
        location.setCountry("Polska");
        locationRepository.save(location);

        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/location?name=Szczecin@page=1&size=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Szczecin")))
                .andExpect(jsonPath("$[0].region", equalTo("Zachodniopomorskie")));
    }

    @Test
    void when_get_locations_by_name_then_two_stored_location_should_be_returned() throws Exception {
        //given
        Location location = new Location();
        location.setName("Szczecin");
        location.setLongitude(15);
        location.setLatitude(53);
        location.setRegion("Zachodniopomorskie");
        location.setCountry("Polska");
        locationRepository.save(location);

        Location location1 = new Location();
        location1.setName("Szczecin");
        location1.setLongitude(20);
        location1.setLatitude(53);
        location1.setRegion("RFN");
        location1.setCountry("Niemcy");
        locationRepository.save(location1);

        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/location?name=Szczecin@page=1&size=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalTo("Szczecin")))
                .andExpect(jsonPath("$[0].region", equalTo("Zachodniopomorskie")))
                .andExpect(jsonPath("$[0].country", equalTo("Polska")))
                .andExpect(jsonPath("$[1].name", equalTo("Szczecin")))
                .andExpect(jsonPath("$[1].region", equalTo("RFN")))
                .andExpect(jsonPath("$[1].country", equalTo("Niemcy")));
    }

    @Test
    void when_find_by_name_region_country_then_tree_location_should_be_returned() throws Exception {
        //given
        Location location = new Location();
        location.setName("Szczecin");
        location.setLongitude(15);
        location.setLatitude(53);
        location.setRegion("Zachodniopomorskie");
        location.setCountry("Polska");
        locationRepository.save(location);

        Location location1 = new Location();
        location1.setName("Berlin");
        location1.setLongitude(20);
        location1.setLatitude(53);
        location1.setRegion("RFN");
        location1.setCountry("Niemcy");
        locationRepository.save(location1);

        Location location2 = new Location();
        location2.setName("Gryfino");
        location2.setLongitude(12);
        location2.setLatitude(43);
        location2.setRegion("Zachodniopomorskie");
        location2.setCountry("Polska");
        locationRepository.save(location2);

        Location location3 = new Location();
        location3.setName("Barcelona");
        location3.setLongitude(20);
        location3.setLatitude(55);
        location3.setRegion("unkown");
        location3.setCountry("Spain");
        locationRepository.save(location3);

        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/location/find?name=Szczecin&country=Spain&region=RFN")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].name", hasItem("Szczecin")))
                .andExpect(jsonPath("$[*].region", hasItem("RFN")))
                .andExpect(jsonPath("$[*].country", hasItem("Spain")));

    }
}
