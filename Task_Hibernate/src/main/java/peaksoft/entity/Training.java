package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.additional.Id;

import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Training extends Id {
    @ManyToOne(fetch = FetchType.LAZY)
    private Trainee trainee;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Trainer trainer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Training_Types trainingTypes;

    private String TrainingName;
    private Date TrainingDate;
    private Number duration;

    public Training() {
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainee=" + trainee +
                ", trainer=" + trainer +
                ", trainingTypes=" + trainingTypes +
                ", TrainingName='" + TrainingName + '\'' +
                ", TrainingDate=" + TrainingDate +
                ", duration=" + duration +
                '}';
    }
}
