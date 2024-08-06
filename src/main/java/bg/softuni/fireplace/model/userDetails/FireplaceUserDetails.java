package bg.softuni.fireplace.model.userDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class FireplaceUserDetails extends User {

    private final UUID uuid;


    public MobileleUserDetails(
            UUID uuid,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,

    ) {
        super(username, password, authorities);
        this.uuid = uuid;

    }

    public FireplaceUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null) {
            fullName.append(firstName);
        }
        if (lastName != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }
            fullName.append(lastName);
        }

        return fullName.toString();
    }
}