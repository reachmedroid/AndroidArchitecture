package com.reachme.androidarchitecture.MVP;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.reachme.androidarchitecture.MVC.CountriesController;
import com.reachme.androidarchitecture.MVC.MVCActivity;
import com.reachme.androidarchitecture.R;

import java.util.ArrayList;
import java.util.List;

public class MVPActivity extends AppCompatActivity implements CountriesPresenter.fetchCountryInterface {

    private ListView listView;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter adapter;
    private ProgressBar progressBar;
    private TextView errorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setTitle("MVP Activity");

        progressBar = findViewById(R.id.progress);
        errorTxt= findViewById(R.id.errorText);
        new CountriesPresenter(this);

        listView= findViewById(R.id.listView);
        adapter= new ArrayAdapter(this,R.layout.row_layout,R.id.listText,list );
        listView.setAdapter(adapter);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MVPActivity.class);
    }


    @Override
    public void setValues(List<String> countryNames) {
        list.clear();
        list.addAll(countryNames);
        adapter.notifyDataSetChanged();
        listView.invalidate();

      /*  progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        errorTxt.setVisibility(View.GONE);*/
    }

    @Override
    public void showErrorView() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        errorTxt.setVisibility(View.VISIBLE);
    }
}
