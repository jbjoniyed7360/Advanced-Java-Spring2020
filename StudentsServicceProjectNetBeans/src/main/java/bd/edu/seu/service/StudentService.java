package bd.edu.seu.service;

import java.util.ArrayList;
import java.util.List;
import bd.edu.seu.models.Student;

public class StudentService {
    public List<Student> getStudentList(){
        Student a = new Student(88, "Joniyed", 3.9);
        Student b = new Student(92,"Nahid",3.75);
        Student c = new Student(128,"Sazib",3.00);
        Student d = new Student(4,"Arif",3.22);
        Student e = new Student(80,"Juwel",3.45);
        
        List<Student> studentList = new ArrayList<>();
        studentList.add(a);
        studentList.add(b);
        studentList.add(c);
        studentList.add(d);
        studentList.add(e);
        
        return studentList;
    }
}
