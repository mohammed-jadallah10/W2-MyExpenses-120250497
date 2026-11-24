package com.example.masrofi;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    public ExpenseDao expenseDao ;
    public CategoryDao categoryDao;

    Repository(Application application){
        ExpenseDatabase db = ExpenseDatabase.getInstance(application);
        expenseDao = db.expenseDao();
        categoryDao = db.categoryDao();
    }

    void insertExpense(Expense expense){
        ExpenseDatabase.IO.execute(() ->{
            expenseDao.insertExpense(expense);
        });
    }

    void deleteExpense(Expense expense){
        ExpenseDatabase.IO.execute(() ->{
            expenseDao.deleteExpense(expense);
        });
    }
    LiveData<List<Expense>> getByCategory(int categoryId){
        return expenseDao.getByCategory(categoryId);
    }

    void insertCategory(Category category){
        ExpenseDatabase.IO.execute(() ->{
            categoryDao.insertCategory(category);
        });
    }

    void deleteCategory(Category category){
        ExpenseDatabase.IO.execute(() ->{
            categoryDao.deleteCategory(category);
        });
    }

    LiveData<List<CategoryTotal>> getCategoriesWithTotal(){
        return categoryDao.getCategoriesWithTotal();
    }
}
