package com.reachme.androidarchitecture.model;

import java.util.List;

import retrofit2.http.GET;
import rx.Single;

public interface CountryAPI {


    @GET("all")
    Single<List<Country>>  getCountries();
}
