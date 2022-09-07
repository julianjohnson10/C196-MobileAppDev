package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;

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

public class TermDetails extends AppCompatActivity {
    EditText editTermName;
    EditText editStartDate;
    EditText editEndDate;
    String termName;
    String startDate;
    String endDate;
    int termID;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);
        editTermName=findViewById(R.id.editTermName);
        editStartDate=findViewById(R.id.editStartDate);
        editEndDate=findViewById(R.id.editEndDate);
        termID = getIntent().getIntExtra("term_id", -1);
        termName = getIntent().getStringExtra("term_name");
        startDate = getIntent().getStringExtra("term_start");
        endDate = getIntent().getStringExtra("term_end");
        editTermName.setText(termName);
        editStartDate.setText(startDate);
        editEndDate.setText(endDate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        ArrayList<Term> terms = repository.getAllTerms();
        final TermAdapter termAdapter=new TermAdapter(this, terms);
        termAdapter.setTerms(terms);
    }

    public void saveTerm(View view) {
        Term t;
        if (termID == -1) {
            int id = repository.getAllTerms().get(repository.getAllTerms().size() -1).getTermID() + 1;
            t = new Term(id, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repository.insertTerm(t);
        } else {
            t = new Term(termID, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
            repository.updateTerm(t);
        }
        Intent intent = new Intent(TermDetails.this, TermList.class);
        startActivity(intent);
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

    public void gotoCourseList(View view) {
        Intent intent = new Intent(TermDetails.this, CourseList.class);
        startActivity(intent);
    }
}