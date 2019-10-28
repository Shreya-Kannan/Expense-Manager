package com.example.expensemanager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnDelete;

    DatabaseHelper mDatabaseHelper;


    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        getSupportActionBar().setTitle("Delete Expense");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);

                toastMessage("Removed from database");
                Intent intent= new Intent(EditDataActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
