package peaksoft.service.implService;

import peaksoft.dao.implDao.TrainerDaoImpl;
import peaksoft.entity.Trainer;
import peaksoft.service.TrainerService;

import java.util.List;

public class TrainerServiceImpl implements TrainerService {
    private final TrainerDaoImpl trainerDao = new TrainerDaoImpl();

    @Override
    public void createTrainerProfile(Trainer trainer) {
        trainerDao.createTrainerProfile(trainer);
    }

    @Override
    public void checkCredentialsTrainer(String username, String password) {
        trainerDao.checkCredentialsTrainer(username, password);
    }

    @Override
    public void SelectTrainerProfileByUsername(String username) {
        trainerDao.SelectTrainerProfileByUsername(username);
    }

    @Override
    public void changeTrainerPassword(String firstName, String newPassword) {
        trainerDao.changeTrainerPassword(firstName, newPassword);
    }

    @Override
    public void updateTrainerProfile(Trainer trainer) {
        trainerDao.updateTrainerProfile(trainer);
    }

    @Override
    public void activateTrainer(int id) {
        trainerDao.activateTrainer(id);
    }

    @Override
    public void deactivateTrainer(int id) {
        trainerDao.deactivateTrainer(id);
    }

    @Override
    public void manageTrainerStatus(String choice) {
        trainerDao.manageTrainerStatus(choice);
    }

    @Override
    public List<Trainer> getNotAssignedActiveTrainersListForTrainee(String traineeUsername) {
        return trainerDao.getNotAssignedActiveTrainersListForTrainee(traineeUsername);
    }

}
