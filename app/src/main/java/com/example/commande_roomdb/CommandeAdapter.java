package com.example.commande_roomdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.CommandeViewHolder> {

    private List<Commande> commandes;

    // Autres méthodes de l'adaptateur...

    @NonNull
    @Override
    public CommandeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_commande, parent, false);

        return new CommandeViewHolder(itemView);
    }

    public Commande getCommandeAtPosition(int position) {
        return commandes.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandeViewHolder holder, int position) {
        Commande currentCommande = commandes.get(position);

        // Afficher les données de la commande dans les TextViews
        holder.textViewNomUtilisateur.setText("Nom Utilisateur: " + currentCommande.getNomUtilisateur());
        holder.textViewDateCommande.setText("Date Commande: " + currentCommande.getDateCommande());
        holder.textViewNomProduit.setText("Nom Produit: " + currentCommande.getNomProduit());
        holder.textViewPrixProduit.setText("Prix Produit: " + currentCommande.getPrixProduit());
        holder.textViewQuantite.setText("Quantité: " + currentCommande.getQuantite());
    }

    @Override
    public int getItemCount() {
        if (commandes == null) {
            return 0;
        }
        return commandes.size();
    }


    // Autres méthodes de l'adaptateur...

    class CommandeViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNomUtilisateur;
        private TextView textViewDateCommande;
        private TextView textViewNomProduit;
        private TextView textViewPrixProduit;
        private TextView textViewQuantite;

        public CommandeViewHolder(View itemView) {
            super(itemView);
            textViewNomUtilisateur = itemView.findViewById(R.id.textViewNomUtilisateur);
            textViewDateCommande = itemView.findViewById(R.id.textViewDateCommande);
            textViewNomProduit = itemView.findViewById(R.id.textViewNomProduit);
            textViewPrixProduit = itemView.findViewById(R.id.textViewPrixProduit);
            textViewQuantite = itemView.findViewById(R.id.textViewQuantite);
        }
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
        notifyDataSetChanged(); // Notifie le RecyclerView que les données ont changé
    }

    public void removeCommand(int position){
        commandes.remove(position);
        notifyDataSetChanged();
    }



}
