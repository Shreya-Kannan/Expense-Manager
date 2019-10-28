package com.example.expensemanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText e_title,amount,doe;
    Button btnAdd,btnView;
    DatabaseHelper myDB;
    String cat;
    Spinner category;
    Calendar calendar;
    Date dateObject;
    EditText mdob_et;
    int day,month,year;
    float b;
    float amtleft;
    BudgetDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        getSupportActionBar().setTitle("Add Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Calendar calendar  = Calendar.getInstance();

        mdob_et=(EditText)findViewById(R.id.doe);
        mdob_et.setOnClickListener(mClickListener);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);


        category = (Spinner) findViewById(R.id.etLastName);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(adapter);
        category.setOnItemSelectedListener(this);


        e_title = (EditText) findViewById(R.id.etFirstName);
        amount = (EditText) findViewById(R.id.amount);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);
        category =findViewById(R.id.etLastName);

        DB = new BudgetDB(this);
        final Cursor data = DB.getListContents();
        final int numRows = data.getCount();



        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,ViewListContents.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expense_title = e_title.getText().toString();
                int val = Integer.parseInt( amount.getText().toString() );
                String date1= mdob_et.getText().toString();
                if(expense_title.length() != 0 && cat.length() != 0 && date1.length()!=0 ){
                    AddData(expense_title,cat, val, date1);
                    e_title.setText("");
                    amount.setText("");
                    mdob_et.setText("");

                }else{
                    Toast.makeText(Main2Activity.this,"You must put something in the text field!",Toast.LENGTH_LONG).show();
                }
                if(numRows!=0) {
                    int i = 0;
                    while (data.moveToNext()) {
                        b=  data.getFloat(1);
                    }

                    String bu= Float.toString(b);
                    amtleft = b - myDB.sumExpenses();
                    String al = Float.toString(amtleft);
                    if (amtleft < 0) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                        builder.setCancelable(true);
                        builder.setTitle("Warning: Budget Exceeded");
                        builder.setMessage("The Budget you set has been exceeded.");
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                        builder.show();


                    }
                }
            }
        });

    }

    public void AddData(String e_title,String cat, int amt, String dateofe ){
        boolean insertData = myDB.addData(e_title,cat,amt, dateofe);


        if(insertData==true ){
            Toast.makeText(Main2Activity.this,"Successfully Entered Data!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Main2Activity.this,"Something went wrong :(.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cat = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    View.OnClickListener mClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DateDialog();
            String dob_var= mdob_et.getText().toString();
            System.out.println(dob_var);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dateObject = formatter.parse(dob_var);
                System.out.println(dateObject);
                //Log.e("date is ", dateObject+"");

            } catch (ParseException e) {

                e.printStackTrace();
            }
        }
    };
    public void DateDialog(){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                int month1= monthOfYear+1;
                mdob_et.setText(dayOfMonth + "/" + month1 + "/" + year);

            }
        };
        DatePickerDialog dpDialog=new DatePickerDialog(Main2Activity.this, listener, year, month, day);
        dpDialog.show();
    }
}
