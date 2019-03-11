package app.com.eattendance.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Student>> allStudents;

    public ViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        allStudents = repository.getAllStudents();
    }

    LiveData<List<Student>> getAllStudents() { return allStudents; }

    public void insert(Student student) { repository.insert(student); }



}
