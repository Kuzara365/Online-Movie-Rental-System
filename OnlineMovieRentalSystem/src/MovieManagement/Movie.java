package MovieManagement;

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
        return String.format("-------------------------------------------\n"
                + "| %-15s | %-20d |\n"
                + "| %-15s | %-20d |\n"
                + "| %-15s | %-20s |\n"
                + "| %-15s | %-20s |\n"
                + "| %-15s | %-20.1f |\n"
                + "| %-15s | %-20s |\n"
                + "| %-15s | %-20.2f |\n"
                + "| %-15s | %-20d |\n"
                + "-------------------------------------------",
                "Movie ID", MovieID,
                "Category ID", categoryID,
                "Title", title,
                "Description", description,
                "Rating", rating,
                "Availability", availability ? "Available" : "Not Available",
                "Rental Price", rentalPrice,
                "Year of Release", yearOfRelease
        );
    }
}
