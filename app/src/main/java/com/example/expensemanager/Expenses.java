package com.example.expensemanager;

public class Expenses {

    private String expense_title;
    private String category;
    private int amount;

    public Expenses(String expense_title, String category, int amount) {
        this.expense_title = expense_title;
        this.category = category;
        this.amount = amount;
    }

    public String getExpense_title() {
        return expense_title;
    }

    public void setExpense_title(String expense_title) {
        this.expense_title = expense_title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
