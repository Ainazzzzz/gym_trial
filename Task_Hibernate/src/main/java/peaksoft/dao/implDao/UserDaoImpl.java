package peaksoft.dao.implDao;

import jakarta.persistence.EntityManager;
import peaksoft.config.HibernateUtil;
import peaksoft.dao.UserDao;
import peaksoft.entity.User;

public class UserDaoImpl implements UserDao {
    public void save(User user) {
        EntityManager entityManager = HibernateUtil.getSession().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Iterable<User> getAll() {
        EntityManager entityManager = HibernateUtil.getSession().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Iterable<User> users = entityManager.createQuery("FROM User", User.class).getResultList();
            entityManager.getTransaction().commit();
            return users;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }


   return null; }
}

