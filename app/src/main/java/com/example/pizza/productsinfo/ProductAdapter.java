package com.example.pizza.productsinfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizza.R;
import com.example.pizza.homescreen.CustomerHandler;
import com.example.pizza.models.Customer;
import com.example.pizza.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;
    private ProductHandler handler;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }
    public void setOnCustomerClickListener ( ProductHandler listener){
        this.handler = listener;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        holder.tvProductName.setText(products.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.onclick(products.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends  RecyclerView.ViewHolder {
        TextView tvProductName;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
        }
    }
}
