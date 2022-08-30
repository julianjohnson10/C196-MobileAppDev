package android.julian.mobileappdev.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.julian.mobileappdev.Database.Repository;
import android.julian.mobileappdev.Entity.Term;
import android.os.Bundle;

import android.julian.mobileappdev.R;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        Repository repository=new Repository(getApplication());
        ArrayList<Term> terms= repository.getAllTerms();
        final TermAdapter termAdapter=new TermAdapter(this, terms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(termAdapter);
        termAdapter.setTerms(terms);

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
        Intent intent = new Intent(TermList.this, CourseList.class);
        startActivity(intent);

    }
}