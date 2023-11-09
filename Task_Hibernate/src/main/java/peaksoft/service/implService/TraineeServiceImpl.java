package peaksoft.service.implService;

import peaksoft.dao.implDao.TraineeDaoImpl;
import peaksoft.entity.Trainee;
import peaksoft.service.TraineeService;

import java.util.List;

public class TraineeServiceImpl implements TraineeService {
    private final TraineeDaoImpl traineeDAO = new TraineeDaoImpl() ;

    @Override
    public Trainee getTraineeById(long id) {
        return traineeDAO.getTraineeById(id);
    }

    @Override
    public void createTraineeProfile(Trainee trainee) {
        traineeDAO.createTraineeProfile(trainee);
    }

    @Override
    public void checkCredentialsTrainee(String username, String password) {
        traineeDAO.checkCredentialsTrainee(username, password);
    }

    @Override
    public void SelectTraineeProfileByUsername(String username) {
        traineeDAO.SelectTraineeProfileByUsername(username);
    }

    @Override
    public void changeTraineePassword(String firstName, String newPassword) {
        traineeDAO.changeTraineePassword(firstName, newPassword);
    }

    @Override
    public void updateTraineeProfile(Trainee trainee) {
        traineeDAO.updateTraineeProfile(trainee);
    }

    @Override
    public void activateTrainee(int id) {
        traineeDAO.activateTrainee(id);
    }

    @Override
    public void deactivateTrainee(int id) {
        traineeDAO.deactivateTrainee(id);
    }

    @Override
    public void manageTraineeStatus(String choice) {
        traineeDAO.manageTraineeStatus(choice);
    }

    @Override
    public void deleteTraineeProfileByUsername(String username) {
        traineeDAO.deleteTraineeProfileByUsername(username);
    }

    @Override
    public void updateTraineeTrainersList(String traineeUsername, List<Long> trainerIds) {
        traineeDAO.updateTraineeTrainersList(traineeUsername, trainerIds);
    }
}
