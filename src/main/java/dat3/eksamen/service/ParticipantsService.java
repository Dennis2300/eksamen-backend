package dat3.eksamen.service;

import dat3.eksamen.dto.ParticipantDto;
import dat3.eksamen.entity.Discipline;
import dat3.eksamen.entity.Participants;
import dat3.eksamen.repository.DisciplineRepository;
import dat3.eksamen.repository.ParticipantsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class ParticipantsService {

    private final ParticipantsRepository participantsRepository;
    private final DisciplineRepository disciplineRepository;

    public ParticipantsService(ParticipantsRepository participantsRepository, DisciplineRepository disciplineRepository) {
        this.participantsRepository = participantsRepository;
        this.disciplineRepository = disciplineRepository;
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

    @Transactional
    public Participants addParticipant(ParticipantDto participantDTO) {
        Participants participant = new Participants();
        participant.setFirstName(participantDTO.getFirstName());
        participant.setLastName(participantDTO.getLastName());
        participant.setAge(participantDTO.getAge());
        participant.setGender(participantDTO.getGender());
        participant.setClub(participantDTO.getClub());

        Set<Discipline> disciplines = new HashSet<>();
        for (String disciplineName : participantDTO.getDisciplines()) {
            Discipline discipline = disciplineRepository.findByDisciplineName(disciplineName);
            if (discipline != null) {
                disciplines.add(discipline);
            } else {
                // Handle discipline not found case
            }
        }
        participant.setDisciplines(disciplines);

        return participantsRepository.save(participant);
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
