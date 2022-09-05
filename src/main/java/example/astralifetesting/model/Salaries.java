package example.astralifetesting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import example.astralifetesting.auditable.Auditable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "salaries")
public class Salaries extends Auditable<String> {
    @Column(name = "salary", length = 11)
    private Integer salary;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "salary_id")
    @JsonBackReference
    private Employee employee;

    public Salaries(Integer salary){
        this.salary = salary;
    }
}
