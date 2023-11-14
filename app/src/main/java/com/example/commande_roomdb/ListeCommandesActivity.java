package com.example.commande_roomdb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListeCommandesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommandeAdapter commandeAdapter;

    private CommandeDao commandeDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_commande);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commandeAdapter = new CommandeAdapter();
        recyclerView.setAdapter(commandeAdapter);

        lireToutesLesCommandes();
    }

    private void lireToutesLesCommandes() {
        new AsyncTask<Void, Void, List<Commande>>() {
            @Override
            protected List<Commande> doInBackground(Void... voids) {
                // Utilise le DAO pour obtenir la liste des commandes
                return AppDatabase.getInstance(ListeCommandesActivity.this).commandeDao().getAllCommandes();
            }

            @Override
            protected void onPostExecute(List<Commande> listeCommandes) {
                super.onPostExecute(listeCommandes);
                // Met à jour l'adaptateur avec la nouvelle liste de commandes
                commandeAdapter.setCommandes(listeCommandes);
            }
        }.execute();
    }

    public void onDelete(int id, int pos){
        commandeDao.deleteCommandeById(id);
        commandeAdapter.removeCommand(pos);
    }


}
