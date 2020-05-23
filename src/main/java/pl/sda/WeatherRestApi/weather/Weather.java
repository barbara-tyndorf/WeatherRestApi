package pl.sda.WeatherRestApi.weather;
//import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.GenericGenerator;
import pl.sda.WeatherRestApi.location.Location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Weather {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Min(-100)
    @Max(100)
    private double temperature;

    @Min(800)
    @Max(1100)
    private int pressure;


    private String humidity;

    private String windDirection;

    private String windSpeed;

    private LocalDate date;
    private String locationId;

}
