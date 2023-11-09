package peaksoft.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.additional.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Training_Types extends Id{
    private String TrainingTypeName;

    public Training_Types() {
    }
}
