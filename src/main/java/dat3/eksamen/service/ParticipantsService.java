package dat3.eksamen.service;

import dat3.eksamen.entity.Participants;
import dat3.eksamen.repository.ParticipantsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class ParticipantsService {

    private final ParticipantsRepository participantsRepository;

    public ParticipantsService(ParticipantsRepository participantsRepository) {
        this.participantsRepository = participantsRepository;
    }

    public List<Participants> getParticipants() {
        try {
            return participantsRepository.findAll();
        } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not find participants");
        }
    }

    public Participants getParticipantById(Integer id) {
        try {
            return participantsRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not find the given participant");
        }
    }

    public Participants addParticipant(Participants participant) {
        try {
            return participantsRepository.save(participant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not add the given participant");
        }
    }

    public Participants updateParticipant(Integer id, Participants updatedParticipant) {
        return participantsRepository.findById(id).map(participant -> {
            participant.setFirstName(updatedParticipant.getFirstName());
            participant.setLastName(updatedParticipant.getLastName());
            participant.setAge(updatedParticipant.getAge());
            participant.setGender(updatedParticipant.getGender());
            participant.setClub(updatedParticipant.getClub());
            return participantsRepository.save(participant);
        }).orElseGet(() -> {
            updatedParticipant.setId(id);
            return participantsRepository.save(updatedParticipant);
        });
    }

    public void deleteParticipant(Integer id) {
        try {
            participantsRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not delete the given participant");
        }
    }

}
