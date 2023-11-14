package tn.esprit.tourbuddy.ui.forum;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.adapter.PublicationAdapter;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Claims;
import tn.esprit.tourbuddy.entity.Publication;
import tn.esprit.tourbuddy.ui.claims.ClaimsFragment;
import tn.esprit.tourbuddy.ui.claims.ClaimsViewModel;

public class ForumFragment extends Fragment {

    private RecyclerView recyclerView;
    private PublicationAdapter adapter;
    private ForumViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PublicationAdapter(new ArrayList<>(),this::onDeleteClick);
        recyclerView.setAdapter(adapter);

        Button btnAddPublication = view.findViewById(R.id.btn_add_publication);

            // Navigation logic to add new publication
        btnAddPublication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                    navController.navigate(R.id.nav_new_publication);
                }
            });

        ForumViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ForumViewModel.class);

        fetchDataFromDatabase();
        return view;
    }

    private void fetchDataFromDatabase() {
        new AsyncTask<Void, Void, List<Publication>>() {
            @Override
            protected List<Publication> doInBackground(Void... voids) {
                // Fetch products from the Room database

                return AppDataBase.getInstance(requireContext()).publicationDao().ListPublications();

            }

            @Override
            protected void onPostExecute(List<Publication> pubList) {
                super.onPostExecute(pubList);
                // Update the RecyclerView with the fetched data
                adapter.setPublications(pubList);
                adapter.notifyDataSetChanged();
                Log.d("",pubList.toString());
            }
        }.execute();
    }


    private void onDeleteClick(int position) {
        Publication pubToDelete = adapter.getPublications().get(position);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDataBase.getInstance(requireContext()).publicationDao().supprimerPublication(pubToDelete);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // Rafraîchir la liste après la suppression

                fetchDataFromDatabase();
            }
        }.execute();
    }
}
