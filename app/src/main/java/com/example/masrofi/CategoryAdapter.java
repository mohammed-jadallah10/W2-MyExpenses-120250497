package com.example.masrofi;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    public interface categoryAction{
        void run(CategoryTotal ct);
    }



    List<CategoryTotal> categories = new ArrayList<>();
    private categoryAction onClick ;
    private categoryAction onLongClick ;

    public CategoryAdapter(categoryAction onClick, categoryAction onLongClick) {
        this.onClick = onClick;
        this.onLongClick = onLongClick;
    }

    public void setCategories (List<CategoryTotal> newCategorties){
        this.categories = newCategorties;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_shape , parent , false);
        return new CategoryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, int position) {
        CategoryTotal c = categories.get(position);
        holder.name.setText(c.name);
        holder.total.setText(c.total + "/" + c.budget);
        if (c.total > c.budget){
            holder.total.setTextColor(Color.RED);
        }else {
            holder.total.setTextColor(Color.WHITE);
        }
        holder.itemView.setOnClickListener(v -> {
            onClick.run(c);
        });

        holder.itemView.setOnLongClickListener(v -> {
            onLongClick.run(c);
            return true;
        });

    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryHolder extends RecyclerView.ViewHolder {
        TextView name , total ;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categoryName);
            total = itemView.findViewById(R.id.categoryTotal);
        }
    }
}
