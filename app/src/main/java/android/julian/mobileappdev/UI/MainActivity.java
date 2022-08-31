package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.Entity.Term;
import android.os.Bundle;
import android.view.View;

import android.julian.mobileappdev.R;

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
        Term term = new Term(1,"Fall Term","8-26-2022","9-26-2022");
        repo.insertTerm(term);
        Course c = new Course(1,"test","test", "test", "test", "test","test","test");
        repo.insertCourse(c);
    }
}