package com.example.masrofi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Category.class , Expense.class} , version = 2 , exportSchema = false)
public abstract class ExpenseDatabase extends RoomDatabase {
    public abstract ExpenseDao expenseDao();
    public abstract CategoryDao categoryDao();

    private static volatile ExpenseDatabase Instance ;
    private static final int Num_Of_Threads = 4 ;
    static final ExecutorService IO = Executors.newFixedThreadPool(Num_Of_Threads);

    public static synchronized ExpenseDatabase getInstance(Context context){
        if (Instance == null){
            Instance = Room.databaseBuilder(context.getApplicationContext() , ExpenseDatabase.class ,
                    "expenses.db").fallbackToDestructiveMigration()
                    .build();
        }
        return Instance;
    }


}
