package peaksoft.service;

import peaksoft.entity.Trainee;

import java.util.List;

public interface TraineeService {
    Trainee getTraineeById(long id);
    void createTraineeProfile(Trainee trainee);
    void checkCredentialsTrainee(String username, String password);
    void SelectTraineeProfileByUsername(String username);
    void changeTraineePassword(String firstName, String newPassword);
    void updateTraineeProfile(Trainee trainee);
    void activateTrainee(int id);
    void deactivateTrainee(int id);
    void manageTraineeStatus(String choice);
    void deleteTraineeProfileByUsername(String username);
    void updateTraineeTrainersList(String traineeUsername, List<Long> trainerIds);
}
