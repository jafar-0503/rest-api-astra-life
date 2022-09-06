package example.astralifetesting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import example.astralifetesting.auditable.Auditable;
import lombok.AllArgsConstructor;
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

    public Salaries(Integer salary){
        this.salary = salary;
    }
}
