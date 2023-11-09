package peaksoft;

import org.junit.jupiter.api.Test;
import peaksoft.entity.Trainer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TrainerServiceImpl {

    @Test
    public void createTrainerProfile() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        Trainer trainer = new Trainer();
        trainerServiceImpl.createTrainerProfile(trainer);
        verify(trainerServiceImpl).createTrainerProfile(trainer);
    }

    @Test
    public void checkCredentialsTrainer() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        trainerServiceImpl.checkCredentialsTrainer("username", "password");
        verify(trainerServiceImpl).checkCredentialsTrainer("username", "password");
    }

    @Test
    public void SelectTrainerProfileByUsername() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        trainerServiceImpl.SelectTrainerProfileByUsername("username");
        verify(trainerServiceImpl).SelectTrainerProfileByUsername("username");
    }

    @Test
    public void changeTrainerPassword() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        trainerServiceImpl.changeTrainerPassword("firstName", "newPassword");
        verify(trainerServiceImpl).changeTrainerPassword("firstName", "newPassword");
    }

    @Test
    public void updateTrainerProfile() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        Trainer trainer = new Trainer();
        trainerServiceImpl.updateTrainerProfile(trainer);
        verify(trainerServiceImpl).updateTrainerProfile(trainer);
    }

    @Test
    public void activateTrainer() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        trainerServiceImpl.activateTrainer(1);
        verify(trainerServiceImpl).activateTrainer(1);
    }

    @Test
    public void deactivateTrainer() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        trainerServiceImpl.deactivateTrainer(1);
        verify(trainerServiceImpl).deactivateTrainer(1);
    }

    @Test
    public void manageTrainerStatus() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        trainerServiceImpl.manageTrainerStatus("choice");
        verify(trainerServiceImpl).manageTrainerStatus("choice");
    }

    @Test
    public void getNotAssignedActiveTrainersListForTrainee() {
        peaksoft.service.implService.TrainerServiceImpl trainerServiceImpl = mock(peaksoft.service.implService.TrainerServiceImpl.class);
        trainerServiceImpl.getNotAssignedActiveTrainersListForTrainee("traineeUsername");
        verify(trainerServiceImpl).getNotAssignedActiveTrainersListForTrainee("traineeUsername");
    }


}
