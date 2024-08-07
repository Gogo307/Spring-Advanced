package bg.softuni.fireplace.web;

import bg.softuni.fireplace.model.dto.UserRegistrationDTO;
import bg.softuni.fireplace.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerDTO")
    public UserRegistrationDTO registerDTO() {
        return new UserRegistrationDTO();
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String register() {
        return "system-register";
    }

    /*
        @WarnIfExecutionExceeds(
                threshold = 1000
        )

     */
    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public String register(UserRegistrationDTO registerDTO) {

        userService.registerUser(registerDTO);

        return "redirect:/";
    }
}
