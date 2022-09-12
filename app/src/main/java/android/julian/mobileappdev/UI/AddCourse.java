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

public class AddCourse extends AppCompatActivity {
    EditText addCourseTitle;
    EditText addCourseStart;
    EditText addCourseEnd;
    String courseTitle;
    String courseStart;
    String courseEnd;
//    String courseStatus;
//    String courseInstName;
//    String courseInstPhone;
//    String courseInstEmail;
    int termID;
    int courseID;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        addCourseTitle =findViewById(R.id.addCourseTitle);
        addCourseStart =findViewById(R.id.addCourseStart);
        addCourseEnd =findViewById(R.id.addCourseEnd);
        courseID = getIntent().getIntExtra("course_id", -1);
        termID = getIntent().getIntExtra("term_id",-1);
        courseTitle=getIntent().getStringExtra("course_name");
        courseStart=getIntent().getStringExtra("course_start");
        courseEnd=getIntent().getStringExtra("course_end");
//        courseStatus=getIntent().getStringExtra("course_status");
//        courseInstName=getIntent().getStringExtra("course_instructor_name");
//        courseInstPhone=getIntent().getStringExtra("course_instructor_phone");
//        courseInstEmail=getIntent().getStringExtra("course_instructor_email");
//        addCourseTitle.setText(courseTitle);
//        addCourseStart.setText(courseStart);
//        addCourseEnd.setText(courseEnd);
//        editCourseStatus.set(courseStatus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repo = new Repository(getApplication());
        ArrayList<Course> courses;
        courses = repo.getAllCourses(termID);
        final CourseAdapter courseAdapter=new CourseAdapter(this, courses);
        courseAdapter.setCourses(courses);
    }

    public void addCourse(View view) {
        Course c;
        if (courseID == -1) {
            int id = repo.getAllCourses(termID).get(repo.getAllCourses(termID).size() -1).getCourseID() + 1;
            c = new Course(id, addCourseTitle.getText().toString(), addCourseStart.getText().toString(), addCourseEnd.getText().toString(), "test","test","test","test",1);
            repo.insertCourse(c);
        } else {
            c = new Course(courseID,  addCourseTitle.getText().toString(), addCourseStart.getText().toString(), addCourseEnd.getText().toString(),"test","test","test","test", 1);
            repo.updateCourse(c);
        }
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_termlist, menu);
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