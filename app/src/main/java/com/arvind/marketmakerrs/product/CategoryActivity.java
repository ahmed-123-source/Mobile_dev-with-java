package com.arvind.marketmakerrs.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arvind.marketmakerrs.LoginActivity;
import com.arvind.marketmakerrs.R;

public class CategoryActivity extends AppCompatActivity {

    ImageView lapIm,fourIm,toysIm,foodIm;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        lapIm=findViewById(R.id.lap);
        fourIm=findViewById(R.id.four);
        toysIm=findViewById(R.id.toys);
        foodIm=findViewById(R.id.food);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lapIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AddActivity.class);
                intent.putExtra("cat", "laptop");
                startActivity(intent);
            }
        });

        fourIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AddActivity.class);
                intent.putExtra("cat", "Furniture");
                startActivity(intent);
            }
        });

        toysIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AddActivity.class);
                intent.putExtra("cat", "toys");
                startActivity(intent);
            }
        });

        foodIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, AddActivity.class);
                intent.putExtra("cat", "food");
                startActivity(intent);
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                // Handle the cart icon click
                Toast.makeText(getApplicationContext(), "This Feature will be added soon !", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_logout:
                // Handle the click for another menu item
                // Replace the following line with the desired action
                startActivity(new Intent(CategoryActivity.this, LoginActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}