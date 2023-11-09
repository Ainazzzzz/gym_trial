package peaksoft;

import org.junit.jupiter.api.Test;
import peaksoft.entity.Training;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TrainingServiceImplTest {

    @Test
    public void getTraineeTrainingsByCriteria() {
        peaksoft.service.implService.TrainingServiceImpl trainingServiceImpl = mock(peaksoft.service.implService.TrainingServiceImpl.class);
        trainingServiceImpl.getTraineeTrainingsByCriteria("traineeUsername", "criteria");
        verify(trainingServiceImpl).getTraineeTrainingsByCriteria("traineeUsername", "criteria");

    }

    @Test
    public void addTraining() {
        peaksoft.service.implService.TrainingServiceImpl trainingServiceImpl = mock(peaksoft.service.implService.TrainingServiceImpl.class);
        Training training = new Training();
        trainingServiceImpl.addTraining(training, 1L, 1L, 1L);
        verify(trainingServiceImpl).addTraining(training, 1L, 1L, 1L);
    }

    @Test
    public void getTrainerTrainingsByCriteria() {
        peaksoft.service.implService.TrainingServiceImpl trainingServiceImpl = mock(peaksoft.service.implService.TrainingServiceImpl.class);
        trainingServiceImpl.getTrainerTrainingsByCriteria("traineeUsername", "criteria");
        verify(trainingServiceImpl).getTrainerTrainingsByCriteria("traineeUsername", "criteria");
    }
}
