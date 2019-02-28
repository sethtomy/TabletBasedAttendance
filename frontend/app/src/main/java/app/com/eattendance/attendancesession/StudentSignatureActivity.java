package app.com.eattendance.attendancesession;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
                getIntent().getExtras().getString(Constants.EXTRA_STUDENT_ID) == null) {
            finish();
            return;
        }

        final String studentId = getIntent().getExtras().getString(Constants.EXTRA_STUDENT_ID);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        Button doneButton = findViewById(R.id.btn_student_submit_attendance);

        final TextView studentNameSigBox = findViewById(R.id.tv_signature_box_name);
        final TextView signatureDate = findViewById(R.id.tv_signature_box_date);
        final SignaturePad signaturePad = findViewById(R.id.signature_view);
        ImageButton clearSignatureBox = findViewById(R.id.btn_clear_signature);

        final TextView studentNameHeading = findViewById(R.id.tv_student_name);

        // Display the date in the signature box
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/YYY", Locale.getDefault());
        signatureDate.setText(simpleDateFormat.format(Calendar.getInstance().getTime()));

        clearSignatureBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signaturePad.isEmpty()) {
                    Toast.makeText(StudentSignatureActivity.this,
                            getString(R.string.error_sign_signature), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentSignatureActivity.this,
                            getString(R.string.message_att_recorded), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        // Fetch Student from Database
        db.collection("student").document(studentId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            student = task.getResult().toObject(Student.class);

                            if (student != null) {
                                studentNameHeading.setText(student.getFullName());
                                studentNameSigBox.setText(String.format("%s, %s",
                                        student.getLastName(), student.getFirstName()));
                            }

                        } else {
                            finish();
                            Toast.makeText(StudentSignatureActivity.this,
                                    getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.page_title_sign_attendance));
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
