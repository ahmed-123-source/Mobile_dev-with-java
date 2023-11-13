package com.arvind.marketmakerrs.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arvind.marketmakerrs.R;

public class DetailActivity extends AppCompatActivity {
    ImageView im;
    TextView tv1,tv2,tv3;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String pages = intent.getStringExtra("pages");

        im=findViewById(R.id.productImageView);
        if (author.equals("Furniture")){
            im.setImageResource(R.drawable.furniturrre);
        }else if (author.equals("toys")){
            im.setImageResource(R.drawable.tooooys);

        }else if (author.equals("food")){
            im.setImageResource(R.drawable.foooood);

        }else  im.setImageResource(R.drawable.laptooop);


        tv1=findViewById(R.id.productNameTextView);
        tv1.setText(title);

        tv2=findViewById(R.id.productPriceTextView);
        tv2.setText(pages);

        tv3=findViewById(R.id.productDescriptionTextView);
        tv3.setText(author);

        addBtn=findViewById(R.id.addToCartButton);

      addBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              MyDatabaseHelper myDB = new MyDatabaseHelper(DetailActivity.this);
              myDB.addToCart(tv1.getText().toString().trim(),
                      tv3.getText().toString().trim(),
                      Integer.valueOf(tv2.getText().toString().trim()));
              Intent intent = new Intent(DetailActivity.this, UserProductActivity.class);
              startActivity(intent);
          }
      });
    }
}