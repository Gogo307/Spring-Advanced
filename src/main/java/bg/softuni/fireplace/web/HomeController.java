package bg.softuni.fireplace.web;

import bg.softuni.fireplace.model.userDetails.FireplaceUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if (userDetails instanceof FireplaceUserDetails fireplaceUserDetails) {
            model.addAttribute("welcomeMessage", fireplaceUserDetails.getUsername());
        } else {
            model.addAttribute("welcomeMessage", "Stranger");
        }


        return "index";
    }
}