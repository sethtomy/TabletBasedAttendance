package app.com.eattendance.attendancesession;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.com.eattendance.AttendanceSessionItemClickListener;
import app.com.eattendance.R;
import app.com.eattendance.data.Student;
import app.com.eattendance.student.StudentSignatureActivity;
import app.com.eattendance.util.Constants;

public class AttendanceSessionActivity extends AppCompatActivity implements AttendanceSessionItemClickListener {
    RecyclerView attendanceSessionRV;
    FirestoreRecyclerAdapter attendanceSessionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_session);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Attendance Session");
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        attendanceSessionRV = findViewById(R.id.rv_attendance_sess);
        Query dbQuery = db.collection("student");
        FirestoreRecyclerOptions<Student> options = new FirestoreRecyclerOptions.Builder<Student>()
                .setQuery(dbQuery, Student.class).build();

        attendanceSessionListAdapter =
                new FirestoreRecyclerAdapter<Student, AttendanceSessionListVH>(options) {

                    @NonNull
                    @Override
                    public AttendanceSessionListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.attendance_session_list_item, parent, false);
                        return new AttendanceSessionListVH(view, AttendanceSessionActivity.this);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull AttendanceSessionListVH attendanceSessionVH,
                                                    int i, @NonNull Student student) {
                        attendanceSessionVH.studentName.setTag(getSnapshots().getSnapshot(i).getId());
                        attendanceSessionVH.studentName.setText(student.getFullName());
                    }
                };
        attendanceSessionRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        attendanceSessionRV.setAdapter(attendanceSessionListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        attendanceSessionListAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        attendanceSessionListAdapter.stopListening();
    }

    @Override
    public void onItemClicked(View view, int position, String studentId) {
        Intent intent = new Intent(this, StudentSignatureActivity.class);
        intent.putExtra(Constants.EXTRA_STUDENT_ID, studentId);
        startActivity(intent);
    }
}
