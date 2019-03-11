package app.com.eattendance.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

@androidx.room.Dao
public interface StudentDao {

    @Insert
    void insert(Student student);

    @Query("DELETE FROM student_table")
    void deleteAll();

    @Query("SELECT * from student_table ORDER BY id ASC")
    LiveData<List<Student>> getAllStudents();

}
