package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent= new Intent(MainActivity.this, Main4Activity.class);
        startActivity(intent);
/*
        name =(EditText)findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        login=(Button) findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });*/


    }

    private void validate(String username, String upassword){

        if((username.equals("Shreya")) && (upassword.equals("1234"))){
            Intent intent= new Intent(MainActivity.this, Main4Activity.class);
            startActivity(intent);
        }
    }
}
