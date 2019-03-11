package app.com.eattendance.data;

import java.util.List;

public class Roster {

    private List<Student> students;

    public Roster(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

}
