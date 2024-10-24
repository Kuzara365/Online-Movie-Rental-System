package Movies;

public class Movie {

    private int MovieID;
    private int categoryID;
    private String title;
    private String description;
    private float rating;
    private boolean availability;
    private double rentalPrice;
    private int yearOfRelease;

    public Movie() {
    }

    public Movie(int MovieID, int categoryID, String title, String description, float rating, boolean availability, double rentalPrice, int yearOfRelease) {
        this.MovieID = MovieID;
        this.categoryID = categoryID;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
                "| %-10d | %-17s | %-14s | %-6.1f | %-12s | %-10.2f | %-10d | %-14d |\n",
                MovieID, title, description, rating,
                availability ? "Available" : "Not Available",
                rentalPrice, categoryID, yearOfRelease
        );
    }

    public Movie inputMovie() {
        int movieId = inputHelper.readInt("Enter Movie's ID: ");
        String title = inputHelper.readString("Enter Title: ");
        String description = inputHelper.readString("Enter Description: ");
        float rating = inputHelper.readFloat("Enter Rating: ");
        boolean avail = inputHelper.readBoolean("Enter Status:\n"
                + "1. True\n"
                + "0. False\n"
                + "Choice: ");
        double rentalPrice = inputHelper.readDouble("Enter Rental's price: ");
        int CategoryId = inputHelper.readInt("Enter Category's ID: ");
        int yearOfRelease = inputHelper.readInt("Enter Year Of Release: ");
        return new Movie(movieId, CategoryId, title, description, rating, avail, rentalPrice, yearOfRelease);
    }
}
