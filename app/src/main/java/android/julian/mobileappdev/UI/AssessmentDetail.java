package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
import android.widget.TextView;

import java.util.ArrayList;

public class AssessmentDetail extends AppCompatActivity {
    EditText editAssessmentTitle;
    Spinner editAssessmentType;
    EditText editAssessmentDate;
    TextView editAssessmentCourseID;
    int assessmentID;
    Repository repository;
    String assessmentTitle;
    String assessmentType;
    String assessmentEnd;
    int courseID;
    //TODO: Add the rest.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        editAssessmentTitle =findViewById(R.id.editAssessmentTitle);
        editAssessmentType = findViewById(R.id.editAssessmentType);
        editAssessmentDate =findViewById(R.id.editAssessmentEnd);
        editAssessmentCourseID = findViewById(R.id.editAssessmentCourseID);
        assessmentID = getIntent().getIntExtra("assessment_id", -1);
        assessmentTitle = getIntent().getStringExtra("assessment_title");
        assessmentType = getIntent().getStringExtra("assessment_type");
        assessmentEnd = getIntent().getStringExtra("assessment_end");
        courseID = getIntent().getIntExtra("course_id", -1);
        editAssessmentTitle.setText(assessmentTitle);
        editAssessmentCourseID.setText(assessmentTitle);
        editAssessmentDate.setText(assessmentEnd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        ArrayList<Assessment> assessments = repository.getAllAssessments(courseID);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this,assessments);
        assessmentAdapter.setAssessments(assessments);

        Spinner spinner = (Spinner) findViewById(R.id.editAssessmentType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.assessment_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        editAssessmentType.setSelection(adapter.getPosition(assessmentType));
    }

    public void saveAssessment(View view) {
        Assessment assessment;
        if (assessmentID == -1) {
            int id = repository.getAllAssessments(courseID).get(repository.getAllAssessments(courseID).size() -1).getAssessmentID() + 1;
            assessment = new Assessment(id,editAssessmentType.getSelectedItem().toString(), editAssessmentTitle.getText().toString(),  editAssessmentDate.getText().toString(), courseID);
            repository.insertAssessment(assessment);
        } else {
            assessment = new Assessment(assessmentID, editAssessmentType.getSelectedItem().toString(), editAssessmentTitle.getText().toString(),  editAssessmentDate.getText().toString(), courseID);
            repository.updateAssessment(assessment);
        }
        finish();
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

    public void gotoAssessmentList(View view) {
        Intent intent = new Intent(AssessmentDetail.this, AssessmentList.class);
        startActivity(intent);
    }
}
