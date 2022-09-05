package example.astralifetesting.repository;

import example.astralifetesting.model.Salaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salaries, Long> {
}
