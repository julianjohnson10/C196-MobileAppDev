package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Term;
import android.julian.mobileappdev.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CourseList extends AppCompatActivity {
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
        repo= new Repository(getApplication());
    }

    public void saveTerm(View view) {
        Term t;
        if (termID == -1) {
            int id = repo.getAllTerms().get(repo.getAllTerms().size() -1).getTermID() + 1;
            t = new Term(id, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repo.insert(t);
        } else {
            t = new Term(termID, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repo.update(t);
        }
        Intent intent = new Intent(CourseList.this, TermList.class);
        startActivity(intent);
    }
}