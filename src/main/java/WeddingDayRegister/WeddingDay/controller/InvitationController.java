package WeddingDayRegister.WeddingDay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvitationController {

    @GetMapping("/")
    public String showInvitation() {
        return "invitation"; // This should match the name of your HTML file without the extension
    }
}
