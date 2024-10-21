package WeddingDayRegister.WeddingDay.controller;

import WeddingDayRegister.WeddingDay.model.RSVPForm;
import WeddingDayRegister.WeddingDay.service.RsvpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping("/guests")
public class RegistrationGuestsController {

    @Autowired
    private RsvpDao rsvpDao;

    // Додавання нового гостя через форму
    @PostMapping("/addGuest")
    public String addGuest(
            @RequestParam String name,
            @RequestParam String secondName,
            @RequestParam String presence,
            @RequestParam List<String> guest,
            @RequestParam List<String> beverage
    ) {
        try {
            RSVPForm newGuest = new RSVPForm();
            newGuest.setName(name);
            newGuest.setSecondName(secondName);
            newGuest.setPresence(presence);
            newGuest.setGuest(guest);
            newGuest.setBeverage(beverage);

            if (name == null || secondName == null || presence == null || guest == null || beverage == null) {
                throw new IllegalArgumentException("No all arguments added");
            }

            rsvpDao.addGuest(newGuest);
            return "redirect:/"; // перенаправлення на список гостей
        } catch (Exception e) {
            throw new RuntimeException("Failed to process data ", e);
        }
    }

//     Отримання списку всіх гостей
@GetMapping("/list")
public ModelAndView getAllGuests() {
    ModelAndView modelAndView = new ModelAndView("guestList"); // відповідний HTML файл "guestList.html"
    modelAndView.addObject("guestsList", rsvpDao.findAllGuests()); // Передаємо список гостей на сторінку
    return modelAndView;
}


    // Оновлення даних гостя
    @PostMapping("/updateGuest")
    public String updateGuest(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String secondName,
            @RequestParam String presence,
            @RequestParam List<String> guest,
            @RequestParam List<String> beverage
    ) {
        try {
            RSVPForm existingGuest = rsvpDao.getById(id);
            if (existingGuest != null) {
                existingGuest.setName(name);
                existingGuest.setSecondName(secondName);
                existingGuest.setPresence(presence);
                existingGuest.setGuest(guest);
                existingGuest.setBeverage(beverage);

                rsvpDao.update(id, existingGuest);
                return "redirect:/guests/list";
            } else {
                throw new RuntimeException("Guest not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update data", e);
        }
    }

    // Видалення гостя
    @PostMapping("/deleteGuest")
    public String deleteGuest(@RequestParam Long id) {
        try {
            RSVPForm existingGuest = rsvpDao.getById(id);
            if (existingGuest != null) {
                rsvpDao.deleteById(id);
                return "redirect:/guests/list";
            } else {
                throw new RuntimeException("Guest not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete guest", e);
        }
    }

}
