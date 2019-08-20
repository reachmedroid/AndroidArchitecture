package com.reachme.androidarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.reachme.androidarchitecture.MVC.MVCActivity;
import com.reachme.androidarchitecture.MVP.MVPActivity;
import com.reachme.androidarchitecture.MVVM.MVVMActivity;

public class ArchitectureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architecture);
    }

    public void onMVCActivity(View view){
        startActivity(MVCActivity.getIntent(this));
    }

    public void onMVPActivity(View view){
        startActivity(MVPActivity.getIntent(this));
    }
    public void onMVVMActivity(View view){
        startActivity(MVVMActivity.getIntent(this));
    }
}
