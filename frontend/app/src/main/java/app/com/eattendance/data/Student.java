package app.com.eattendance.data;

import androidx.room.Entity;

@Entity(tableName = "student_table")
public class Student extends Person {

    public Student(String email, String firstName, String lastName) {
        super(email, firstName, lastName);
    }


}
