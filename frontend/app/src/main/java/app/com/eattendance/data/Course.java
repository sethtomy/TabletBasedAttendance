package app.com.eattendance.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {

    @NonNull
    private Roster roster;

    @NonNull
    private Instructor instructor;

    @NonNull
    @PrimaryKey
    private String name;

    @NonNull
    @ColumnInfo(name = "warning_threshold")
    private int warningThreshold;


    private int currentAttendanceSession = 0;

    public Course(String name, Instructor instructor, Roster roster, int warningThreshold) {
        this.name = name;
        this.instructor = instructor;
        this.roster = roster;
        this.warningThreshold = warningThreshold;
    }

}
