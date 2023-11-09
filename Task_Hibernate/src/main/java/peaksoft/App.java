package peaksoft;

import lombok.extern.log4j.Log4j;
import peaksoft.entity.Trainee;
import peaksoft.service.TraineeService;
import peaksoft.service.implService.TraineeServiceImpl;


import java.util.logging.LogManager;
import java.util.logging.Logger;

@Log4j
public class App {
    private static final Logger logger = LogManager.getLogManager().getLogger(App.class.getName());

    public static void main( String[] args ) {

        TraineeService traineeService = new TraineeServiceImpl();

        Trainee trainee = new Trainee();
        trainee.setDate_of_birth(null);
        trainee.setAddress("Bishkek");
        traineeService.createTraineeProfile(trainee);
        logger.info("Trainee created successfully");


    }
}
