package app.com.eattendance.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "instructor_table")
public class Instructor extends Person {

    @NonNull
    @ColumnInfo(name = "auth_token")
    private String authToken;
    // TODO: Course list

    public Instructor(String email, String firstName, String lastName, String authToken) {
        super(email, firstName, lastName);
        this.authToken = authToken;
    }

    public String getAuthToken() { return authToken; }

    public void setAuthToken(String authToken) { this.authToken = authToken; }

}
