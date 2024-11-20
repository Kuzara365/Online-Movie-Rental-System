/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieCategories;

/**
 *
 * @author ngoct
 */
public class MovieCategory {
    private int movieID, categoryID;

    public MovieCategory(int movieID, int categoryID) {
        this.movieID = movieID;
        this.categoryID = categoryID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
    
}
