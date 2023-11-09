package peaksoft.service;

import peaksoft.entity.Trainee;
import peaksoft.entity.Trainer;

import java.util.List;

public interface TrainerService {
    void createTrainerProfile(Trainer trainer);
    void checkCredentialsTrainer(String username, String password);
    void SelectTrainerProfileByUsername(String username);
    void changeTrainerPassword(String firstName, String newPassword);
    void updateTrainerProfile(Trainer trainer);
    void activateTrainer(int id);
    void deactivateTrainer(int id);
    void manageTrainerStatus(String choice);
    List<Trainer> getNotAssignedActiveTrainersListForTrainee(String traineeUsername);
}
