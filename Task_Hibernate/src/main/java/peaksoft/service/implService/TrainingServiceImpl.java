package peaksoft.service.implService;

import peaksoft.dao.TrainingDao;
import peaksoft.dao.implDao.TrainingDaoImpl;
import peaksoft.entity.Training;
import peaksoft.service.TrainingService;


public class TrainingServiceImpl implements TrainingService {
    private TrainingDao trainingDao = new TrainingDaoImpl() ;


    @Override
    public void getTraineeTrainingsByCriteria(String traineeUsername, String criteria) {
        trainingDao.getTraineeTrainingsByCriteria(traineeUsername, criteria);
    }

    @Override
    public void addTraining(Training training, Long traineeId, Long trainerId, Long trainingTypesId) {
        trainingDao.addTraining(training, traineeId, trainerId, trainingTypesId);
    }

    @Override
    public void getTrainerTrainingsByCriteria(String traineeUsername, String criteria) {
        trainingDao.getTrainerTrainingsByCriteria(traineeUsername, criteria);
    }
}
