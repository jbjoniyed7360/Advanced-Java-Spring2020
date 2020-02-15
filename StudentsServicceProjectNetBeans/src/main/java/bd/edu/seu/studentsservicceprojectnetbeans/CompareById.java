package bd.edu.seu.studentsservicceprojectnetbeans;

import bd.edu.seu.models.Student;
import java.util.Comparator;

/**
 *
 * @author Joniyed
 */
public class CompareById implements Comparator<Student>{

    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(Student a, Student b) {
        return (int)(a.getId()-b.getId());
    }
    
}
