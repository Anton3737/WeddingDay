package WeddingDayRegister.WeddingDay;

import WeddingDayRegister.WeddingDay.model.RSVPForm;
import WeddingDayRegister.WeddingDay.service.RsvpDao;
import WeddingDayRegister.WeddingDay.controller.RegistrationGuestsController; // Імпортуємо контролер
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

public class RegistrationGuestsTests {

    @Mock
    private RsvpDao rsvpDao;

    @InjectMocks
    private RegistrationGuestsController registrationGuestsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddGuestSuccess() {
        // Given
        String name = "John";
        String secondName = "Doe";
        String presence = "yes";
        List<String> guest = new ArrayList<>(List.of("Jane"));
        List<String> beverage = new ArrayList<>(List.of("Water"));

        doNothing().when(rsvpDao).addGuest(any(RSVPForm.class)); // Мокаємо на будь-який RSVPForm

        // When
        String result = registrationGuestsController.addGuest(name, secondName, presence, guest, beverage);

        // Then
        assertEquals("redirect:/", result);
        verify(rsvpDao).addGuest(any(RSVPForm.class));  // Перевіряємо виклик з будь-яким RSVPForm
    }

    @Test
    public void testAddGuestMissingArguments() {
        // Given
        String name = null;
        String secondName = "Doe";
        String presence = "yes";
        List<String> guest = new ArrayList<>(List.of("Jane"));
        List<String> beverage = new ArrayList<>(List.of("Water"));

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                registrationGuestsController.addGuest(name, secondName, presence, guest, beverage)
        );
        assertEquals("No all arguments added", exception.getMessage());
    }

    @Test
    public void testAddGuestThrowsRuntimeException() {
        // Given
        String name = "John";
        String secondName = "Doe";
        String presence = "yes";
        List<String> guest = new ArrayList<>(List.of("Jane"));
        List<String> beverage = new ArrayList<>(List.of("Water"));

        when(rsvpDao.addGuest(any(RSVPForm.class))).thenThrow(new RuntimeException("Database error"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                registrationGuestsController.addGuest(name, secondName, presence, guest, beverage)
        );
        assertTrue(exception.getMessage().contains("Failed to process data"));
    }
}
