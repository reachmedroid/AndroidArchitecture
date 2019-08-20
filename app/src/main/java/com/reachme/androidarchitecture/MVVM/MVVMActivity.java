package com.reachme.androidarchitecture.MVVM;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.reachme.androidarchitecture.R;

import java.util.ArrayList;
import java.util.List;

public class MVVMActivity extends AppCompatActivity {

    private CountriesModelView countriesModelView;
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter adapter;
    private ProgressBar progressBar;
    private TextView errorTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle("MVVM Activity");

        progressBar = findViewById(R.id.progress);
        errorTxt= findViewById(R.id.errorText);

        listView= findViewById(R.id.listView);
        adapter= new ArrayAdapter(this,R.layout.row_layout,R.id.listText,list );
        listView.setAdapter(adapter);

        countriesModelView = ViewModelProviders.of(this).get(CountriesModelView.class);


        observerViewModels();

    }

    private void observerViewModels(){

        /*countriesModelView.getCountriesLiveData().observe(this,countries->{
            if(countries!=null){
            Log.d("TAG", "In getCountriesLiveData() "+countries.size());

            list.clear();
            list.addAll(countries);
            adapter.notifyDataSetChanged();
            listView.invalidate();

          *//* progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            errorTxt.setVisibility(View.GONE);*//*
        }
        });*/

        countriesModelView.getCountriesLiveData().observe(this, countries -> {
            if(countries != null) {
                Log.d("TAG", "In getCountriesLiveData() "+countries.size());

                list.clear();
                list.addAll(countries);
                adapter.notifyDataSetChanged();
                listView.invalidate();

          /* progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                errorTxt.setVisibility(View.GONE);*/
            } else {
                Log.d("TAG", "In getCountriesLiveData() Error");
                listView.setVisibility(View.GONE);
            }
        });


        countriesModelView.getCountriesErrorFlag().observe(this,error->{
            Log.d("TAG", "In getCountriesErrorFlag() "+error);

            if(error){
                Toast.makeText(this, "error in fetching countries",Toast.LENGTH_SHORT)
                        .show();

                /*  progressBar.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                    errorTxt.setVisibility(View.VISIBLE);*/
            }
        });

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVVMActivity.class);
    }
}
