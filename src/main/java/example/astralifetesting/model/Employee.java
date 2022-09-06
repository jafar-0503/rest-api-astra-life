package example.astralifetesting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import example.astralifetesting.auditable.Auditable;
import example.astralifetesting.property.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends Auditable<String> {
    @Column(name = "birth_date")
    private Date birth_date;

    @Column(name = "first_name", length = 14)
    private String first_name;

    @Column(name = "last_name", length = 16)
    private String last_name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "hire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hire_date;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE
                })
    @JoinTable(
            name = "dept_emp",
            joinColumns = { @JoinColumn(name = "emp_id")},
            inverseJoinColumns = { @JoinColumn(name = "dept_id")})
    @JsonIgnore
    private Set<Department> dept;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL,
                    CascadeType.PERSIST},
            mappedBy = "emp")
    @JsonIgnore
    private Set<Department> dept_manager;

    public Employee(Date birth_date, String first_name, String last_name, Enum gender, Date hire_date, Set<Department> dept, Set<Salaries> salaries, Set<Department> dept_manager) {
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender= (Gender) gender;
        this.hire_date=hire_date;
        this.dept = dept;
        this.dept_manager=dept_manager;
    }
}
