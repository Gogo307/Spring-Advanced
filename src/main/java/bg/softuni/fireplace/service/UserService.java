package bg.softuni.fireplace.service;

import bg.softuni.fireplace.model.dto.UserRegistrationDTO;
import bg.softuni.fireplace.model.userDetails.FireplaceUserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void registerUser(UserRegistrationDTO userRegistration);

    Optional<FireplaceUserDetails> getCurrentUser();
}
