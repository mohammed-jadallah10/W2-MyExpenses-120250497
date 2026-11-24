package com.example.masrofi;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insertCategory(Category category);

    @Delete
    void deleteCategory(Category category);


    @Query("SELECT categories.id, categories.name, categories.budget, " +
            "COALESCE(SUM(expenses.amount), 0) AS total " +
            "FROM categories " +
            "LEFT JOIN expenses ON expenses.category_id = categories.id " +
            "GROUP BY categories.id")
    LiveData<List<CategoryTotal>> getCategoriesWithTotal();
}
