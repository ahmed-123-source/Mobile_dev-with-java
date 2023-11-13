package com.arvind.marketmakerrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arvind.marketmakerrs.databinding.ActivityLoginBinding;
import com.arvind.marketmakerrs.product.CategoryActivity;
import com.arvind.marketmakerrs.product.MainProductActivity;
import com.arvind.marketmakerrs.product.UserProductActivity;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if(email.equals("")||password.equals(""))
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

                else if(email.equals("admin")&&password.equals("admin")){
                    Toast.makeText(LoginActivity.this, "Admin Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(getApplicationContext(), CategoryActivity.class);
                    startActivity(intent);
                }

                else{
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                    if(checkCredentials == true){
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), UserProductActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}