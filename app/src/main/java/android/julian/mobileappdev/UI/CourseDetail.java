package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;

public class CourseDetail extends AppCompatActivity {
    EditText editCourseTitle;
    EditText editCourseStart;
    EditText editCourseEnd;
    int courseID;
    Repository repository;
    String courseTitle;
    String courseStart;
    String courseEnd;
    //TODO: Add the rest.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        editCourseTitle=findViewById(R.id.editCourseTitle);
        editCourseStart=findViewById(R.id.editCourseStart);
        editCourseEnd=findViewById(R.id.editCourseEnd);
        courseID = getIntent().getIntExtra("course_id", -1);
        courseTitle = getIntent().getStringExtra("course_title");
        courseStart = getIntent().getStringExtra("course_start");
        courseEnd = getIntent().getStringExtra("course_end");
        editCourseTitle.setText(courseTitle);
        editCourseStart.setText(courseStart);
        editCourseEnd.setText(courseEnd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        ArrayList<Course> courses = repository.getAllCourses();
        final CourseAdapter courseAdapter = new CourseAdapter(this,courses);
        courseAdapter.setCourses(courses);

    }

    public void saveCourse(View view) {
        Course course;
        if (courseID == -1) {
            int id = repository.getAllTerms().get(repository.getAllTerms().size() -1).getTermID() + 1;
            course = new Course(id, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),"in progress", "Walter White","876-987-3746","walterwhite@school.edu",1);
            repository.insertCourse(course);
        } else {
            course = new Course(courseID, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),"in progress", "Walter White","876-987-3746","walterwhite@school.edu",1);
            repository.updateCourse(course);
        }
        Intent intent = new Intent(CourseDetail.this, CourseList.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coursedetail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
