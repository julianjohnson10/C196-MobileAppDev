package android.julian.mobileappdev.UI;

import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.Entity.Term;
import android.julian.mobileappdev.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.courseRV);
        TextView emptyView = findViewById(R.id.empty_view);

        Repository repository=new Repository(getApplication());
        ArrayList<Course> courses= repository.getCoursesFromTerm(TermDetails.termID);
        final CourseAdapter courseAdapter=new CourseAdapter(this, courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseAdapter);
        courseAdapter.setCourses(courses);


        if (courses.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_course_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.courseRV);
        TextView emptyView = findViewById(R.id.empty_view);

        Repository repository = new Repository(getApplication());
        ArrayList<Course> courses = repository.getCoursesFromTerm(TermDetails.termID);
        final CourseAdapter courseAdapter = new CourseAdapter(this, courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseAdapter);
        courseAdapter.setCourses(courses);

        if (courses.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.baseline, menu);
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

    public void gotoAddCourse(View view) {
        Intent i = new Intent(CourseList.this, AddCourse.class);
        startActivity(i);
    }
}