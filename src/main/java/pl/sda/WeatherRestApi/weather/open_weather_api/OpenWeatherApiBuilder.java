package pl.sda.WeatherRestApi.weather.open_weather_api;

public class OpenWeatherApiBuilder {

    private static final String QUERY_PARAM_SEPARATOR = "&";
    private static final String UNITS_PARAM = "units=";
    private static final String CITY_PARAM = "q=";
    private static final String APP_ID_PARAM = "appId=";
    private static final String LAT_PARAM = "lat=";
    private static final String LON_PARAM = "lon=";
    private static final String QUERY_PARAMETERS = "?";
    private String host = "http://api.openweathermap.org/data/2.5/weather";
    private String cityName;
    private String units = "metric";
    private String appId = "76362d77a01e0436d65a4975ea0c417a";
    private String latitude;
    private String longitude;

    public OpenWeatherApiBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public OpenWeatherApiBuilder setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public OpenWeatherApiBuilder setUnits(String units) {
        this.units = units;
        return this;
    }

    public OpenWeatherApiBuilder setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public OpenWeatherApiBuilder setCoordinates(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        return this;
    }

    public String build() {
        /*if (units == null) {
            units = "metric";
        }
        if (host == null || appId == null) {
            throw new IllegalArgumentException("No host or appId provided!");
        }*/
        OpenWeatherApiUrl openWeatherApiUrl = new OpenWeatherApiUrl(host, cityName, units, appId, latitude, longitude);
        StringBuilder builder = new StringBuilder();
        builder.append(openWeatherApiUrl.host)
                .append(QUERY_PARAMETERS)
                .append(UNITS_PARAM)
                .append(openWeatherApiUrl.units)
                .append(QUERY_PARAM_SEPARATOR)
                .append(APP_ID_PARAM)
                .append(openWeatherApiUrl.appId)
                .append(QUERY_PARAM_SEPARATOR);

        if (openWeatherApiUrl.cityName != null) {
            builder.append(CITY_PARAM)
                    .append(cityName);
        } else if (openWeatherApiUrl.latitude != null && openWeatherApiUrl.longitude != null) {
            builder.append(LAT_PARAM)
                    .append(openWeatherApiUrl.latitude)
                    .append(QUERY_PARAM_SEPARATOR)
                    .append(LON_PARAM)
                    .append(openWeatherApiUrl.longitude);
        }
        return builder.toString();
    }
}
