//package android.julian.mobileappdev.UI;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.AlarmManager;
//import android.app.DatePickerDialog;
//import android.app.PendingIntent;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.julian.mobileappdev.Entity.Term;
//import android.julian.mobileappdev.R;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.EditText;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//
//public class CourseDetail extends AppCompatActivity {
//    EditText editDate;
//
//    DatePickerDialog.OnDateSetListener startDate;
//    final Calendar calendarStart = Calendar.getInstance();
//    String format;
//    SimpleDateFormat dateFormat;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_course_detail);
////        editDate=findViewById(R.id.editDate);
//        format = "MM/dd/yy";
//        dateFormat = new SimpleDateFormat(format, Locale.US);
//
//        editDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Date date;
//                String info = editDate.getText().toString();
//                if(info.equals(""));
//                try{
//                    calendarStart.setTime(dateFormat.parse(info));
//                } catch (ParseException e){
//                    e.printStackTrace();
//                }
//
//                new DatePickerDialog(CourseDetail.this, startDate, calendarStart
//                        .get(Calendar.YEAR), calendarStart.get(Calendar.MONTH),
//                        calendarStart.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//
//        startDate = (datePicker, year, monthOfYear, dayOfMonth) -> {
//            calendarStart.set(Calendar.YEAR,year);
//            calendarStart.set(Calendar.MONTH,monthOfYear);
//            calendarStart.set(Calendar.DAY_OF_MONTH,dayOfMonth);
//            updateLabel();
//        };
//    }
//
//    private void updateLabel(){
//        editDate.setText(dateFormat.format(calendarStart.getTime()));
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_coursedetail, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//
//        switch (menuItem.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//            case R.id.share:
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT, "text from the note field.");
//                intent.putExtra(Intent.EXTRA_TITLE, "Title");
//                intent.setType("text/plain");
//                Intent shareIntent = Intent.createChooser(intent, null);
//                startActivity(shareIntent);
//                return true;
//            case R.id.notify:
//                String datePicked = editDate.getText().toString();
//                Date date = null;
//                try {
//                    date = dateFormat.parse(datePicked);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Long trigger = date.getTime();
//
//                Intent intent1 = new Intent(CourseDetail.this, Receiver.class);
//                intent1.putExtra("key","Your course TITLE starts today!");
//
//                PendingIntent sender = PendingIntent.getBroadcast(CourseDetail.this,MainActivity.numAlert++, intent1,0);
//
//                AlarmManager manager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                manager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
//
//                return true;
//        }
//        return super.onOptionsItemSelected(menuItem);
//    }
//
//    public void saveCourse(View view) {
//        Term t;
//        if (courseID == -1) {
//            int id = repository.getAllTerms().get(repository.getAllTerms().size() -1).getTermID() + 1;
//            t = new Term(id, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
//            repository.insertTerm(t);
//        } else {
//            t = new Term(termID, editTermName.getText().toString(), editStartDate.getText().toString(), editEndDate.getText().toString());
//            repository.updateTerm(t);
//        }
//        Intent intent = new Intent(TermDetails.this, TermList.class);
//        startActivity(intent);
//    }
//}
