package bg.softuni.fireplace.model.userDetails;

import bg.softuni.fireplace.model.entity.ArticleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class FireplaceUserDetails extends UserEn {

    private final UUID uuid;


    public FireplaceUserDetails(
            UUID uuid,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            String username


    ) {
        super(username, password, authorities);
        this.uuid = uuid;

    }

    public FireplaceUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities, String username, Integer age,
                                List<ArticleEntity> articles, List<>
    ) {
        super();
    }

    public UUID getUuid() {
        return uuid;
    }



}