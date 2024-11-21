package Movies;

public class Movie {

    private int MovieID;
    private String title;
    private String description;
    private float rating;
    private boolean availability;
    private double rentalPrice;
    private int yearOfRelease;

    public Movie() {
    }

    public Movie(int MovieID, String title, String description, float rating, boolean availability, double rentalPrice, int yearOfRelease) {
        this.MovieID = MovieID;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.availability = availability;
        this.rentalPrice = rentalPrice;
        this.yearOfRelease = yearOfRelease;
    }

    public Movie(String title, String description, float rating, boolean availability, double rentalPrice, int yearOfRelease) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.availability = availability;
        this.rentalPrice = rentalPrice;
        this.yearOfRelease = yearOfRelease;
    }

    public int getMovieID() {
        return MovieID;
    }

    public void setMovieID(int MovieID) {
        this.MovieID = MovieID;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    @Override
    public String toString() {
        return String.format(
                "| %-10s | %-20s | %-25s | %-6s | %-12s | %-10s | %-14s |\n",
                MovieID, title, description, rating,
                availability ? "Available" : "Not Available",
                rentalPrice, yearOfRelease
        );
    }

    public Movie inputMovie() {
        String title = inputHelper.readString("Enter Title: ");
        String description = inputHelper.readString("Enter Description: ");
        float rating = inputHelper.readFloat("Enter Rating: ");
        boolean avail = inputHelper.readBoolean("Enter Status:\n"
                + "1. Available\n"
                + "0. Not Available\n"
                + "Choice: ");
        double rentalPrice = inputHelper.readDouble("Enter Rental's price: ");
        int yearOfRelease = inputHelper.readInt("Enter Year Of Release: ");
        return new Movie(title, description, rating, avail, rentalPrice, yearOfRelease);
    }
}
