package com.carboni.cinebuff.network;

import com.carboni.cinebuff.model.Person;
import com.carboni.cinebuff.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericcarboni on 10/18/16.
 */

public class PersonFetcher {
    private String query;

    public PersonFetcher(String input) {
        List<Result> result;
        query = input;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    TMdbAPI api = retrofit.create(TMdbAPI.class);
    Call<Person> call = api.searchPerson(query);

}
