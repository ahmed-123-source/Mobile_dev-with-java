package tn.esprit.tourbuddy.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import tn.esprit.tourbuddy.entity.Claims;
import tn.esprit.tourbuddy.entity.Publication;
import tn.esprit.tourbuddy.R;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {

    private List<Publication> publications;
    private OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public PublicationAdapter(List<Publication> publications,OnDeleteClickListener deleteClickListener) {
        this.publications = publications;
        this.deleteClickListener = deleteClickListener;

    }

    @Override
    public PublicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publication_item, parent, false);
        Log.d("list",publications.toString());

        return new PublicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublicationViewHolder holder, int position) {
        Publication publication = publications.get(position);
        holder.textViewTitle.setText(publication.getTitre());
        holder.textViewContent.setText(publication.getContenu());
        // Load and display the image using Glide (you can replace it with your preferred image loading library)
        Glide.with(holder.itemView.getContext())
                .load(publication.getImageP())
                .placeholder(R.drawable.placeholder_image) // Set a placeholder image or use a default image// Set an error image to display in case of failure
                .into(holder.pubImageView);
        holder.deleteButton.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return publications.size();
    }
    public List<Publication> getPublications() {
        return publications;
    }
    // Update the adapter's data and notify the dataset change
    public void setPublications(List<Publication> publications) {
        this.publications = publications;
        notifyDataSetChanged();
    }

    static class PublicationViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewContent;
        ImageView pubImageView;
        Button deleteButton;


        public PublicationViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.pubNameTextView);
            textViewContent = itemView.findViewById(R.id.pubDescriptionTextView);
            pubImageView = itemView.findViewById(R.id.pubImageView);
            deleteButton = itemView.findViewById(R.id.deleteButton);

        }
    }
}
