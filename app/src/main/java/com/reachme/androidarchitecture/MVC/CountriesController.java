package com.reachme.androidarchitecture.MVC;

import android.util.Log;

import com.reachme.androidarchitecture.model.Country;
import com.reachme.androidarchitecture.model.CountryService;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.schedulers.Schedulers;

public class CountriesController {

    private MVCActivity mvcView;
    private CountryService service;


    public CountriesController(MVCActivity view) {
    this.mvcView= view;
    this.service= new CountryService();
    fetchCountries();
    }


    private void fetchCountries(){
       service.getCountries()
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<List<Country>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TAG", "In onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "In onError()");
                        mvcView.showErrorView();
                    }

                    @Override
                    public void onNext(List<Country> value) {
                        Log.d("TAG", "In onNext()");
                        List<String> countryNames = new ArrayList<>();
                        for(Country country: value) {
                            countryNames.add(country.conuntryName);
                        }
                        mvcView.setValues(countryNames);
                    }
                });

    }
}
