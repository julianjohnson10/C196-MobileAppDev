package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Term;
import android.julian.mobileappdev.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;

public class AddTerm extends AppCompatActivity {
    EditText addTermName;
    EditText addTermStart;
    EditText addTermEnd;
    String termName;
    String termStart;
    String termEnd;
    int termID;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        addTermName =findViewById(R.id.addTermName);
        addTermStart=findViewById(R.id.addTermStart);
        addTermEnd =findViewById(R.id.addTermEnd);
        termID = getIntent().getIntExtra("term_id", -1);
        termName = getIntent().getStringExtra("term_name");
        termStart = getIntent().getStringExtra("term_start");
        termEnd = getIntent().getStringExtra("term_end");
//        addTermName.setText(termName);
//        addTermStart.setText(termStart);
//        addTermEnd.setText(termEnd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        ArrayList<Term> terms = repository.getAllTerms();
        final TermAdapter termAdapter=new TermAdapter(this, terms);
        termAdapter.setTerms(terms);

    }

    public void addTerm(View view) {
        Term t;
        if (termID == -1) {
            int id = repository.getAllTerms().get(repository.getAllTerms().size() -1).getTermID() + 1;
            t = new Term(id, addTermName.getText().toString(), addTermStart.getText().toString(), addTermEnd.getText().toString());
            repository.insertTerm(t);
        } else {
            t = new Term(termID, addTermName.getText().toString(), addTermStart.getText().toString(), addTermEnd.getText().toString());
            repository.updateTerm(t);
        }
        Intent intent = new Intent(AddTerm.this, TermList.class);
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


}