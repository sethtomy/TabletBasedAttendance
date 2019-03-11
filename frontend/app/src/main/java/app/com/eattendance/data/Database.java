package app.com.eattendance.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Student.class, Instructor.class}, version = 1)
public abstract class Database extends RoomDatabase {

    // Dao for the student table
    public abstract StudentDao studentDao();
    public abstract InstructorDao instructorDao();

    // Singleton to prevent multiple database instantiations
    private static volatile Database INSTANCE;
    static Database getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (Database.class) {
                if(INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "database").addCallback(databaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    // Wipe the db; add test students, instructors
    private static Database.Callback databaseCallback = new Database.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final StudentDao studentDao;
        private final InstructorDao instructorDao;

        PopulateDbAsync(Database db) {
            studentDao = db.studentDao();
            instructorDao = db.instructorDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            studentDao.deleteAll();
            instructorDao.deleteAll();
            // TODO: Populate from config file
            Student student = new Student("stomy1@student.gsu.edu", "Seth",
                    "Tomy");
            studentDao.insert(student);
            Instructor instructor = new Instructor("amussa@gsu.edu", "Awad",
                    "Mussa", "Invalid Auth Token");
            instructorDao.insert(instructor);
            return null;
        }

    }

}
