package WeddingDayRegister.WeddingDay;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import WeddingDayRegister.WeddingDay.model.RSVPForm;
import WeddingDayRegister.WeddingDay.repository.RsvpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

public class RsvpServiceTest {

    @InjectMocks
    private RSVPForm rsvpForm;

    @Mock
    private RsvpRepository rsvpRepository; // Припустимо, ви маєте репозиторій для збереження

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRsvp() {
        RSVPForm rsvp = new RSVPForm();
        rsvp.setName("Іван");
        rsvp.setSecondName("Петренко");
        rsvp.setPresence("Так, я прийду!");
        rsvp.setGuest(List.of("Буду одиноким"));
        rsvp.setBeverage(List.of("Червоне вино", "Шампанське"));

        rsvpRepository.save(rsvp);

        verify(rsvpRepository, times(1)).save(rsvp);
    }




    @Test
    void testSaveRsvpWithEmptyBeverageList() {
        RSVPForm rsvp = new RSVPForm();
        rsvp.setName("Олександр");
        rsvp.setSecondName("Коваленко");
        rsvp.setPresence("Так, я прийду!");
        rsvp.setGuest(List.of("Друг"));
        rsvp.setBeverage(Collections.emptyList()); // Порожній список

        rsvpRepository.save(rsvp);

        verify(rsvpRepository, times(1)).save(rsvp);
        assertTrue(rsvp.getBeverage().isEmpty());
    }

}
