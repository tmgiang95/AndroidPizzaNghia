package com.example.pizza.homescreen;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.pizza.BaseActivity;
import com.example.pizza.R;
import com.example.pizza.login.MainActivity;
import com.example.pizza.models.Customer;
import com.example.pizza.productsinfo.ProductManagerActivity;
import com.example.pizza.utils.APIClient;
import com.example.pizza.utils.APIInterface;
import com.facebook.accountkit.AccountKit;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pizza.login.MainActivity.USER_INFORMATION;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvUsername;
    TextView tvUserphone;
    private RecyclerView rvCustomer;
    private ArrayList<Customer> customers = new ArrayList<>();
    private CustomerAdapter adapter;
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        configNavigation();
        Customer customer = (Customer) getIntent().getSerializableExtra(USER_INFORMATION);
        tvUsername.setText(customer.getName());
        tvUserphone.setText(customer.getPhone());

        rvCustomer = findViewById(R.id.rvCustomer);
        adapter = new CustomerAdapter(customers);
        adapter.setOnCustomerClickListener(new CustomerHandler() {
            @Override
            public void onclick(Customer customer) {
                DialogCash dialogCash = new DialogCash(HomeActivity.this,customer);
                dialogCash.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        rvCustomer.setLayoutManager(layoutManager);
        rvCustomer.setAdapter(adapter);




        doGetData();
    }
    private void doGetData() {
        showProgressDialog();
        Call<List<Customer>> call = apiInterface.doGetListCustomers();
        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                customers.addAll(response.body());
                adapter.notifyDataSetChanged();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                Log.d("giangtm1", "DoGetAllCustomer - onFailure: " + t.getMessage());
            }
        });
    }

    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        tvUsername = view.findViewById(R.id.username);
        tvUserphone = view.findViewById(R.id.userphone);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_logout:
                AccountKit.logOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
                break;
            case R.id.nav_product:
                startActivity(new Intent(getBaseContext(), ProductManagerActivity.class));
            default:
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
