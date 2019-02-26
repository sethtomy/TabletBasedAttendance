package app.com.eattendance.student;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import app.com.eattendance.R;
import app.com.eattendance.data.Student;
import app.com.eattendance.util.Constants;

/**
 * Activity allows students to sign their signature during an attendance session.
 */
public class StudentSignatureActivity extends AppCompatActivity {
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signature);

        // Close activity is student id not passed.
        if (getIntent().getExtras() == null ||
                getIntent().getExtras().getString(Constants.EXTRA_STUDENT_ID) == null)
            finish();

        final String studentId = getIntent().getExtras().getString(Constants.EXTRA_STUDENT_ID);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        Button doneButton = findViewById(R.id.btn_student_submit_attendance);

        final TextView studentName = findViewById(R.id.tv_student_name);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentSignatureActivity.this,
                        "Attendance submitted. Thank You.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Fetch Student from Database
        db.collection("student").document(studentId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            student = task.getResult().toObject(Student.class);
                            studentName.setText(student.getFullName());
                        }
                    }
                });

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sign Attendance");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Toast.makeText(this, "Student ID: " + studentId, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
