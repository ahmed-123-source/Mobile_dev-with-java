package com.example.reclamation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reclamation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);
        EditText rec = findViewById(R.id.rec);
        EditText reference = findViewById(R.id.reference);
        Button button = findViewById(R.id.button);
        Database db = new Database(getApplicationContext(),"thp",null,1);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Rec = rec.getText().toString();
                String Reference = reference.getText().toString();
                db.addReclamation(Rec,Reference);
                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Reclamation.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}