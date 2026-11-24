package com.example.masrofi;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "expenses", foreignKeys = @ForeignKey(entity = Category.class , parentColumns = {"id"} ,
childColumns = {"category_id"} , onDelete = ForeignKey.CASCADE), indices = @Index("category_id"))
public class Expense {
    @PrimaryKey(autoGenerate = true)
    private int expenseId;
    private String title ;
    private double amount ;
    private long createdAt;
    @ColumnInfo(name = "category_id")
    private int categoryId ;

    public Expense(String title, double amount, long createdAt, int categoryId) {
        this.title = title;
        this.amount = amount;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
