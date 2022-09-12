package android.julian.mobileappdev.DAO;

import android.julian.mobileappdev.Entity.Assessment;
import android.julian.mobileappdev.Entity.Course;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("SELECT * FROM assessments WHERE courseID = :courseID")
    List<Assessment> getAssessmentsFromCourse(int courseID);

    @Query("SELECT * FROM assessments ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();
}
