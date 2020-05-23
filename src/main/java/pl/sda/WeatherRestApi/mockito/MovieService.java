package pl.sda.WeatherRestApi.mockito;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovieService {

    private final MovieHttpClient movieHttpClient;

    public MovieService(MovieHttpClient movieHttpClient) {
        this.movieHttpClient = movieHttpClient;
    }

    public Set<Movie> findBy(Map<String, String> params) {
        Set<Movie> results = new HashSet<>();
        if (params.containsKey("title")) {
            results.addAll(movieHttpClient.findMovieByTitle(params.get("title")));
        }if (params.containsKey("rating")) {
            results.addAll(movieHttpClient
                    .findMovieByRating(Integer.parseInt(params.get("rating"))));
        }if (params.containsKey("yearOfRelease")) {
            results.addAll(movieHttpClient
                    .findMovieByYearOfRelease(Integer.parseInt(params.get("yearOfRelease"))));
        }
        if (params.isEmpty()) {
            results.addAll(movieHttpClient.fetchAllMovies());
        }
        return results;
    }
}
