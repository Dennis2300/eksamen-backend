package dat3.eksamen.service;

import dat3.eksamen.entity.Participants;
import dat3.eksamen.repository.ParticipantsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    public Participants getParticipantById(int id) {
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
}
