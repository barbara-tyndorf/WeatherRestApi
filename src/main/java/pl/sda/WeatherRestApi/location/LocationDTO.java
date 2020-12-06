package pl.sda.WeatherRestApi.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LocationDTO {

    private String id;
    private double longitude;
    private double latitude;
    private String name;
    private String region;
    private String country;
    private List<Long> weathersIds = new ArrayList<>();

    public LocationDTO() {
    }

    public LocationDTO(String id, double longitude, double latitude, String name, String region, String country, List<Long> weathersIds) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
        this.region = region;
        this.country = country;
        this.weathersIds = weathersIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Long> getWeathersIds() {
        return weathersIds;
    }

    public void setWeathersIds(List<Long> weathersIds) {
        this.weathersIds = weathersIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDTO that = (LocationDTO) o;
        return Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(region, that.region) &&
                Objects.equals(country, that.country) &&
                Objects.equals(weathersIds, that.weathersIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude, name, region, country, weathersIds);
    }
}
