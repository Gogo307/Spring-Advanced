package bg.softuni.fireplace.model.userDetails;

import bg.softuni.fireplace.model.entity.ArticleEntity;
import bg.softuni.fireplace.model.entity.CommentEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class FireplaceUserDetails extends User {

    private final UUID uuid;

    private final String username;
    private final Integer age;

    private final List<ArticleEntity> articles;

    private final List<CommentEntity> comments;



    public FireplaceUserDetails(
            UUID uuid,
            String email,
            Collection<? extends GrantedAuthority> authorities,
            String password,
            String username,
            Integer age,
            List<ArticleEntity> articles,
            List<CommentEntity> comments
            ) {
        super(email, password, authorities);
        this.uuid = uuid;
        this.age = age;
        this.username = username;

        this.articles = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

/*
    public FireplaceUserDetails(UUID uuid, String email, String password, String username, Integer age, List<ArticleEntity> articles, List<CommentEntity> comments, Collection<? extends GrantedAuthority> authorities) {
        super(email,password,authorities);
    }

 */

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }
}