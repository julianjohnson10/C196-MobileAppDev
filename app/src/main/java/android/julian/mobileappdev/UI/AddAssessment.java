package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Assessment;
import android.julian.mobileappdev.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class AddAssessment extends AppCompatActivity {
    EditText addAssessmentTitle;
    Spinner addAssessmentType;
    EditText addAssessmentEnd;
    String assessmentTitle;
    String assessmentType;
    String assessmentEnd;
    int assessmentID;
    int courseID;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        addAssessmentTitle =findViewById(R.id.addAssessmentTitle);
        addAssessmentType = findViewById(R.id.addAssessmentType);
        addAssessmentEnd =findViewById(R.id.addAssessmentEnd);
        assessmentID = getIntent().getIntExtra("assessment_id", -1);
        assessmentTitle=getIntent().getStringExtra("assessment_title");
        assessmentType=getIntent().getStringExtra("assessment_type");
        assessmentEnd=getIntent().getStringExtra("assessment_end");
        courseID = getIntent().getIntExtra("course_id", -1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repo = new Repository(getApplication());
        ArrayList<Assessment> assessments;
        assessments = repo.getAllAssessments(courseID);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this, assessments);
        assessmentAdapter.setAssessments(assessments);

        Spinner spinner = (Spinner) findViewById(R.id.addAssessmentType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.assessment_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addAssessmentType.setSelection(adapter.getPosition(assessmentType));
    }

    public void addAssessment(View view) {
        Assessment assessment;
        if (assessmentID == -1) {
            int id = repo.getAllAssessments(courseID).get(repo.getAllAssessments(courseID).size() -1).getAssessmentID() + 1;
            assessment = new Assessment(id, addAssessmentType.getSelectedItem().toString(), addAssessmentTitle.getText().toString(), addAssessmentEnd.getText().toString(),2);
            repo.insertAssessment(assessment);
        } else {
            assessment = new Assessment(assessmentID, addAssessmentType.getSelectedItem().toString(), addAssessmentTitle.getText().toString(), addAssessmentEnd.getText().toString(),courseID);
            repo.updateAssessment(assessment);
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