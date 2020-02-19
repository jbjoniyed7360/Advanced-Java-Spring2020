package bd.edu.seu.service;

import java.util.List;
import bd.edu.seu.models.Student;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentService {

    public List<Student> getStudentList() {

        List<Student> studentList;
//        List<Student> studentList = Arrays.asList(
//                new Student(88, "Joniyed", 3.9),
//                new Student(92, "Nahid", 3.75),
//                new Student(128, "Sazib", 3.00),
//                new Student(4, "Arif", 3.22),
//                new Student(80, "Juwel", 3.45),
//                new Student(101, "Rahmutullah Tomal", 3.85),
//                new Student(102, "Shakil Ahmed", 2.99),
//                new Student(103, "Abul Hossain", 1.9),
//                new Student(106, "Kabul Ahmed", 1.11),
//                new Student(110, "Babul Ahmed", 1.5),
//                new Student(120, "Bulbul Ahmed", .97)
//        
//        );

        double EPS = 1E-4;
        int n = 10_000_000;
//         for(int i=0;i<n;i++){
//             long id = i+1;
//             String name = "Joniyed Bhuiyan";
//             double Cgpa = Math.random()*4+EPS;
//             if(Cgpa>4)Cgpa=4;
//             Student student = new Student(id, name, Cgpa);
//             studentList.add(student);
//         }
//         Collections.shuffle(studentList);

        // byte [] array = new byte[5];
        Random r = new Random();
        studentList = IntStream.rangeClosed(1, n)
                .mapToObj(id -> {
//                    new Random().nextBytes(array);
//                    String name = new String(array);
                    String name = r.ints(97, 122)
                            .limit(5)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                    double Cgpa = Math.random() * 4 + EPS;
                    if (Cgpa > 4) {
                        Cgpa = 4;
                    }
                    Student student = new Student(id, name, Cgpa);
                    return student;
                })
                .collect(Collectors.toList());

        Collections.shuffle(studentList);

        return studentList;
    }
}
