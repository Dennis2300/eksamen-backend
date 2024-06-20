package dat3.eksamen.service;

import dat3.eksamen.entity.Discipline;
import dat3.eksamen.repository.DisciplineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public List<Discipline> getDisciplines() {
        try {
            return disciplineRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find disciplines");
        }
    }

    public Discipline getDisciplineById(int id) {
        try {
            return disciplineRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find discipline with id: " + id);
        }
    }

    public Discipline addDiscipline(Discipline discipline) {
        try {
            return disciplineRepository.save(discipline);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not add discipline");
        }
    }

    public Discipline updateDiscipline(Integer id, Discipline updatedDiscipline) {
        return disciplineRepository.findById(id).map(discipline -> {
            discipline.setDisciplineName(updatedDiscipline.getDisciplineName());
            return disciplineRepository.save(discipline);
        }).orElseGet(() -> {
            updatedDiscipline.setId(id);
            return disciplineRepository.save(updatedDiscipline);
        });
    }

    public void deleteDiscipline(int id) {
        try {
            disciplineRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not delete discipline with id: " + id);
        }
    }
}


