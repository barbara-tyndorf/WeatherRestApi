package pl.sda.WeatherRestApi.mockito;

public class Movie {
    private String title;
    private String description;
    private int rating;
    private int yearOfRelease;

    public Movie(String title, String description, int rating, int yearOfRelease) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
