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
//@AllArgsConstructor
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
    @JsonIgnore
    private Set<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "dept_manager",
            joinColumns = { @JoinColumn(name = "dept_id")},
            inverseJoinColumns = { @JoinColumn(name = "emp_id")})
    @JsonIgnore
    private Set<Employee> emp;

    public Department(String dept_name){
        this.dept_name = dept_name;
    }
}
