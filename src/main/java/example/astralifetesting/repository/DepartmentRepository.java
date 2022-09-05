package example.astralifetesting.repository;

import example.astralifetesting.model.Department;
import example.astralifetesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
