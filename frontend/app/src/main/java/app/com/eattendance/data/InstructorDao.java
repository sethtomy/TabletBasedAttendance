package app.com.eattendance.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface InstructorDao {

    @Insert
    void insert(Instructor instructor);

    @Query("DELETE FROM instructor_table")
    void deleteAll();

    @Query("SELECT * from instructor_table ORDER BY id ASC")
    LiveData<List<Instructor>> getAllInstructors();

}
