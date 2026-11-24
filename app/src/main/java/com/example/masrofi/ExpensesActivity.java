package com.example.masrofi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpensesActivity extends AppCompatActivity {
    TextView address ;
    MyViewModel viewModel ;
    EditText description , amount ;
    int categoryId;
    RecyclerView rv ;
    Button insert_expense_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        address = findViewById(R.id.itemAddress);


        Intent i = getIntent();
        String name = i.getStringExtra("name");
        categoryId= i.getIntExtra("id" , 1);

        setTitle(name); // وضع الاسم تبع ال item اللي دخلنا من خلالو في الAction Bar

         viewModel = new ViewModelProvider(this).get(MyViewModel.class);
         rv = findViewById(R.id.so_recycler);
         insert_expense_btn = findViewById(R.id.insert_expense_btn);
         description = findViewById(R.id.expenseDescription);
         amount = findViewById(R.id.expenseAmount);
         ExpenseAdapter adapter = new ExpenseAdapter(this::deleteExpense);
         rv.setLayoutManager(new LinearLayoutManager(this));
         rv.setAdapter(adapter);
         insert_expense_btn.setOnClickListener(v -> {addExpense();});
         viewModel.getByCategory(categoryId).observe(this , adapter::setExpenses);

         FloatingActionButton back_btn = findViewById(R.id.back);
         back_btn.setOnClickListener(v -> {
             finish();
         });













    }

    private void addExpense(){
        String expenseDescription =description.getText().toString().trim();
        double expenseAmount = Double.parseDouble(amount.getText().toString().trim());
        Expense e = new Expense(expenseDescription , expenseAmount , System.currentTimeMillis() , categoryId);
        viewModel.insertExpense(e);

        description.setText("");
        amount.setText("");





    }

    private void deleteExpense(Expense expense){
        new AlertDialog.Builder(this).setTitle("Delete Expense")
                .setPositiveButton("Yes" , ((dialog, which) -> {
                    viewModel.deleteExpense(expense);
                })).setNegativeButton("No" , null).show();

    }
}