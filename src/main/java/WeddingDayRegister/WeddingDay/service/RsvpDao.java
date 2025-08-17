package WeddingDayRegister.WeddingDay.service;

import WeddingDayRegister.WeddingDay.model.RSVPForm;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RsvpDao {

    @Query("SELECT r FROM RSVPForm r ORDER BY r.name")
    List<RSVPForm> findAllGuests();

    RSVPForm addGuest(RSVPForm rsvpForm);

    void deleteById(Long id);

    RSVPForm getById(Long id);

    RSVPForm update(Long id, RSVPForm updateNewsCreator);


}
