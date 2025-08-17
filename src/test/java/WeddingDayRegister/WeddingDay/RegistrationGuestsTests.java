//package WeddingDayRegister.WeddingDay;
//
//import WeddingDayRegister.WeddingDay.model.RSVPForm;
//import WeddingDayRegister.WeddingDay.service.RsvpDao;
//import WeddingDayRegister.WeddingDay.controller.RegistrationGuestsController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.ModelAndView;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class RegistrationGuestsTests {
//    @InjectMocks
//    private RegistrationGuestsController controller;
//
//    @Mock
//    private RsvpDao rsvpDao;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this); // Ініціалізуємо Mockito
//    }
//
//    @Test
//    void addGuest_ShouldAddNewGuest() {
//        // Створюємо новий гість з даними
//        RSVPForm newGuest = new RSVPForm();
//        newGuest.setName("John");
//        newGuest.setSecondName("Doe");
//        newGuest.setPresence("Yes");
//        newGuest.setGuest(Arrays.asList("Alice", "Bob"));
//        newGuest.setBeverage(Arrays.asList("Water", "Wine"));
//
//        // Налаштовуємо поведінку для dao
//        when(rsvpDao.addGuest(any(RSVPForm.class))).thenReturn(newGuest);
//
//        // Викликаємо метод контролера для додавання гостя
//        String result = controller.addGuest("John", "Doe", "Yes", Arrays.asList("Alice", "Bob"), Arrays.asList("Water", "Wine"));
//
//        // Перевіряємо, що ми перенаправляємо на головну сторінку
//        assertEquals("redirect:/", result);
//        verify(rsvpDao, times(1)).addGuest(any(RSVPForm.class)); // Перевіряємо, що dao був викликаний один раз
//    }
//
//    @Test
//    void getAllGuests_ShouldReturnModelAndView() {
//        // Створюємо список гостей
//        List<RSVPForm> guests = Arrays.asList(new RSVPForm(), new RSVPForm());
//        when(rsvpDao.findAllGuests()).thenReturn(guests); // Налаштовуємо поведінку dao
//
//        // Викликаємо метод контролера для отримання всіх гостей
//        ModelAndView modelAndView = controller.getAllGuests();
//
//        // Перевіряємо, що модель і вид не пусті
//        assertNotNull(modelAndView);
//        assertEquals("guestList", modelAndView.getViewName()); // Перевіряємо, що відображається правильний вид
//        assertEquals(guests, modelAndView.getModel().get("guestsList")); // Перевіряємо, що список гостей правильно передається в модель
//    }
//
//    @Test
//    void updateGuest_ShouldUpdateExistingGuest() {
//        // Створюємо існуючого гостя для оновлення
//        RSVPForm existingGuest = new RSVPForm();
//        existingGuest.setId(1L);
//        existingGuest.setName("John");
//        existingGuest.setSecondName("Doe");
//
//        // Налаштовуємо поведінку dao для отримання та оновлення гостя
//        when(rsvpDao.getById(1L)).thenReturn(existingGuest);
//        when(rsvpDao.update(1L, existingGuest)).thenReturn(existingGuest);
//
//        // Викликаємо метод контролера для оновлення даних гостя
//        String result = controller.updateGuest(1L, "Jane", "Doe", "Yes", Arrays.asList(), Arrays.asList());
//
//        // Перевіряємо, що перенаправляємо на список гостей
//        assertEquals("redirect:/guests/list", result);
//        verify(rsvpDao, times(1)).update(1L, existingGuest); // Перевіряємо, що dao був викликаний один раз
//    }
//
//    @Test
//    void deleteGuest_ShouldDeleteExistingGuest() {
//        // Створюємо існуючого гостя для видалення
//        RSVPForm existingGuest = new RSVPForm();
//        existingGuest.setId(1L);
//
//        // Налаштовуємо поведінку dao для отримання гостя
//        when(rsvpDao.getById(1L)).thenReturn(existingGuest);
//
//        // Викликаємо метод контролера для видалення гостя
//        String result = controller.deleteGuest(1L);
//
//        // Перевіряємо, що перенаправляємо на список гостей
//        assertEquals("redirect:/guests/list", result);
//        verify(rsvpDao, times(1)).deleteById(1L); // Перевіряємо, що dao був викликаний один раз
//    }
//}
