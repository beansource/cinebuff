package com.carboni.cinebuff;

import com.carboni.cinebuff.model.Person;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ericcarboni on 10/4/16.
 */

public interface TMdbAPI {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("search/person?api_key=4dd1ed8cbc9fe2a51c02805bddc7d390")
    Call<Person> searchPerson(@Query("query") String query);

    /*
    Testing purposes
     */
    @GET("search/person?api_key=4dd1ed8cbc9fe2a51c02805bddc7d390&query=brad%20pitt&language=en")
    Call<ResponseBody> getAll();

}