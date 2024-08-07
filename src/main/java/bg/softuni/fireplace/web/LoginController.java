package bg.softuni.fireplace.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class LoginController {

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login() {
        return "system-login";
    }
}
