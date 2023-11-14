package tn.esprit.tourbuddy.ui.forum;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tn.esprit.tourbuddy.R;
import tn.esprit.tourbuddy.dao.PublicationDao;
import tn.esprit.tourbuddy.database.AppDataBase;
import tn.esprit.tourbuddy.entity.Publication;

public class NewPublicationFragment extends Fragment {

    private EditText editTextTitle, editTextContent;
    private Button btnSubmitPublication, btnUploadImage;
    private ImageView imageView;
    private Uri imageUri;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_publication, container, false);

        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextContent = view.findViewById(R.id.edit_text_content);
        btnSubmitPublication = view.findViewById(R.id.btn_add_publication);
        btnUploadImage = view.findViewById(R.id.btn_upload_image);
        imageView = view.findViewById(R.id.image_view); // Make sure this ID is correct

        btnSubmitPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPublication();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        return view;
    }

    private void addNewPublication() {
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();

        if (!title.isEmpty() && !content.isEmpty() && imageUri != null) {
            // Create a new publication
            Publication newPublication = new Publication(title, content, imageUri.toString());

            // Execute database operation in AsyncTask
            new AddPublicationAsyncTask().execute(newPublication);
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields and select an image.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                imageView.setImageURI(imageUri);
                imageView.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // AsyncTask to insert the publication in the background
    private class AddPublicationAsyncTask extends AsyncTask<Publication, Void, Void> {
        @Override
        protected Void doInBackground(Publication... publications) {
            // Access the database and insert the publication
            AppDataBase appDataBase = AppDataBase.getInstance(requireContext());
            PublicationDao publicationDao = appDataBase.publicationDao();
            publicationDao.insererPublication(publications[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // This is where you can handle UI updates or notify the user after the database operation is complete
            // Pop the fragment from the back stack
            getParentFragmentManager().popBackStack();
        }
    }
}
