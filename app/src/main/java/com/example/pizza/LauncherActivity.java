package com.example.pizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pizza.homescreen.HomeActivity;
import com.example.pizza.login.MainActivity;
import com.example.pizza.models.Customer;
import com.example.pizza.utils.APIClient;
import com.example.pizza.utils.APIInterface;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pizza.login.MainActivity.USER_INFORMATION;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            Call<List<Customer>> callLoginVerify = apiInterface.doGetVerifyLoginByCustomerID(accessToken.getAccountId());
            callLoginVerify.enqueue(new Callback<List<Customer>>() {
                @Override
                public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                    if (response.body() != null && response.body().get(0).getRole() == 1) {
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        intent.putExtra(USER_INFORMATION, response.body().get(0));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LauncherActivity.this, "You dont have permission to Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<List<Customer>> call, Throwable t) {
                    Toast.makeText(LauncherActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(),MainActivity.class));
                    finish();
                }
            });

        } else {
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }
}
