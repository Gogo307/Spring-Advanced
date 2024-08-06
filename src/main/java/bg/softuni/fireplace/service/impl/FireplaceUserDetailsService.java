package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.entity.UserEntity;
import bg.softuni.fireplace.model.entity.UserRoleEntity;
import bg.softuni.fireplace.model.enums.UserRoleEnum;
import bg.softuni.fireplace.model.userDetails.FireplaceUserDetails;
import bg.softuni.fireplace.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class FireplaceUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public FireplaceUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository
                .findByUsername(username)
                .map(FireplaceUserDetailsService::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private static UserDetails map(UserEntity userEntity) {

        return new FireplaceUserDetails(
                userEntity.getUuid(),
                userEntity.get
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRoles().stream().map(UserRoleEntity::getRole).map(FireplaceUserDetailsService::map).toList()

        );
    }

    private static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority("ROLE_" + role);
    }
}
