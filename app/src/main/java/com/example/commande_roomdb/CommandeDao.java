package com.example.commande_roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommandeDao {

    @Insert
    void insert(Commande commande);

    @Query("SELECT * FROM commande_table")
    List<Commande> getAllCommandes();

    @Query("SELECT * FROM commande_table WHERE id = :commandeId")
    Commande getCommandeById(int commandeId);

    @Update
    void updateCommande(Commande commande);

    @Delete
    void deleteCommande(Commande commande);

    @Query("DELETE FROM commande_table WHERE id = :commandeId")
    void deleteCommandeById(int commandeId);

    // Ajoutez d'autres méthodes CRUD si nécessaire

}

