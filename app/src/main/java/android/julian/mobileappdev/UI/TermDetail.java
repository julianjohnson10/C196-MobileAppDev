package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.Entity.Term;
import android.julian.mobileappdev.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class TermDetail extends AppCompatActivity {
    EditText editTermName;
    EditText editStartDate;
    EditText editEndDate;
    String termName;
    String startDate;
    String endDate;
    int termID;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        editTermName=findViewById(R.id.editTermName);
        editStartDate=findViewById(R.id.editStartDate);
        editEndDate=findViewById(R.id.editEndDate);
        termID = getIntent().getIntExtra("id", -1);
        termName=getIntent().getStringExtra("name");
        startDate=getIntent().getStringExtra("start");
        endDate=getIntent().getStringExtra("end");
        editTermName.setText(termName);
        editStartDate.setText(startDate);
        editEndDate.setText(endDate);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.recyclerView2);
        Repository repository=new Repository(getApplication());
        ArrayList<Course> courses = repository.getAllCourses();
        final CourseAdapter courseAdapter=new CourseAdapter(this, courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(courseAdapter);
        courseAdapter.setCourses(courses);
    }

    public void saveTerm(View view) {
        Term t;
        if (termID == -1) {
            int id = repo.getAllTerms().get(repo.getAllTerms().size() -1).getTermID() + 1;
            t = new Term(id, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repo.insertTerm(t);
        } else {
            t = new Term(termID, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repo.updateTerm(t);
        }
        Intent intent = new Intent(TermDetail.this, TermList.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_courselist, menu);
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

    public void gotoTermDetails(View view){
        Intent intent = new Intent(TermDetail.this, CourseDetail.class);
        startActivity(intent);
    }

}