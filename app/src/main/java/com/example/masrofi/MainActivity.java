package com.example.masrofi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv ;
    EditText categoryName , categoryBudget ;
    CategoryAdapter adapter ;
    MyViewModel viewModel;

    Button insert_btn ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         viewModel= new ViewModelProvider(this).get(MyViewModel.class);
        rv = findViewById(R.id.recycler);
        categoryName = findViewById(R.id.category_name);
        categoryBudget = findViewById(R.id.category_budget);
        insert_btn = findViewById(R.id.insert_btn);
        adapter = new CategoryAdapter(this ::intentToExpense , this::deleteItem);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        viewModel.getCategoriesWithTotal().observe(this , adapter ::setCategories);
        insert_btn.setOnClickListener(v -> {addCategory();});

        viewModel.getAllExpenses().observe(this , total ->{ // البونص
                 setTitle("Total : " +total);
        });

    }





     public  void deleteItem(CategoryTotal category ){

        Category c = new Category(category.name , category.budget);
        c.setId(category.id);
        new AlertDialog.Builder(this).setTitle("Delete Category").setMessage("All expenses within the category will be deleted")
                .setPositiveButton("Yes" ,
                ((dialog, which) -> {
                    viewModel.deleteCategory(c);
                })).setNegativeButton("No" , null).show();

    }

    void addCategory(){
        String category_name = categoryName.getText().toString().trim();
        double category_budget = Double.parseDouble(categoryBudget.getText().toString().trim());
        Category c = new Category(category_name , category_budget);
        viewModel.insertCategory(c);

        categoryName.setText("");
        categoryBudget.setText("");

    }

   private void intentToExpense(CategoryTotal category){
        Intent i = new Intent(MainActivity.this , ExpensesActivity.class);
        i.putExtra("id" ,category.id);
        i.putExtra("name" , category.name);
        startActivity(i);
    }



}