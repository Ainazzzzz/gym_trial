package peaksoft.dao.implDao;

import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.config.HibernateUtil;
import peaksoft.dao.TrainingDao;
import peaksoft.entity.Trainee;
import peaksoft.entity.Trainer;
import peaksoft.entity.Training;
import peaksoft.entity.Training_Types;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class TrainingDaoImpl implements TrainingDao {
    @Override
    public void getTraineeTrainingsByCriteria(String traineeUsername, String criteria) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Query query = session.createQuery("SELECT t FROM Training t " +
                    "WHERE t.trainee.user.username = :traineeUsername " +
                    "AND t.trainingTypes.trainingTypeName = :criteria", Training.class);
            query.setParameter("traineeUsername", traineeUsername);
            query.setParameter("criteria", criteria);
            Trainee trainee = (Trainee) query.getSingleResult();
            System.out.println(trainee);
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public void getTrainerTrainingsByCriteria(String trainerUsername, String criteria) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Query query = session.createQuery("SELECT t FROM Training t " +
                    "WHERE t.trainer.user.username = :trainerUsername " +
                    "AND t.trainingTypes.trainingTypeName = :criteria", Training.class);
            query.setParameter("trainerUsername", trainerUsername);
            query.setParameter("criteria", criteria);
            Trainer trainer = (Trainer) query.getSingleResult();
            System.out.println(trainer);
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public void addTraining(Training training, Long traineeId, Long trainerId, Long trainingTypesId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            Trainee trainee = session.get(Trainee.class, traineeId);
            Trainer trainer = session.get(Trainer.class, trainerId);
            Training_Types trainingTypes = session.get(Training_Types.class, trainingTypesId);

            training = new Training();
            training.setTrainee(trainee);
            training.setTrainer(trainer);
            training.setTrainingTypes(trainingTypes);

            String uniqueID = UUID.randomUUID().toString();
            String trainingName = "Training_" + uniqueID.substring(0, 8);
            training.setTrainingName(trainingName);

            Random random = new Random();
            long millis = System.currentTimeMillis() - (long) random.nextInt(365) * 24 * 60 * 60 * 1000;
            Date trainingDate = new Date(millis);
            training.setTrainingDate(trainingDate);

            int duration = random.nextInt(120);
            training.setDuration(duration);

            session.merge(training);

            transaction.commit();
        } catch (HibernateException e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }
}
