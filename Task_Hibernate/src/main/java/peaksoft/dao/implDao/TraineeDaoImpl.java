package peaksoft.dao.implDao;

import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.config.HibernateUtil;
import peaksoft.dao.TraineeDao;
import peaksoft.entity.Trainee;
import peaksoft.entity.Trainer;
import peaksoft.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TraineeDaoImpl implements TraineeDao {
    private final UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void createTraineeProfile(Trainee trainee) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            String firstName = UUID.randomUUID().toString();
            String lastName = UUID.randomUUID().toString();

            User user = new User();
            user.setFirstAndLastName(firstName,lastName);
            user.setActive(true);

            userDao.save(user);
            Trainee trainee1 = new Trainee();
            trainee1.setDate_of_birth(new Date());
            trainee1.setAddress(trainee.getAddress());
            trainee1.setUser(user);

            session.merge(trainee1);

            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void checkCredentialsTrainee(String username, String password) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            Trainee trainee = session.createQuery("FROM Trainee t " +
                            "JOIN t.user u " +
                            "WHERE u.username = :username", Trainee.class)
                    .setParameter("username", username).uniqueResult();

            User user = session.createNamedQuery("User.checkCredentials", User.class).
                    setParameter("username", trainee.getUser().getUsername()).
                    setParameter("password", trainee.getUser().getPassword()).uniqueResult();
            System.out.println(user);
            if (user != null && user.getPassword().equalsIgnoreCase(trainee.getUser().getPassword()) ==
                    user.getUsername().equalsIgnoreCase(trainee.getUser().getUsername())) {
                System.out.println("data entered correctly");
            } else {
                System.out.println("data entered is incorrect");
            }
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Incorrect password/username" + e.getMessage());
        }
    }

    @Override
    public void SelectTraineeProfileByUsername(String username) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT t FROM Trainee t WHERE t.user.username = :username", Trainee.class);
            query.setParameter("username", username);

            Object trainee = query.getSingleResult();
            System.out.println(trainee);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void changeTraineePassword(String firstName, String newPassword) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM Trainee t WHERE t.user.firstName" +
                    "= :firstName", Trainee.class);
            query.setParameter("firstName", firstName);

            Trainee trainee = (Trainee) query.getSingleResult();
            trainee.getUser().setPassword(newPassword);
            session.merge(trainee.getUser());

            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateTraineeProfile(Trainee trainee) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Scanner scanner = new Scanner(System.in);
            Trainee trainee1 = session.get(Trainee.class, trainee.getId());

            User user = trainee1.getUser();
            System.out.print("Write FirstName: ");
            user.setFirstName(scanner.nextLine());
            System.out.print("Write LastName: ");
            user.setLastName(scanner.nextLine());
            System.out.print("Write UserName: ");
            user.setUsername(scanner.nextLine());
            System.out.print("Write Password : ");
            user.setPassword(scanner.nextLine());
            user.setActive(false);
            user.setId(user.getId());

            trainee.setDate_of_birth(new Date());
            trainee.setAddress("UPDATE");
            trainee.setUser(user);
            trainee.setId(trainee.getId());

            session.merge(user);
            session.merge(trainee);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void manageTraineeStatus(String choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case "ACTIVATE" -> {
                System.out.print("Write id Trainee: ");
                int activeId = scanner.nextInt();
                activateTrainee(activeId);
            }
            case "DEACTIVATE" -> {
                System.out.print("Write id Trainee: ");
                int deactivateId = scanner.nextInt();
                deactivateTrainee(deactivateId);
            }
            default -> throw new IllegalArgumentException("Invalid action type");
        }
    }

    @Override
    public void activateTrainee(int id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Trainee trainee = session.get(Trainee.class, id);
            trainee.getUser().setActive(true);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deactivateTrainee(int id) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Trainee trainee = session.get(Trainee.class, id);
            trainee.getUser().setActive(false);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void deleteTraineeProfileByUsername(String username) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            User user = (User) query.getSingleResult();

            Query query2 = session.createQuery("SELECT t FROM Trainee t WHERE t.user = :user", Trainee.class);
            query2.setParameter("user", user);
            Trainee trainee = (Trainee) query2.getSingleResult();
            trainee.setUser(null);

            if (user != null) {
                session.remove(user);

                System.out.println("Успешно");
            }

            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Trainee getTraineeById(long id) {
        Trainee trainee=null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
         trainee = session.get(Trainee.class,id);
         transaction.commit();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return trainee;
    }

    @Override
    public void updateTraineeTrainersList(String traineeUsername, List<Long> trainerIds) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            Trainee trainee = session.createQuery("SELECT tr FROM Trainee tr JOIN FETCH tr.trainers trn WHERE tr.user.username = :traineeUsername", Trainee.class)
                    .setParameter("traineeUsername", traineeUsername)
                    .getSingleResult();

            List<Trainer> trainers = session.createQuery("SELECT t FROM Trainer t WHERE t.id IN (:trainerIds)", Trainer.class)
                    .setParameter("trainerIds", trainerIds)
                    .getResultList();

            trainee.setTrainers(trainers);

            session.merge(trainee);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Failed to update trainee's trainers list: " + e.getMessage(), e);
        }
    }
}
