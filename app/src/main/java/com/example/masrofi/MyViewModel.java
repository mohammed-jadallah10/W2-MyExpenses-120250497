package com.example.masrofi;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Repository repository ;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);

    }
    void insertExpense(Expense expense){
            repository.insertExpense(expense);
    }

    void deleteExpense(Expense expense){
            repository.deleteExpense(expense);
    }
    LiveData<List<Expense>> getByCategory(int categoryId){
        return repository.getByCategory(categoryId);
    }

    void insertCategory(Category category){

            repository.insertCategory(category);

    }

    void deleteCategory(Category category){

            repository.deleteCategory(category);
    }

    LiveData<List<CategoryTotal>> getCategoriesWithTotal(){
        return repository.getCategoriesWithTotal();
    }

    LiveData<Double> getAllExpenses(){
        return repository.getAllExpenses();
    }
}
