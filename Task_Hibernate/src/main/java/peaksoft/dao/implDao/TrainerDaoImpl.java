package peaksoft.dao.implDao;

import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.config.HibernateUtil;
import peaksoft.dao.TrainerDao;
import peaksoft.entity.Trainer;
import peaksoft.entity.Training_Types;
import peaksoft.entity.User;
import peaksoft.enums.TrainingType;

import java.util.*;

public class TrainerDaoImpl implements TrainerDao {
    private final UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void createTrainerProfile(Trainer trainer) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            TrainingType[] types = TrainingType.values();
            TrainingType type = types[new Random().nextInt(types.length)];

            Training_Types trainingTypes = new Training_Types();
            trainingTypes.setTrainingTypeName(type.toString());
            session.merge(trainingTypes);
            trainer.setTrainingTypes(trainingTypes);

            String firstName = UUID.randomUUID().toString();
            String lastName = UUID.randomUUID().toString();
            String username = UUID.randomUUID().toString();
            String password = UUID.randomUUID().toString();

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setPassword(password);
            user.setActive(true);
            userDao.save(user);
            trainer.setUser(user);

            session.merge(trainer);

            transaction.commit();
        }
    }

    @Override
    public void checkCredentialsTrainer(String username, String password) {
        try(Session session = HibernateUtil.getSession().openSession()){
            Transaction transaction = session.beginTransaction();

            Trainer trainer = session.createQuery("FROM Trainer t " +
                            "JOIN t.user u " +
                            "WHERE u.username = :username", Trainer.class)
                    .setParameter("username", username).uniqueResult();

            User user = session.createNamedQuery("User.checkCredentials", User.class).
                    setParameter("username", trainer.getUser().getUsername()).
                    setParameter("password", trainer.getUser().getPassword()).uniqueResult();
            System.out.println(user);
            if(user != null && user.getPassword().equalsIgnoreCase(trainer.getUser().getPassword()) ==
            user.getUsername().equalsIgnoreCase(trainer.getUser().getUsername())){
                System.out.println("data entered correctly");
            }else {
                System.out.println("data entered is incorrect");
            }
            transaction.commit();
        }catch (HibernateException e){
            throw new RuntimeException("Incorrect password/username" + e.getMessage());
        }
    }

    @Override
    public void SelectTrainerProfileByUsername(String username) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT t FROM Trainer t " +
                    "WHERE t.user.username = :username", Trainer.class);
            query.setParameter("username", username);

            Object trainer = query.getSingleResult();
            System.out.println(trainer);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void changeTrainerPassword(String firstName, String newPassword) {
        try(Session session = HibernateUtil.getSession().openSession()){
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM Trainer t WHERE t.user.firstName" +
                    "= :firstName", Trainer.class);
            query.setParameter("firstName", firstName);

            Trainer trainer = (Trainer) query.getSingleResult();
            trainer.getUser().setPassword(newPassword);
            session.merge(trainer.getUser());

            transaction.commit();
        }catch (HibernateException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateTrainerProfile(Trainer trainer) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Scanner scanner = new Scanner(System.in);
            Transaction transaction = session.beginTransaction();
            TrainingType[] types = TrainingType.values();
            TrainingType type = types[new Random().nextInt(types.length)];

            Trainer trainer1 = session.get(Trainer.class, trainer.getId());

            Training_Types trainingTypes = new Training_Types();
            trainingTypes.setTrainingTypeName(type.toString());
            trainingTypes.setId(trainer1.getId());
            session.merge(trainingTypes);
            trainer.setTrainingTypes(trainingTypes);

            User user = trainer1.getUser();
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
            session.merge(user);
            trainer.setUser(user);

            session.merge(trainer);
            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void activateTrainer(int id) {
        try(Session session = HibernateUtil.getSession().openSession()){
            Transaction transaction = session.beginTransaction();
            Trainer trainer = session.get(Trainer.class, id);
            trainer.getUser().setActive(true);
            transaction.commit();
        }catch (HibernateException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deactivateTrainer(int id) {
        try(Session session = HibernateUtil.getSession().openSession()){
            Transaction transaction = session.beginTransaction();
            Trainer trainer = session.get(Trainer.class, id);
            trainer.getUser().setActive(false);
            transaction.commit();
        }catch (HibernateException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void manageTrainerStatus(String choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case "ACTIVATE" -> {
                System.out.print("Write id Trainee: ");
                int activeId = scanner.nextInt();
                activateTrainer(activeId);
            }
            case "DEACTIVATE" -> {
                System.out.print("Write id Trainee: ");
                int deactivateId = scanner.nextInt();
                deactivateTrainer(deactivateId);
            }
            default -> throw new IllegalArgumentException("Invalid action type");
        }
    }
    @Override
    public List<Trainer> getNotAssignedActiveTrainersListForTrainee(String traineeUsername) {
        List<Trainer> trainers;
        try (Session session = HibernateUtil.getSession().openSession()) {
            Query query = session.createQuery("SELECT t FROM Trainer t " +
                    "WHERE t NOT IN (SELECT tr FROM Trainee tr JOIN tr.trainers trn WHERE trn.user.username = :traineeUsername) " +
                    "AND t.user.active = true", Trainer.class);
            query.setParameter("traineeUsername", traineeUsername);
            trainers = query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
        return trainers;
    }
}
