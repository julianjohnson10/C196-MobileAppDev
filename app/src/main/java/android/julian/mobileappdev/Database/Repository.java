package android.julian.mobileappdev.Database;

import android.app.Application;
import android.julian.mobileappdev.DAO.AssessmentDAO;
import android.julian.mobileappdev.DAO.CourseDAO;
import android.julian.mobileappdev.DAO.TermDAO;
import android.julian.mobileappdev.Entity.Assessment;
import android.julian.mobileappdev.Entity.Course;
import android.julian.mobileappdev.Entity.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final TermDAO mTermDAO;
    private final CourseDAO mCourseDAO;
    private final AssessmentDAO mAssessmentDAO;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;
    private List<Assessment> mAllAssessments;

    private static final int THREADS=4;
    static final ExecutorService dbExecutor= Executors.newFixedThreadPool(THREADS);

    public Repository(Application application){
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);
        mCourseDAO=db.courseDAO();
        mTermDAO=db.termDAO();
        mAssessmentDAO=db.assessmentDAO();
    }

    public ArrayList<Term>getAllTerms(){
        dbExecutor.execute(()->{
            mAllTerms=mTermDAO.getAllTerms();
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (ArrayList<Term>) mAllTerms;
    }

    public ArrayList<Course>getAllCourses(int termID){
        dbExecutor.execute(()->{
            mAllCourses=mCourseDAO.getAllCourses(termID);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (ArrayList<Course>) mAllCourses;
    }

    public ArrayList<Assessment>getAllAssessments(int courseID){
        dbExecutor.execute(()->{
            mAllAssessments = mAssessmentDAO.getAllAssessments(courseID);
        });

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (ArrayList<Assessment>) mAllAssessments;
    }

    public void insertTerm(Term term) {
        dbExecutor.execute(() -> {
            mTermDAO.insert(term);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertCourse(Course course) {
        dbExecutor.execute(() -> {
            mCourseDAO.insert(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertAssessment(Assessment assessment) {
        dbExecutor.execute(() -> {
            mAssessmentDAO.insert(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateTerm(Term term){
        dbExecutor.execute(()->{
            mTermDAO.update(term);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateCourse(Course course){
        dbExecutor.execute(()->{
            mCourseDAO.update(course);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateAssessment(Assessment assessment){
        dbExecutor.execute(()->{
            mAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteTerm(Term term){
        dbExecutor.execute(()->{
            mTermDAO.delete(term);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteCourse(Course course){
        dbExecutor.execute(()->{
            mCourseDAO.delete(course);
        });
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
