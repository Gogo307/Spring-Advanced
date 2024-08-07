package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.dto.UserRegistrationDTO;
import bg.softuni.fireplace.model.entity.UserEntity;
import bg.softuni.fireplace.model.userDetails.FireplaceUserDetails;
import bg.softuni.fireplace.repository.UserRepository;
import bg.softuni.fireplace.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistration) {
        userRepository.save(map(userRegistration));
    }

    @Override
    public Optional<FireplaceUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() instanceof FireplaceUserDetails fireplaceUserDetails) {
            return Optional.of(fireplaceUserDetails);
        }
        return Optional.empty();
    }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity mappedEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);

        mappedEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        return mappedEntity;
    }
}
