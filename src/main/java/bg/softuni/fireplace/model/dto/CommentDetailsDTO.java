package bg.softuni.fireplace.model.dto;

import bg.softuni.fireplace.model.entity.ArticleEntity;
import bg.softuni.fireplace.model.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class CommentDetailsDTO {

    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @NotNull(message = "Content cannot be null.")
    @Length(min = 5, max = 500, message = "Content should be between 5 and 500 symbols.")

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @NotNull
    private UserEntity author;

    @NotNull
    private ArticleEntity article;

    public CommentDetailsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
