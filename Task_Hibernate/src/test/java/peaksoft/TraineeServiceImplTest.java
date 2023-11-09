package peaksoft;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.config.HibernateUtil;
import peaksoft.dao.UserDao;
import peaksoft.entity.Trainee;
import peaksoft.entity.User;
import peaksoft.service.TraineeService;

import peaksoft.service.UserService;
import peaksoft.service.implService.TraineeServiceImpl;
import peaksoft.service.implService.UserServiceImpl;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class TraineeServiceImplTest {

    @Test
    public void testCreateTraineeProfile() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        Trainee trainee = new Trainee();
        traineeServiceImpl.createTraineeProfile(trainee);
        verify(traineeServiceImpl).createTraineeProfile(trainee);
    }

    @Test
    public void testCheckCredentialsTrainee() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.checkCredentialsTrainee("username", "password");
        verify(traineeServiceImpl).checkCredentialsTrainee("username", "password");
    }

    @Test
    public void testSelectTraineeProfileByUsername() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.SelectTraineeProfileByUsername("username");
        verify(traineeServiceImpl).SelectTraineeProfileByUsername("username");
    }

    @Test
    public void testChangeTraineePassword() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.changeTraineePassword("firstName", "newPassword");
        verify(traineeServiceImpl).changeTraineePassword("firstName", "newPassword");
    }

    @Test
    public void testUpdateTraineeProfile() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        Trainee trainee = new Trainee();
        traineeServiceImpl.updateTraineeProfile(trainee);
        verify(traineeServiceImpl).updateTraineeProfile(trainee);
    }

    @Test
    public void testActivateTrainee() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.activateTrainee(1);
        verify(traineeServiceImpl).activateTrainee(1);
    }

    @Test
    public void testDeactivateTrainee() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.deactivateTrainee(1);
        verify(traineeServiceImpl).deactivateTrainee(1);
    }

    @Test
    public void testManageTraineeStatus() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.manageTraineeStatus("choice");
        verify(traineeServiceImpl).manageTraineeStatus("choice");
    }

    @Test
    public void testDeleteTraineeProfileByUsername() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.deleteTraineeProfileByUsername("username");
        verify(traineeServiceImpl).deleteTraineeProfileByUsername("username");
    }

    @Test
    public void testUpdateTraineeTrainersList() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.updateTraineeTrainersList("traineeUsername", null);
        verify(traineeServiceImpl).updateTraineeTrainersList("traineeUsername", null);
    }

    @Test
    public void testGetTraineeById() {
        TraineeServiceImpl traineeServiceImpl = mock(TraineeServiceImpl.class);
        traineeServiceImpl.getTraineeById(1);
        verify(traineeServiceImpl).getTraineeById(1);
    }



}