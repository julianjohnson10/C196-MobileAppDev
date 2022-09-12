package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Assessment;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.Entity.Term;
import android.os.Bundle;
import android.view.View;

import android.julian.mobileappdev.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterApp(View view) {

        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);
        Repository repo=new Repository(getApplication());
        Term term = new Term(1,"Fall Term 2022", "08/10/22","02/20/22");
        repo.insertTerm(term);
        Course c = new Course(1,"Intro to IT","08/10/22", "09/20/22", "In Progress", "Jerry Garcia","(844)-100-2222","jgarcia@school.edu","Here are my class notes.",1);
        repo.insertCourse(c);
        Assessment a = new Assessment(1,"Performance Assessment","IT Fundamentals Test", "09/20/22", 1);
        repo.insertAssessment(a);
    }
}