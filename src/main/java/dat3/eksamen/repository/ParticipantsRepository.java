package dat3.eksamen.repository;

import dat3.eksamen.entity.Participants;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ParticipantsRepository extends JpaRepository<Participants, Integer>{
    Participants findByFirstNameAndLastName(String firstName, String lastName);
}
