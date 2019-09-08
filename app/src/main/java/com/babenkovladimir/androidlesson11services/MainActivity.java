package com.babenkovladimir.androidlesson11services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.babenkovladimir.androidlesson11services.service.ServiceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onServiceClick(View view) {
        startActivity(new Intent(this, ServiceActivity.class));
    }
}
