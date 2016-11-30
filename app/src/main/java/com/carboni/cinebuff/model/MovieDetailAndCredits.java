package com.carboni.cinebuff.model;

/**
 * Created by ericcarboni on 11/29/16.
 */

public class MovieDetailAndCredits {
    public MovieDetail detail;
    public MovieCredits credits;

    public MovieDetailAndCredits(MovieDetail detail, MovieCredits credits) {
        this.detail = detail;
        this.credits = credits;
    }

}
