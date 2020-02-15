package bd.edu.seu.studentsservicceprojectnetbeans;

import bd.edu.seu.service.StudentService;
import java.util.List;
import bd.edu.seu.models.Student;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    
    public static class ComapreByCgpa implements Comparator<Student>{

        @Override
        public int compare(Student a, Student b) {
            return ((int)(-a.getCgpa()*1000+b.getCgpa()*1000));
        }
        
    }
      
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        List<Student> studentList = studentService.getStudentList();
        System.out.println("Before sorting.....");
        studentList.forEach(s -> System.out.println(s));
        
        
        System.out.println("\nAfter sorting...by id");
        studentList.sort(new CompareById());
        studentList.forEach(System.out::println);
        
        System.out.println("\nAfter sorting... by cgpa");
        Collections.sort(studentList,new ComapreByCgpa());
        studentList.forEach(s -> System.out.println(s));
        
        
        System.out.println("\nAfter sorting using lambda...by name");
        Collections.sort(studentList,(a,b) -> b.getName().compareTo(a.getName()));
        studentList.forEach(System.out::println);
        
        System.out.println("\nSort using Annynomus Class..");
        Collections.sort(studentList,new Comparator<Student>() {
            @Override
            public int compare(Student t, Student t1) {
                return t.getName().compareTo(t1.getName());
            }
        });
        studentList.forEach(s -> System.out.println(s));
        
    }
}
