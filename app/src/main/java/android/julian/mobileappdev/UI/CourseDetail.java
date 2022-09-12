package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
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

public class CourseDetail extends AppCompatActivity {
    EditText editCourseTitle;
    EditText editCourseStart;
    EditText editCourseEnd;
    EditText editInstructorName;
    EditText editInstructorPhone;
    EditText editInstructorEmail;
    public static int courseID;
    Repository repository;
    String courseTitle;
    String courseStart;
    String courseEnd;
    int termID;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar calendarStart = Calendar.getInstance();
    final Calendar calendarEnd = Calendar.getInstance();
    String format;
    SimpleDateFormat dateFormat;
    String courseInstructor;
    String courseInstructorPhone;
    String courseInstructorEmail;
    Spinner editCourseStatus;
    String courseStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        editCourseTitle=findViewById(R.id.editCourseTitle);
        editCourseStart=findViewById(R.id.editCourseStart);
        editCourseEnd=findViewById(R.id.editCourseEnd);
        editCourseStatus=findViewById(R.id.editCourseStatus);
        editInstructorName=findViewById(R.id.editInstructorName);
        editInstructorPhone=findViewById(R.id.editInstructorPhone);
        editInstructorEmail=findViewById(R.id.editInstructorEmail);
        courseID = getIntent().getIntExtra("course_id", -1);
        courseTitle = getIntent().getStringExtra("course_title");
        courseStart = getIntent().getStringExtra("course_start");
        courseStatus = getIntent().getStringExtra("course_status");
        courseEnd = getIntent().getStringExtra("course_end");
        courseInstructor = getIntent().getStringExtra("course_instructor_name");
        courseInstructorPhone = getIntent().getStringExtra("course_instructor_phone");
        courseInstructorEmail = getIntent().getStringExtra("course_instructor_email");
        editCourseTitle.setText(courseTitle);

        editCourseStart.setText(courseStart);
        editCourseEnd.setText(courseEnd);
        editInstructorName.setText(courseInstructor);
        editInstructorPhone.setText(courseInstructorPhone);
        editInstructorEmail.setText(courseInstructorEmail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        ArrayList<Course> courses;
        courses = repository.getAllCourses(termID);
        final CourseAdapter courseAdapter = new CourseAdapter(this,courses);
        courseAdapter.setCourses(courses);

        Spinner spinner = (Spinner) findViewById(R.id.editCourseStatus);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.course_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        editCourseStatus.setSelection(adapter.getPosition(courseStatus));

        format = "MM/dd/yy";
        dateFormat = new SimpleDateFormat(format, Locale.US);

        editCourseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = editCourseStart.getText().toString();
                if(info.equals(""));
                try{
                    calendarStart.setTime(dateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(CourseDetail.this, startDate, calendarStart
                        .get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editCourseEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = editCourseEnd.getText().toString();
                if(info.equals(""));
                try{
                    calendarEnd.setTime(dateFormat.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                new DatePickerDialog(CourseDetail.this, endDate, calendarEnd
                        .get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH),
                        calendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        startDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            calendarStart.set(Calendar.YEAR,year);
            calendarStart.set(Calendar.MONTH,monthOfYear);
            calendarStart.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            editCourseStart.setText(dateFormat.format(calendarStart.getTime()));
        };

        endDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
            calendarEnd.set(Calendar.YEAR,year);
            calendarEnd.set(Calendar.MONTH,monthOfYear);
            calendarEnd.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            editCourseEnd.setText(dateFormat.format(calendarEnd.getTime()));
        };
    }

    public void saveCourse(View view) {
        Course course;

        if (courseID == -1) {
            int id = repository.getAllTerms().get(repository.getAllTerms().size() -1).getTermID() + 1;
            course = new Course(id, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),editCourseStatus.getSelectedItem().toString(), editInstructorName.getText().toString(),editInstructorPhone.getText().toString(),editInstructorEmail.getText().toString(), termID);
            repository.insertCourse(course);
        } else {
            course = new Course(courseID, editCourseTitle.getText().toString(), editCourseStart.getText().toString(), editCourseEnd.getText().toString(),editCourseStatus.getSelectedItem().toString(), editInstructorName.getText().toString(),editInstructorPhone.getText().toString(),editInstructorEmail.getText().toString(), termID);
            repository.updateCourse(course);
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
        Intent intent = new Intent(CourseDetail.this, AssessmentList.class);
        startActivity(intent);
    }
}
