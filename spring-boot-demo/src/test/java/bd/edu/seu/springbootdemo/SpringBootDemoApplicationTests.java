package bd.edu.seu.springbootdemo;

import bd.edu.seu.springbootdemo.model.Student;
import bd.edu.seu.springbootdemo.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringBootDemoApplicationTests {
    @Autowired
    private StudentRepository studentRepository;


    @AfterEach
    public void deleteAll(){
        studentRepository.deleteAll();
    }

    /*
    @Test
    public void save(){
        Student student = new Student(201788,"Md Joniyed Bhuiyan",3.5, LocalDate.now());
        Student saveStudent = studentRepository.save(student);
        System.out.println(saveStudent);
        Assertions.assertEquals(student.getId(),saveStudent.getId());
        Assertions.assertEquals(student.getName(),saveStudent.getName());
        Assertions.assertEquals(student.getCgpa(),saveStudent.getCgpa(),.001);
    }


    @Test
    public void readStudents(){
        Iterable<Student> students = studentRepository.findAll();

        List<Student> studentList = new ArrayList<>();

        students.forEach(studentList::add);

        System.out.println(studentList+"\n"+studentList.size()+" students..");
    }


    @Test
    public void readStudentByName(){
        Student student1 = new Student(1,"joniyed bhuiyan",3.9,LocalDate.now());
        Student student2 = new Student(2,"Nahidul islam",3.8,LocalDate.now());
        Student student3 = new Student(3,"arif hossain",3.7,LocalDate.now());
        Student student4 = new Student(4,"mahabub sazib",3.6,LocalDate.now());
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);


        List<Student> students = studentRepository.findByName("joniyed bhuiyan");
        students.stream().forEach(System.out::println);
    }

    @Test
    public void readStudentByNameMatched(){
        Student student1 = new Student(1,"joniyed bhuiyan",3.9,LocalDate.now());
        Student student2 = new Student(2,"Nahidul islam",3.8,LocalDate.now());
        Student student3 = new Student(3,"arif hossain",3.7,LocalDate.now());
        Student student4 = new Student(4,"mahabub sazib",3.6,LocalDate.now());
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);


        List<Student> students = studentRepository.findByNameContaining("n");
        students.stream().forEach(System.out::println);
    }




    //TODO delete single student
    @Test
    public void deleteStudent(){
        Student student1 = new Student(1,"joniyed bhuiyan",3.9,LocalDate.now());
        studentRepository.save(student1);
        Student student2 = new Student(2,"Nahidul Islam",3.8,LocalDate.now());
        studentRepository.save(student2);

        studentRepository.findAll().forEach(System.out::println);

        studentRepository.delete(student1);

        System.out.println("\nAfter delete....");
        studentRepository.findAll().forEach(System.out::println);
    }

    //TODO delete all student
    @Test
    public void deleteAllStudents(){
        Student student1 = new Student(1,"joniyed bhuiyan",3.9,LocalDate.now());
        studentRepository.save(student1);
        Student student2 = new Student(2,"Nahidul islam",3.8,LocalDate.now());
        studentRepository.save(student2);
        Student student3 = new Student(3,"arif hossain",3.7,LocalDate.now());
        studentRepository.save(student3);
        Student student4 = new Student(4,"mahabub sazib",3.6,LocalDate.now());
        studentRepository.save(student4);


        List<Student> studentList = new ArrayList<>();

        studentRepository.findAll().forEach(studentList::add);

        studentList.stream().forEach(System.out::println);

        studentRepository.deleteAll();

        studentList.clear();

        studentRepository.findAll().forEach(studentList::add);
        System.out.println("\nAfter delete all"+"\n\n"+studentList.size()+" students");

    }


    //TODO update a student
    @Test
    public void updateStudent(){
        Student student1 = new Student(1,"joniyed bhuiyan",3.9,LocalDate.now());
        studentRepository.save(student1);

        System.out.println("Before update : \n");
        studentRepository.findById(1l).ifPresent(System.out::print);

        student1.setId(1);
        student1.setName("Nahidul Islam");
        student1.setCgpa(3.8);
        studentRepository.save(student1);

        System.out.println("After update : \n");
        studentRepository.findById(1l).ifPresent(System.out::print);
    }

    //TODO find all student whose cgpa is >=3.75
    @Test
    public void findAllByCgpaLimit(){
        Student student1 = new Student(1,"joniyed bhuiyan",3.9,LocalDate.of(1997,2,2));
        studentRepository.save(student1);
        Student student2 = new Student(2,"Nahidul islam",3.8,LocalDate.now());
        studentRepository.save(student2);
        Student student3 = new Student(3,"arif hossain",3.7,LocalDate.now());
        studentRepository.save(student3);
        Student student4 = new Student(4,"mahabub sazib",3.6,LocalDate.now());
        studentRepository.save(student4);


        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
        System.out.println("All student....");
        studentList.stream().forEach(System.out::println);

        System.out.println("\n\nStudent whose cgpa is greate or equal to 3.75");
        studentRepository.findAllByCgpaAfter(3.75)
                .stream()
                .forEach(System.out::println);

    }


    //TODO find all student whose DOB between 1990 to 2000
    @Test
    public void findAllByDOBLimit(){
        Student student1 = new Student(1,"joniyed bhuiyan",3.9,LocalDate.of(1980,3,3));
        studentRepository.save(student1);
        Student student2 = new Student(2,"Nahidul islam",3.8,LocalDate.of(1990,1,1));
        studentRepository.save(student2);
        Student student3 = new Student(3,"arif hossain",3.7,LocalDate.of(2000,12,31));
        studentRepository.save(student3);
        Student student4 = new Student(4,"mahabub sazib",3.6,LocalDate.now());
        studentRepository.save(student4);


        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);

        System.out.println("All student:");
        studentList.stream().forEach(System.out::println);


        System.out.println("\nDOB between 1990-2000");
        studentRepository.findAllByLocalDateBetween(LocalDate.of(1990,1,1),LocalDate.of(2000,12,31))
                .stream()
                .forEach(System.out::println);
    }
     */


}
