package WeddingDayRegister.WeddingDay;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import WeddingDayRegister.WeddingDay.model.RSVPForm;
import WeddingDayRegister.WeddingDay.repository.RsvpRepository;
import WeddingDayRegister.WeddingDay.service.RsvpDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RsvpServiceTest {
    @InjectMocks
    private RsvpDaoImpl rsvpDao;

    @Mock
    private RsvpRepository rsvpRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Ініціалізуємо Mockito
    }

    @Test
    void findAllGuests_ShouldReturnListOfGuests() {
        // Створюємо список гостей
        RSVPForm guest1 = new RSVPForm();
        RSVPForm guest2 = new RSVPForm();
        RSVPForm guest3 = new RSVPForm();
        RSVPForm guest4 = new RSVPForm();

        when(rsvpRepository.findAll()).thenReturn(Arrays.asList(guest1, guest2, guest3, guest4)); // Налаштовуємо поведінку репозиторія

        // Викликаємо метод dao для отримання всіх гостей
        List<RSVPForm> result = rsvpDao.findAllGuests();

        // Перевіряємо, що результат не пустий і містить правильну кількість гостей
        assertNotNull(result);
        assertEquals(4, result.size());
        verify(rsvpRepository, times(1)).findAll(); // Перевіряємо, що репозиторій був викликаний один раз
    }

    @Test
    void addGuest_ShouldSaveGuest() {
        // Створюємо нового гостя
        RSVPForm newGuest = new RSVPForm();
        when(rsvpRepository.save(any(RSVPForm.class))).thenReturn(newGuest); // Налаштовуємо поведінку репозиторія

        // Викликаємо метод dao для додавання гостя
        RSVPForm result = rsvpDao.addGuest(newGuest);

        // Перевіряємо, що результат не пустий
        assertNotNull(result);
        verify(rsvpRepository, times(1)).save(newGuest); // Перевіряємо, що репозиторій був викликаний один раз
    }

    @Test
    void deleteById_ShouldDeleteGuest() {
        Long guestId = 1L;
        doNothing().when(rsvpRepository).deleteById(guestId); // Налаштовуємо поведінку репозиторія для видалення

        // Викликаємо метод dao для видалення гостя
        rsvpDao.deleteById(guestId);

        verify(rsvpRepository, times(1)).deleteById(guestId); // Перевіряємо, що репозиторій був викликаний один раз
    }

    @Test
    void getById_ShouldReturnGuest() {
        // Створюємо нового гостя
        RSVPForm guest = new RSVPForm();
        when(rsvpRepository.findById(1L)).thenReturn(Optional.of(guest)); // Налаштовуємо поведінку репозиторія

        // Викликаємо метод dao для отримання гостя за ID
        RSVPForm result = rsvpDao.getById(1L);

        // Перевіряємо, що результат не пустий
        assertNotNull(result);
        verify(rsvpRepository, times(1)).findById(1L); // Перевіряємо, що репозиторій був викликаний один раз
    }

    @Test
    void update_ShouldUpdateExistingGuest() {
        // Створюємо існуючого гостя для оновлення
        RSVPForm existingGuest = new RSVPForm();
        when(rsvpRepository.findById(1L)).thenReturn(Optional.of(existingGuest)); // Налаштовуємо поведінку репозиторія
        when(rsvpRepository.save(any(RSVPForm.class))).thenReturn(existingGuest); // Налаштовуємо поведінку репозиторія для збереження

        // Викликаємо метод dao для оновлення даних гостя
        RSVPForm result = rsvpDao.update(1L, existingGuest);

        // Перевіряємо, що результат не пустий
        assertNotNull(result);
        verify(rsvpRepository, times(1)).save(existingGuest); // Перевіряємо, що репозиторій був викликаний один раз
    }
}
