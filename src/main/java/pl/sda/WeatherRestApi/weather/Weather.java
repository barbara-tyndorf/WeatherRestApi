package pl.sda.WeatherRestApi.weather;
//import com.google.gson.annotations.SerializedName;
import pl.sda.WeatherRestApi.location.Location;

import java.time.LocalDate;

public class Weather {

    private String id;
    private double temperature;
    private int pressure;
    private String humidity;

//    @SerializedName("wind_direction")
    private String windDirection;

//    @SerializedName("wind_speed")
    private String windSpeed;

    private LocalDate date;
    private String locationId;

}
