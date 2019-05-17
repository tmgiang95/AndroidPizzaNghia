package com.example.pizza.homescreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza.R;
import com.example.pizza.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private List<Customer> customers = new ArrayList<>();
    private CustomerHandler handler;

    public CustomerAdapter(List<Customer> customers) {
        this.customers = customers;
    }
    public void setOnCustomerClickListener ( CustomerHandler listener){
        this.handler = listener;
    }
    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_customer,parent,false);

        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, final int position) {
            holder.tvCustomerName.setText(customers.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handler.onclick(customers.get(position));
                }
            });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    class CustomerViewHolder extends  RecyclerView.ViewHolder {
        TextView tvCustomerName;
        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
        }
    }
}
