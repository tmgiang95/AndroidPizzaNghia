package com.example.pizza.homescreen;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pizza.R;
import com.example.pizza.models.Customer;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

public class DialogCash extends Dialog {
    private Customer customer;
    private TextView tvCustomerName;
    private TextView tvPhone;
    private TextView tvBirthday;
    private TextView tvBalance;
    private TextInputEditText etCash;

    public DialogCash(@NonNull Context context, Customer customer) {
        super(context);
        this.customer = customer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_money);
        tvCustomerName = findViewById(R.id.tvCustomerName);
        tvPhone = findViewById(R.id.tvPhone);
        tvBirthday = findViewById(R.id.tvBirthday);
        tvBalance = findViewById(R.id.tvBalance);
        etCash = findViewById(R.id.cash_et);

        tvCustomerName.setText(customer.getName());
        tvPhone.setText(customer.getPhone());
        tvBirthday.setText(getDate(customer.getBirthday()));
        tvBalance.setText(String.valueOf(customer.getBalance()));
        etCash.requestFocus();

    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd/MM/yyyy", cal).toString();
        return date;
    }
}
