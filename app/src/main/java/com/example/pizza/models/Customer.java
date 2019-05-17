package com.example.pizza.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {http://demo.mozasolution.com/moza-econtent/backend/web/index.php/content/api/dashboard?fbclid=IwAR2WBtKS6A09HXJ8e1jnx1mL6Kg-yeEgMvSXYQJeaNx4_qQLzBgpEHbDBFU
    @SerializedName("customerid")
    private String customerid;
    @SerializedName("name")
    private String name;
    @SerializedName("birthday")
    private int birthday;
    @SerializedName("phone")
    private String phone;
    @SerializedName("address")
    private String address;
    @SerializedName("balance")
    private double balance;
    @SerializedName("role")
    private int role;

    public Customer(String customerid, String name, int birthday, String phone, String address, double balance, int role) {
        this.customerid = customerid;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
