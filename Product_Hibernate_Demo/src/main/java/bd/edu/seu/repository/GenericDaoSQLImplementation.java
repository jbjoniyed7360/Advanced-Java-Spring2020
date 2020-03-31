package bd.edu.seu.repository;import bd.edu.seu.util.HibernateUtil;import org.hibernate.Session;import java.io.Serializable;import java.util.List;public class GenericDaoSQLImplementation<T,I> implements GenericDao<T,I> {    private Class<T> aClass;    public GenericDaoSQLImplementation(Class<T> aClass) {        this.aClass = aClass;    }    @Override    public List<T> findAll() {        Session session = HibernateUtil.getSessionFactory().openSession();        session.beginTransaction();        return session.createQuery("from " + aClass.getSimpleName(), aClass).getResultList();    }    @Override    public Object findById(Object id) {        Session session = HibernateUtil.getSessionFactory().openSession();        session.beginTransaction();        return session.get(aClass, (Serializable) id);    }    @Override    public Object save(Object o) {        Session session = HibernateUtil.getSessionFactory().openSession();        session.beginTransaction();        session.save(o);        session.getTransaction().commit();        return o;    }    @Override    public Object update(Object o, Object id) {        Session session = HibernateUtil.getSessionFactory().openSession();        session.beginTransaction();        T type = session.get(aClass, (Serializable) id);        session.delete(type);        session.save(o);        type = session.get(aClass, (Serializable) id);        session.getTransaction().commit();        return type;    }    @Override    public void deleteAll() {        Session session = HibernateUtil.getSessionFactory().openSession();        session.beginTransaction();        session.createQuery("delete from " + aClass.getSimpleName()).executeUpdate();    }    @Override    public Object delete(Object id) {        Session session = HibernateUtil.getSessionFactory().openSession();        session.beginTransaction();        T type = session.get(aClass, (Serializable) id);        session.delete(type);        session.getTransaction().commit();        return type;    }}