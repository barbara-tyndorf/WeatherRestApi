package pl.sda.WeatherRestApi.mockito;

import java.util.List;

public interface MovieHttpClient {

    List<Movie> fetchAllMovies();
    List<Movie> findMovieByTitle(String title);
    List<Movie> findMovieByRating(int rating);
    List<Movie> findMovieByYearOfRelease(int yearOfRelease);
}
