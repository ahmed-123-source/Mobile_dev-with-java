package com.example.commande_roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.os.AsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private CommandeDao commandeDao;

    private EditText editTextNomUtilisateur;
    private EditText editTextDateCommande;
    private EditText editTextNomProduit;
    private EditText editTextPrixProduit;
    private EditText editTextQuantite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=AppDatabase.getInstance(this);
        commandeDao=database.commandeDao();

        // Initialisation de la base de données
       // database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();

        // Récupé ration des références des champs du formulaire
        editTextNomUtilisateur = findViewById(R.id.editTextNomUtilisateur);
        editTextDateCommande = findViewById(R.id.editTextDateCommande);
        editTextNomProduit = findViewById(R.id.editTextNomProduit);
        editTextPrixProduit = findViewById(R.id.editTextPrixProduit);
        editTextQuantite = findViewById(R.id.editTextQuantite);
    }

    // Méthode pour ajouter une commande à partir du formulaire
    private void ajouterCommandeDepuisFormulaire() {
        String nomUtilisateur = editTextNomUtilisateur.getText().toString();
        String dateCommande = editTextDateCommande.getText().toString();
        String nomProduit = editTextNomProduit.getText().toString();
        double prixProduit = Double.parseDouble(editTextPrixProduit.getText().toString());
        int quantite = Integer.parseInt(editTextQuantite.getText().toString());

        // Crée un objet Commande avec les données du formulaire
        Commande nouvelleCommande = new Commande(nomUtilisateur, dateCommande, nomProduit, prixProduit, quantite);

        // Utilise AsyncTask pour effectuer l'insertion sur un thread différent
        new InsertCommandeAsyncTask(database).execute(nouvelleCommande);
    }

    private static class InsertCommandeAsyncTask extends AsyncTask<Commande, Void, Void> {
        private AppDatabase database;

        // Constructeur qui prend la référence à la base de données
        public InsertCommandeAsyncTask(AppDatabase database) {
            this.database = database;
        }

        @Override
        protected Void doInBackground(Commande... commandes) {
            // Effectue l'insertion sur un thread différent
            database.commandeDao().insert(commandes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Ajoute une notification ou une autre action si nécessaire après l'insertion
        }
    }



    // Méthode pour lire toutes les commandes depuis la base de données
    private void lireToutesLesCommandes() {
        // Utilise AsyncTask pour effectuer la lecture sur un thread différent
        new AsyncTask<Void, Void, List<Commande>>() {
            @Override
            protected List<Commande> doInBackground(Void... voids) {
                // Utilise le DAO pour obtenir la liste des commandes
                return database.commandeDao().getAllCommandes();
            }

            @Override
            protected void onPostExecute(List<Commande> listeCommandes) {
                super.onPostExecute(listeCommandes);
                // Fais quelque chose avec la liste de commandes, comme l'afficher dans une vue
                for (Commande commande : listeCommandes) {
                    // Affiche chaque commande dans la console, par exemple
                    System.out.println("Commande - ID: " + commande.getId() +
                            ", Nom Utilisateur: " + commande.getNomUtilisateur() +
                            ", Date Commande: " + commande.getDateCommande() +
                            ", Nom Produit: " + commande.getNomProduit() +
                            ", Prix Produit: " + commande.getPrixProduit() +
                            ", Quantité: " + commande.getQuantite());
                }
            }

        }.execute();


    }



    // Méthode pour remplir le formulaire pour l'édition
    private void remplirFormulairePourEdition(int commandeId) {
        Commande commande = database.commandeDao().getCommandeById(commandeId);

        editTextNomUtilisateur.setText(commande.getNomUtilisateur());
        editTextDateCommande.setText(commande.getDateCommande());
        editTextNomProduit.setText(commande.getNomProduit());
        editTextPrixProduit.setText(String.valueOf(commande.getPrixProduit()));
        editTextQuantite.setText(String.valueOf(commande.getQuantite()));

        // Affiche le formulaire pour que l'utilisateur puisse le modifier
    }

    // Méthode appelée lorsqu'on clique sur le bouton d'ajout
    public void onClickAjouterCommande(View view) {
        ajouterCommandeDepuisFormulaire();
    }

    // Méthode appelée lorsqu'on clique sur le bouton de lecture
    public void onClickLireCommandes(View view) {
        lireToutesLesCommandes();
        Intent intent = new Intent(MainActivity.this, ListeCommandesActivity.class);
        startActivity(intent);

    }


}
