package android.julian.mobileappdev.Database;

import android.app.Application;
import android.julian.mobileappdev.DAO.CourseDAO;
import android.julian.mobileappdev.DAO.TermDAO;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.Entity.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;

    private static int THREADS=4;
    static final ExecutorService dbExecutor= Executors.newFixedThreadPool(THREADS);

    public Repository(Application application){
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);
        mCourseDAO=db.courseDAO();
        mTermDAO=db.termDAO();
    }

    public List<Term>getAllTerms(){
        dbExecutor.execute(()->{
            mAllTerms=mTermDAO.getAllTerms();
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Term term) {
        dbExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
