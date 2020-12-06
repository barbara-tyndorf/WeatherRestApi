package pl.sda.WeatherRestApi.location;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LocationRepository extends JpaRepository<Location, String> {

    List<Location> findAllByName(String name, Pageable pageable);

    List<Location> findByName(String name);

    List<Location> findByRegion(String region);

    List<Location> findByCountry(String region);

    Optional<Location> findByLongitudeAndLatitude(double longi, double lat);

    List<Location> findByOrderByNameAsc();

    List<Location> findByOrderByNameDesc();

    List<Location> findByOrderByCountryAscNameAsc();

    List<Location> findByOrderByCountryDescNameDesc();

}
