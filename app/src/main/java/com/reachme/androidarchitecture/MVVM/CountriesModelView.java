package com.reachme.androidarchitecture.MVVM;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reachme.androidarchitecture.model.Country;
import com.reachme.androidarchitecture.model.CountryService;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.schedulers.Schedulers;

public class CountriesModelView extends ViewModel {

    private CountryService service;

    public MutableLiveData<List<String>> getCountriesLiveData() {
        return countriesLiveData;
    }

    public MutableLiveData<Boolean> getCountriesErrorFlag() {
        return countriesErrorFlag;
    }

    private MutableLiveData<List<String>> countriesLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> countriesErrorFlag = new MutableLiveData<>();

    public CountriesModelView() {
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
                        countriesErrorFlag.setValue(true);
                    }

                    @Override
                    public void onNext(List<Country> value) {
                        Log.d("TAG", "In onNext()");
                        List<String> countryNames = new ArrayList<>();
                        for(Country country: value) {
                            countryNames.add(country.conuntryName);
                        }
                        countriesErrorFlag.setValue(false);
                        countriesLiveData.setValue(countryNames);
                    }
                });

    }

}
