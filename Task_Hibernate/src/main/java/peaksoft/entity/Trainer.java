package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.additional.Id;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Trainer extends Id {
    @ManyToOne(fetch = FetchType.LAZY)
    private Training_Types trainingTypes;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToMany(mappedBy = "trainers")
    private List<Trainee> trainees;

    public Trainer() {
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "trainingTypes=" + trainingTypes +
                ", user=" + user +
                '}';
    }
}
