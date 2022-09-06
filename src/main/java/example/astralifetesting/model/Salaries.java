package example.astralifetesting.model;

import example.astralifetesting.auditable.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "salaries")
public class Salaries extends Auditable<String> {
    @Column(name = "salary", length = 11)
    private Integer salary;

    public Salaries(Integer salary){
        this.salary = salary;
    }
}
