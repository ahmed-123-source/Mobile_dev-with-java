package tn.esprit.tourbuddy.ui.forum;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Publication;
import java.util.List;

public class ForumViewModel extends ViewModel {

    private LiveData<List<Publication>> publications;

    public ForumViewModel() {
        // This constructor can be empty if initialization is not needed
    }

    public ForumViewModel(Context appContext) {
        AppDataBase db = AppDataBase.getInstance(appContext);
        publications = db.publicationDao().getAllPublications();
    }

    public LiveData<List<Publication>> getPublications() {
        return publications;
    }
}
