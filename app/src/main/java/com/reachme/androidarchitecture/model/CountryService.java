package com.reachme.androidarchitecture.model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Single;

public class CountryService {

    public static final String BASE_URL="https://restcountries.eu/rest/v2/";

     private CountryAPI api;

    public CountryService(){

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api= retrofit.create(CountryAPI.class);
    }


       public Single<List<Country>> getCountries(){
        return api.getCountries();
       }


}
