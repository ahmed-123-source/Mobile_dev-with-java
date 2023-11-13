package com.arvind.marketmakerrs.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.arvind.marketmakerrs.R;

public class AddActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        String receivedText = intent.getStringExtra("cat");

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        author_input.setText(receivedText);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addProduct(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        Integer.valueOf(pages_input.getText().toString().trim()));
                Intent intent = new Intent(AddActivity.this, MainProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
