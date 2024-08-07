package bg.softuni.fireplace.model.entity;


import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.beans.factory.annotation.Value;

import static org.hibernate.type.SqlTypes.VARCHAR;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String email;

    @UuidGenerator
    @JdbcTypeCode(VARCHAR)
    private UUID uuid;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Positive
    @Min(1)
    @Max(118)
    private Integer age;

    @ManyToOne
    private List<ArticleEntity> articles;

    @ManyToOne
    private List<CommentEntity> comments;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<UserRoleEntity> roles = new ArrayList<>();

    public UserEntity() {
        this.articles = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.roles = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }


    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }
}