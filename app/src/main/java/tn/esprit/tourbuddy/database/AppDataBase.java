package tn.esprit.tourbuddy.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.tourbuddy.dao.ClaimsDao;
import tn.esprit.tourbuddy.dao.CommentDao;
import tn.esprit.tourbuddy.dao.PublicationDao;
import tn.esprit.tourbuddy.dao.ResponseDao;
import tn.esprit.tourbuddy.entity.Claims;
import tn.esprit.tourbuddy.entity.Comment;
import tn.esprit.tourbuddy.entity.Publication;
import tn.esprit.tourbuddy.entity.Response;

@Database(entities = {Publication.class, Comment.class , Claims.class , Response.class }, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDataBase.class, "database-name") // Remplacez "database-name" par le nom de votre DB
                            .fallbackToDestructiveMigration() // Ajoutez cette ligne pour gérer les migrations de la base de données
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract ClaimsDao claimsDao();
    public abstract ResponseDao responseDao();
    public abstract PublicationDao publicationDao();
    public abstract CommentDao commentDao();
}
