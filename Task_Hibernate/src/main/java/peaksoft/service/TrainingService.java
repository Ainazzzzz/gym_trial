package peaksoft.service;

import peaksoft.entity.Training;

import java.util.List;

public interface TrainingService {
    void getTraineeTrainingsByCriteria(String traineeUsername, String criteria);
    void addTraining(Training training, Long traineeId, Long trainerId, Long trainingTypesId);
    void getTrainerTrainingsByCriteria(String traineeUsername, String criteria);
}
