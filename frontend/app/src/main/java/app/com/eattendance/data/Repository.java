package app.com.eattendance.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {

    private StudentDao studentDao;
    private InstructorDao instructorDao;
    private LiveData<List<Student>> allStudents;

    Repository(Application application) {
        Database db = Database.getDatabase(application);
        studentDao = db.studentDao();
        instructorDao = db.instructorDao();
        allStudents = studentDao.getAllStudents();
    }

    LiveData<List<Student>> getAllStudents() { return allStudents; }

    public void insert(Student student) {
        new insertAsyncTask(studentDao).execute(student);
    }

    private static class insertAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDao AsyncTaskDao;

        insertAsyncTask(StudentDao dao) {
            AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... params) {
            AsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
