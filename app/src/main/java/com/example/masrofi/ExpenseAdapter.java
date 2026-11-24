package com.example.masrofi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    public interface ExpenseAction{
        void run(Expense expense);
    }

    List<Expense> expenses = new ArrayList<>();
    private ExpenseAction onLongClick;

    public ExpenseAdapter(ExpenseAction onLongClick) {
        this.onLongClick = onLongClick;
    }

    public void setExpenses(List<Expense> newExpenses){
        this.expenses = newExpenses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpenseAdapter.ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_shape , parent , false);
        return new ExpenseHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseHolder holder, int position) {
        Expense e = expenses.get(position);
        holder.description.setText(e.getTitle());
        holder.amount.setText(String.valueOf(e.getAmount()));
        holder.itemView.setOnLongClickListener(v -> {
            onLongClick.run(e);
            return true;
        });


    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public static class ExpenseHolder extends RecyclerView.ViewHolder {
        TextView description , amount ;
        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.expenseDescription);
            amount = itemView.findViewById(R.id.expenseAmount);
        }
    }
}
