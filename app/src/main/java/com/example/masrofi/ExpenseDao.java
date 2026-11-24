package com.example.masrofi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ExpenseDao {
    @Insert
    void insertExpense(Expense expense);

    @Delete
    void deleteExpense(Expense expense);

    @Query("Select * from expenses Where category_id = :categoryId" )
    LiveData<List<Expense>> getByCategory(int categoryId);

    @Query("Select COALESCE(SUM(amount) , 0) From expenses") // البونص
    LiveData<Double> getAllExpenses();
}
