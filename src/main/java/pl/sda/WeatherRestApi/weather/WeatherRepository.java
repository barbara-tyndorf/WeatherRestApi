package pl.sda.WeatherRestApi.weather;

import org.springframework.data.jpa.repository.JpaRepository;


public interface WeatherRepository extends JpaRepository<Weather, Long> {


}
