package pl.sda.WeatherRestApi.weather;
import org.hibernate.annotations.GenericGenerator;
import pl.sda.WeatherRestApi.location.Location;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Min(-100)
    @Max(100)
    private double temperature;

    @Min(800)
    @Max(1100)
    private double pressure;

    @NotEmpty
    private String humidity;

    @NotEmpty
    private String windDirection;

    @NotEmpty
    private double windSpeed;

    @NotEmpty
    private String date;

    @ManyToOne
    private Location location;

    public Weather() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id='" + id + '\'' +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity='" + humidity + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", date='" + date + '\'' +
                ", locationId=" + location +
                '}';
    }
}
