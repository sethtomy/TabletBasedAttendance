package app.com.eattendance;

import android.view.View;

public interface AttendanceSessionItemClickListener {
    public void onItemClicked(View view, int position, String studentId);
}
