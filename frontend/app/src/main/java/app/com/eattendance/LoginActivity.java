package app.com.eattendance;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import app.com.eattendance.attendancesession.AttendanceSessionActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AttendanceSessionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
