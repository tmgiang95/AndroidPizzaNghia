package com.example.pizza.utils;

import com.example.pizza.models.Category;
import com.example.pizza.models.Customer;
import com.example.pizza.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/category-api.php")
    Call<List<Category>> doGetListCategories();

    @POST("/category-api.php")
    Call<List<Category>> doPOSTListCategories(@Body ArrayList<Category> categoryArrayList);

    @PUT("/category-api.php")
    Call<List<Category>> doPUTListCategories(@Body ArrayList<Category> categoryArrayList);

    @DELETE("/category-api.php/{id}")
    Call<String> doDELETECategory(@Path("id") int itemId);

    @GET("/customer-api.php")
    Call<List<Customer>> doGetListCustomers();

    @POST("/customer-api.php")
    Call<List<Customer>> doPOSTListCustomers(@Body ArrayList<Customer> customerArrayList);

    @PUT("/customer-api.php")
    Call<List<Customer>> doPUTListCustomers(@Body ArrayList<Customer> customerArrayList);

    @DELETE("/customer-api.php/{id}")
    Call<String> doDELETECustomer(@Path("id") int itemId);

    @GET("/product-api.php")
    Call<List<Product>> doGetListProducts();

    @POST("/product-api.php")
    Call<List<Product>> doPOSTListProducts(@Body ArrayList<Product> productArrayList);

    @PUT("/product-api.php")
    Call<List<Product>> doPUTListProducts(@Body ArrayList<Product> productArrayList);

    @DELETE("/product-api.php/{id}")
    Call<String> doDELETEProduct(@Path("id") int itemId);

    @GET("login-api.php")
    Call<List<Customer>> doGetVerifyLoginByCustomerID(@Query("customerid") String customerid);


}
