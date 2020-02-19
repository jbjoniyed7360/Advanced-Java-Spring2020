package bd.edu.seu.studentsservicceprojectnetbeans;

import bd.edu.seu.service.StudentService;
import java.util.List;
import bd.edu.seu.models.Student;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {

    private static double findAvg(List<Student> studentList) {
        double avg = 0.0;
        double sum = 0;
        for (int i = 0; i < studentList.size(); i++) {
            sum += studentList.get(i).getCgpa();
        }

        avg = sum / studentList.size();

        return avg;
    }

    private static double findAvgEnhenceFroloop(List<Student> studentList) {
        double sum = 0, avg = 0;
        for (Student student : studentList) {
            sum += student.getCgpa();
        }
        avg = sum / studentList.size();
        return avg;
    }

    private static double findAvgStream(List<Student> studentList) {
        return studentList.stream()
                .mapToDouble(s -> s.getCgpa())
                .average()
                .getAsDouble();
    }

    private static double findAvgStreamParallel(List<Student> studentList) {
        return studentList.stream()
                .parallel()
                .mapToDouble(s -> s.getCgpa())
                .max()
                .getAsDouble();
    }

    private static List<Student> findEvenIDStreamParallel(List<Student> studentList) {

        return studentList.stream()
                .parallel()
                .filter(s -> s.getId() % 2 == 0)
                .sorted((a, b) -> (int) a.getId() - (int) b.getId())
                .collect(Collectors.toList());
    }

    private static List<Student> findOddIDStreamParallel(List<Student> studentList) {
       return studentList.stream()
               .parallel()
               .filter(s -> s.getCgpa()<3)
               .collect(Collectors.toList());
       
      // return studentList;
    }

    public static class ComapreByCgpa implements Comparator<Student> {

        @Override
        public int compare(Student a, Student b) {
            return ((int) (-a.getCgpa() * 1000 + b.getCgpa() * 1000));
        }

    }

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        long start = System.currentTimeMillis();
        List<Student> studentList = studentService.getStudentList();
        long end = System.currentTimeMillis();

        System.out.printf("Time taken for read data =%.3f", ((double) (end - start) / 1000));
        System.out.println("sec");
        /* System.out.println("Before sorting.....");
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
    
        
        System.out.print("\nMax from less than 3 Cgpa Filtered Student = ");
        studentList
                .stream()
                .filter(s -> s.getCgpa()<3)
                .filter(s -> s.getName().endsWith("ed"))
                .sorted((a,b) -> (int)a.getId()-(int)b.getId())
                .mapToDouble(s -> s.getCgpa())
                .max()
                .ifPresent(System.out::print);
        
        
        
        System.out.println("\nSort the list using stream api...");
        List<Student> newList = studentList
                .stream()
                .filter(s -> s.getName().length()>8)
                .sorted((a,b) -> (int)a.getId()-(int)b.getId())
                .collect(Collectors.toList());
        
        newList.forEach(System.out::println);*/

//        start = System.currentTimeMillis();
//        double avg = findAvg(studentList);
//        end = System.currentTimeMillis();
//        System.out.printf("Avg : %.3f", avg);
//        System.out.printf("  Time need %.3f", (double) (end - start) / 1000);
//
//        start = System.currentTimeMillis();
//        avg = findAvgEnhenceFroloop(studentList);
//        end = System.currentTimeMillis();
//        System.out.printf("\n\nAvg Enhenced : %.3f", avg);
//        System.out.printf("  Time need %.3f", (double) (end - start) / 1000);
//
//        start = System.currentTimeMillis();
//        avg = findAvgStream(studentList);
//        end = System.currentTimeMillis();
//        System.out.printf("\n\nAvg Stream : %.3f", avg);
//        System.out.printf("  Time need %.3f", (double) (end - start) / 1000);
//
//        start = System.currentTimeMillis();
//        avg = findAvgStreamParallel(studentList);
//        end = System.currentTimeMillis();
//        System.out.printf("\n\nAvg Stream Parallel: %.3f", avg);
//        System.out.printf("  Time need %.3f", (double) (end - start) / 1000);
//
//        start = System.currentTimeMillis();
//       studentList = findEvenIDStreamParallel(studentList);
//        end = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(studentList.get(i));
//        }
//        System.out.printf("  Time need %.3f", (double) (end - start) / 1000);
//        System.out.println("");

        start = System.currentTimeMillis();
        studentList = findOddIDStreamParallel(studentList);
        end = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(studentList.get(i));
        }
        System.out.printf("  Time need for odd number %.3f", (double) (end - start) / 1000);

    }
}
