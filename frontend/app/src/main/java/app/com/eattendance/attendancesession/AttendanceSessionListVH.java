package app.com.eattendance.attendancesession;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import app.com.eattendance.AttendanceSessionItemClickListener;
import app.com.eattendance.R;

/**
 * View holder for attendance session recyclerview list.
 */
public class AttendanceSessionListVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final int TAG_STUDENT_ID = 19;
    TextView studentName;
    private AttendanceSessionItemClickListener clickListener;

    public AttendanceSessionListVH(@NonNull View itemView,
                                   AttendanceSessionItemClickListener clickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.clickListener = clickListener;
        studentName = itemView.findViewById(R.id.tv_student_name);
    }

    @Override
    public void onClick(View v) {
        clickListener.onItemClicked(itemView, getAdapterPosition(),
                studentName.getTag().toString());
    }
}
