package com.reachme.androidarchitecture.MVP;

import android.util.Log;

import com.reachme.androidarchitecture.model.Country;
import com.reachme.androidarchitecture.model.CountryService;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.schedulers.Schedulers;

public class CountriesPresenter {

    private CountryService service;
    private fetchCountryInterface countryInterface;
    //private View view;


    public CountriesPresenter(fetchCountryInterface view) {
        this.service= new CountryService();
        this.countryInterface=view;
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
                        countryInterface.showErrorView();
                    }

                    @Override
                    public void onNext(List<Country> value) {
                        Log.d("TAG", "In onNext()");
                        List<String> countryNames = new ArrayList<>();
                        for(Country country: value) {
                            countryNames.add(country.conuntryName);
                        }
                        //mvcView.setValues(countryNames);
                        countryInterface.setValues(countryNames);
                    }
                });

    }



    public interface fetchCountryInterface{
        void setValues(List<String> countryNames);
        void showErrorView();
    }

}
