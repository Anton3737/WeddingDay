package WeddingDayRegister.WeddingDay.repository;

import WeddingDayRegister.WeddingDay.model.RSVPForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RsvpRepository extends JpaRepository<RSVPForm, Long> {


}
