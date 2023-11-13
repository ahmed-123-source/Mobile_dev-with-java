package com.arvind.marketmakerrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arvind.marketmakerrs.product.CategoryActivity;
import com.arvind.marketmakerrs.product.UserProductActivity;

public class TestttttActivity extends AppCompatActivity {
    EditText ChampEmail,ChampPassword;
    Button login;
    TextView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testtttt);

        ChampEmail = findViewById(R.id.editTextTextEmailAddress);
        ChampPassword=findViewById(R.id.editTextTextPassword);
        login =findViewById(R.id.button);
        l=findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ChampEmail.getText().toString().equals("melek")&&(ChampPassword.getText().toString().equals("melek"))){
                    Toast.makeText(TestttttActivity.this, "Admin Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), TestActivity.class);
                    startActivity(intent);

                }
            }
        });





    }
}