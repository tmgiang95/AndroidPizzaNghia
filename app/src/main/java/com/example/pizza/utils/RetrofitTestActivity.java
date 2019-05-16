package com.example.pizza.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pizza.R;

public class RetrofitTestActivity extends AppCompatActivity {
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
    }
}
