package com.example.commande_roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "commande_table")
public class Commande {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nomUtilisateur;
    private String dateCommande;
    private String nomProduit;
    private double prixProduit;
    private int quantite;

    // Constructeur par défaut
    public Commande() {
    }

    // Constructeur avec paramètres
    public Commande(String nomUtilisateur, String dateCommande, String nomProduit, double prixProduit, int quantite) {
        this.nomUtilisateur = nomUtilisateur;
        this.dateCommande = dateCommande;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantite = quantite;
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
