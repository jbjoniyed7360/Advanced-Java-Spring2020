package bd.edu.seu.repository;

import java.util.List;

public interface GenericDao<TYPE,I> {
    List<TYPE> findAll();
    TYPE findById(I id);
    TYPE save(TYPE type);
    TYPE update(TYPE type,I id);
    void deleteAll();
    TYPE delete(I id);
}
