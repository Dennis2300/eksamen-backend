package dat3.eksamen.controller;

import dat3.eksamen.entity.Participants;
import dat3.eksamen.service.ParticipantsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantsController {

    private final ParticipantsService participantsService;

    public ParticipantsController(ParticipantsService participantsService) {
        this.participantsService = participantsService;
    }

    @GetMapping
    public List<Participants> getParticipants() {
        return participantsService.getParticipants();
    }

    @GetMapping("/{id}")
    public Participants getParticipantById(@PathVariable Integer id) {
        return participantsService.getParticipantById(id);
    }

    @PostMapping("/add")
    public Participants addParticipant(@RequestBody Participants participant) {
        return participantsService.addParticipant(participant);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Participants> updateParticipant(@PathVariable Integer id, @RequestBody Participants updatedParticipant) {
        Participants updated = participantsService.updateParticipant(id, updatedParticipant);
        return ResponseEntity.ok(updated);
    }
}
