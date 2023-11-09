package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.additional.Id;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Trainee extends Id {
    private Date date_of_birth;
    private String address;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private User user;
    @ManyToMany
    @JoinTable(
            name = "trainee_trainer",
            joinColumns = @JoinColumn(name = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    private List<Trainer> trainers;

    public Trainee() {
    }

    @Override
    public String toString() {
        return "Trainee{" + "date_of_birth=" + date_of_birth + ", address='" + address + '\'' + ", user=" + user + '}';
    }
}
