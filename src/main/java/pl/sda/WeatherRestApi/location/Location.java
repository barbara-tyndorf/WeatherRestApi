package pl.sda.WeatherRestApi.location;

import javax.validation.constraints.*;


public class Location {

    @NotNull
    private String id;

    @Min(-180)
    @Max(180)
    private double longitude;

    @Min(-90)
    @Max(90)
    private double latitude;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    private String region;

    @NotNull
    @NotBlank
    @NotEmpty
    private String country;

    public Location(String id, double longitude, double latitude, String name, String region, String country) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
        this.region = region;
        this.country = country;
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
}
