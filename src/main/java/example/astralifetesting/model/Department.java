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
@Table(name = "department")
public class Department extends Auditable<String> {
    @Column(name = "dept_name", length = 40)
    private String dept_name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.ALL,
                CascadeType.PERSIST},
            mappedBy = "dept")
    @JsonBackReference
    private Set<Employee> employees;

    public Department(String dept_name){
        this.dept_name = dept_name;
    }
}
