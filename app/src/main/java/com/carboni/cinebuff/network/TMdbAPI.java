package com.carboni.cinebuff.network;

import com.carboni.cinebuff.BuildConfig;
import com.carboni.cinebuff.model.MovieDetail;
import com.carboni.cinebuff.model.Movies;
import com.carboni.cinebuff.model.Person;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ericcarboni on 10/4/16.
 */

public interface TMdbAPI {
    String API_KEY = BuildConfig.TMDB_API_KEY;

    /*
    Retrieve list of People
     */
    @GET("search/person?api_key=" + API_KEY)
    Call<Person> searchPerson(@Query("query") String query);

    /*
    Retrieve list of Movies
     */
    @GET("discover/movie?api_key=" + API_KEY)
    Call<Movies> getMovies(@Query("with_cast") String query);

    /*
    Retrieve details about a Movie
     */
    @GET("movie/{movie_id}" + "?" + API_KEY)
    Call<MovieDetail> getMovieDetail(@Path("movie_id") String movie_id);

}