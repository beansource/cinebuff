package com.carboni.cinebuff.network;

import com.carboni.cinebuff.interactors.SearchPersonInteractor;
import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.model.Result;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ericcarboni on 10/4/16.
 */

public interface TMdbAPI {
    String API_KEY = "4dd1ed8cbc9fe2a51c02805bddc7d390";

    @GET("search/person?api_key=" + API_KEY)
    Call<Person> searchPerson(@Query("query") String query);

    @GET("search/person?api_key=" + API_KEY)
    Call<List<Result>> listPerson(@Query("query") String query);

    @GET("discover/movie?api_key=" + API_KEY)
    Call getMovies(@Query("with_people") String query);

    /*
    Testing purposes
     */
    @GET("search/person?api_key=" + API_KEY + "&query=brad%20pitt&language=en")
    Call<ResponseBody> getAll();

}