package com.reachme.androidarchitecture.MVC;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.reachme.androidarchitecture.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MVCActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter adapter;
    private CountriesController controller;
    private ProgressBar progressBar;
    private TextView errorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        setTitle("MVC Activity");
        progressBar = findViewById(R.id.progress);

        errorTxt= findViewById(R.id.errorText);

        controller= new CountriesController(this);



        listView= findViewById(R.id.listView);
        adapter= new ArrayAdapter(this,R.layout.row_layout,R.id.listText,list );
        listView.setAdapter(adapter);


    }
    public void setValues( List<String> values){

        list.clear();
        list.addAll(values);
        adapter.notifyDataSetChanged();
        listView.invalidate();

      /*  progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        errorTxt.setVisibility(View.GONE);*/
    }

    public void showErrorView(){
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        errorTxt.setVisibility(View.VISIBLE);
    }



    public static Intent getIntent(Context context) {
        return new Intent(context, MVCActivity.class);
    }

}
