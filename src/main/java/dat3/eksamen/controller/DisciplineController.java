package dat3.eksamen.controller;

import dat3.eksamen.entity.Discipline;
import dat3.eksamen.service.DisciplineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public List<Discipline> getDisciplines() {
        return disciplineService.getDisciplines();
    }

    @GetMapping("/{id}")
    public Discipline getDisciplineById(@PathVariable Integer id) {
        return disciplineService.getDisciplineById(id);
    }

    @PostMapping("/add")
    public Discipline addDiscipline(@RequestBody Discipline discipline) {
        return disciplineService.addDiscipline(discipline);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Integer id, @RequestBody Discipline updatedDiscipline) {
        Discipline updated = disciplineService.updateDiscipline(id, updatedDiscipline);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDiscipline(@PathVariable Integer id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.ok("Discipline deleted successfully");
    }
}
