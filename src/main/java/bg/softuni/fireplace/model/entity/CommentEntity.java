package bg.softuni.fireplace.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private ArticleEntity article;

    public CommentEntity() {
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }
}
