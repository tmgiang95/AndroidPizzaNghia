package com.example.pizza.productsinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pizza.BaseActivity;
import com.example.pizza.R;
import com.example.pizza.homescreen.CustomerAdapter;
import com.example.pizza.homescreen.CustomerHandler;
import com.example.pizza.homescreen.DialogCash;
import com.example.pizza.homescreen.HomeActivity;
import com.example.pizza.models.Customer;
import com.example.pizza.models.Product;
import com.example.pizza.utils.APIClient;
import com.example.pizza.utils.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManagerActivity extends BaseActivity {
    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
    private ArrayList<Product> products = new ArrayList<>();
    private RecyclerView rvProduct;
    private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvProduct = findViewById(R.id.rvProduct);
        adapter = new ProductAdapter(products);
        adapter.setOnCustomerClickListener(new ProductHandler() {
            @Override
            public void onclick(Product product) {
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        rvProduct.setLayoutManager(layoutManager);
        rvProduct.setAdapter(adapter);
        doGetData();
    }

    private void doGetData() {
        showProgressDialog();
        Call<List<Product>> call = apiInterface.doGetListProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products.addAll(response.body());
                adapter.notifyDataSetChanged();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("giangtm1", "DoGetAllCustomer - onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
