package com.arvind.marketmakerrs.product;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.arvind.marketmakerrs.R;

import java.util.ArrayList;

public class RecuAdapter extends RecyclerView.Adapter<RecuAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList book_id, book_title, book_author, book_pages;

    RecuAdapter(Activity activity, Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author,
                ArrayList book_pages){
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_reciept_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

           // holder.product_img.setImageResource(R.drawable.laptooop);
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        // holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));

        //Recyclerview onClickListener



    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;
        ImageView product_img;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
           // book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
           // book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
          //  product_img=itemView.findViewById(R.id.productImageView);
            //Animate Recyclerview
           // Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //mainLayout.setAnimation(translate_anim);
        }

    }

}
