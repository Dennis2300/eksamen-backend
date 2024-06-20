package dat3.eksamen.repository;

import dat3.eksamen.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    Discipline findByDisciplineName(String disciplineName);
}
