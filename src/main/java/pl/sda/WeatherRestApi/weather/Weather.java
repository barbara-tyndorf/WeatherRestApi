package pl.sda.WeatherRestApi.weather;
import pl.sda.WeatherRestApi.location.Location;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;


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

    @NotNull
    @Min(0)
    private double humidity;

    @NotEmpty
    private String windDirection;

    @NotNull
    @Min(0)
    private double windSpeed;

    @NotEmpty
    private String date;

    @ManyToOne
    private Location location;

    public Weather() {
    }

    public Weather(long id, double temperature, String windDirection, double windSpeed, double pressure, double humidity, String date, Location location) {
        this.id = id;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.humidity = humidity;
        this.date = date;
        this.location = location;
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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return id == weather.id &&
                Double.compare(weather.temperature, temperature) == 0 &&
                Double.compare(weather.pressure, pressure) == 0 &&
                Double.compare(weather.windSpeed, windSpeed) == 0 &&
                Objects.equals(humidity, weather.humidity) &&
                Objects.equals(windDirection, weather.windDirection) &&
                Objects.equals(date, weather.date) &&
                Objects.equals(location, weather.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, pressure, humidity, windDirection, windSpeed, date, location);
    }
}
