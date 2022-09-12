package android.julian.mobileappdev.Database;

import android.content.Context;
import android.julian.mobileappdev.DAO.AssessmentDAO;
import android.julian.mobileappdev.DAO.CourseDAO;
import android.julian.mobileappdev.DAO.TermDAO;
import android.julian.mobileappdev.Entity.Assessment;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.Entity.Term;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Term.class, Course.class, Assessment.class}, version=6, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract TermDAO termDAO();

    public abstract CourseDAO courseDAO();

    public abstract AssessmentDAO assessmentDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "schedulerDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}