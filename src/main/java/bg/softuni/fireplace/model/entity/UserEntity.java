package bg.softuni.fireplace.model.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import static org.hibernate.type.SqlTypes.VARCHAR;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @UuidGenerator
    @JdbcTypeCode(VARCHAR)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private Integer age;

    private List<Article> articles;

    private List<Comment> comments;
    --------------------------------------

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles = new ArrayList<>();



    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", full name='" + fullName + '\'' +
                ", age='" + age + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }




}