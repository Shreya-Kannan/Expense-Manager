package com.example.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Exp.db";
    public static final String TABLE_NAME = "expdat";
    public static final String COL1 = "ID";
    public static final String COL2 = "Expense_title";
    public static final String COL3 = "Category";
    public static final String COL4 = "Amount";
    public static final String COL5 = "Date_of_Expense";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " Expense_title TEXT, Category TEXT, Amount INTEGER, Date_of_Expense TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String etitle, String category, int amount, String doe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, etitle);
        contentValues.put(COL3, category);
        contentValues.put(COL4, amount);
        contentValues.put(COL5, doe);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //query for 1 week repeats
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";

        db.execSQL(query);
    }

    public int sumExpenses()
    {
        int total = 0;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COL4 + ") as Total FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()){

            total = cursor.getInt(cursor.getColumnIndex("Total"));
        }

        return total;
    }

    public int sumCategory(String cat)
    {
        int total = 0;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COL4 + ") as Total FROM " + TABLE_NAME + " WHERE "
                +COL3 + " = '" + cat + "'", null);

        if (cursor.moveToFirst()){

            total = cursor.getInt(cursor.getColumnIndex("Total"));
        }

        return total;
    }

   public int distyears(int year)
    {
        int total=0;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(" + COL4 + ") as Total FROM " + TABLE_NAME + " WHERE "
                +COL5 + " LIKE '%" + year + "'", null);

        if (cursor.moveToFirst()){

            total = cursor.getInt(cursor.getColumnIndex("Total"));
        }

        return total;
    }

    public int getamount(String date)
    {
        int total=0;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(" + COL4 + ") as Total FROM " + TABLE_NAME + " WHERE "
                +COL5 + " = '" + date + "'", null);

        if (cursor.moveToFirst()){

            total = cursor.getInt(cursor.getColumnIndex("Total"));
        }

        return total;
    }

    public int getmonthamount(String month)
    {
        int total=0;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(" + COL4 + ") as Total FROM " + TABLE_NAME + " WHERE "
                +COL5 + " LIKE '%" + month  +"%'", null);

        if (cursor.moveToFirst()){

            total = cursor.getInt(cursor.getColumnIndex("Total"));
        }

        return total;
    }


}
