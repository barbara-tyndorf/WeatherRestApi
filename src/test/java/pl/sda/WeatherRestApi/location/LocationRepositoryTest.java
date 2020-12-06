package pl.sda.WeatherRestApi.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LocationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void when_find_by_country_value_then_only_one_location_should_be_returned() {
        //given
        Location location = new Location();
        location.setName("Szczecin");
        location.setRegion("Zachodnipomorskie");
        location.setCountry("Polska");
        location.setLongitude(10);
        location.setLatitude(10);

        Location location1 = new Location();
        location1.setName("Berlin");
        location1.setRegion("Stolica");
        location1.setCountry("Niemcy");
        location1.setLongitude(20);
        location1.setLatitude(20);
        entityManager.persist(location);
        entityManager.persist(location1);

        //when
        List<Location> locations = locationRepository.findByCountry("Polska");

        //then
        assertEquals(1, locations.size());
        assertEquals(location, locations.get(0));
    }
}