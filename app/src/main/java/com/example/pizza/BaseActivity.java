package com.example.pizza;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizza.homescreen.HomeActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pd = new ProgressDialog(BaseActivity.this);
        pd.setMessage("Đang lấy dữ liệu ...");
    }

    public void showProgressDialog(){
        pd.show();
    }
    public void dismissProgressDialog(){
        pd.dismiss();
    }


}
