package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Assessment;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddAssessment extends AppCompatActivity {
    EditText addAssessmentTitle;
    Spinner addAssessmentType;
    EditText addAssessmentDate;
    String assessmentType;
    int assessmentID;
    Repository repo;

    DatePickerDialog.OnDateSetListener assessmentDate;
    final Calendar calendarEnd = Calendar.getInstance();
    String format;
    SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        addAssessmentTitle =findViewById(R.id.addAssessmentTitle);
        addAssessmentType = findViewById(R.id.addAssessmentType);
        addAssessmentDate =findViewById(R.id.addAssessmentDate);
        assessmentID = getIntent().getIntExtra("assessment_id", -1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repo = new Repository(getApplication());
        ArrayList<Assessment> assessments;
        assessments = repo.getAllAssessments();
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this, assessments);
        assessmentAdapter.setAssessments(assessments);

        Spinner spinner = (Spinner) findViewById(R.id.addAssessmentType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.assessment_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addAssessmentType.setSelection(adapter.getPosition(assessmentType));

        format = "MM/dd/yy";
        dateFormat = new SimpleDateFormat(format, Locale.US);

        addAssessmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = addAssessmentDate.getText().toString();
                if(info.equals(""));
                try{
                    calendarEnd.setTime(dateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(AddAssessment.this, assessmentDate, calendarEnd
                        .get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        assessmentDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            calendarEnd.set(Calendar.YEAR,year);
            calendarEnd.set(Calendar.MONTH,monthOfYear);
            calendarEnd.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            addAssessmentDate.setText(dateFormat.format(calendarEnd.getTime()));
        };

    }

    public void addAssessment(View view) {
        Assessment assessment;
        if (assessmentID == -1) {
            int id = repo.getAllAssessments().get(repo.getAllAssessments().size() -1).getAssessmentID() + 1;
            assessment = new Assessment(id, addAssessmentType.getSelectedItem().toString(), addAssessmentTitle.getText().toString(), addAssessmentDate.getText().toString(),CourseDetail.courseID);
            repo.insertAssessment(assessment);
        } else {
            assessment = new Assessment(assessmentID, addAssessmentType.getSelectedItem().toString(), addAssessmentTitle.getText().toString(), addAssessmentDate.getText().toString(),CourseDetail.courseID);
            repo.updateAssessment(assessment);
        }
        finish();
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
}