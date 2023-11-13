package com.arvind.marketmakerrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {
    EditText editTextTextEmailAddress2, editTextTextPassword2;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextTextEmailAddress2.getText().toString().equals("melek") && (editTextTextEmailAddress2.getText().toString().equals("melek"))) {
                    Toast.makeText(TestActivity.this, "Admin Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                    startActivity(intent);

                }

            }
        });
    }
}