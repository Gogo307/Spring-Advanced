package bg.softuni.fireplace.model.dto;

import bg.softuni.fireplace.model.entity.ArticleEntity;
import bg.softuni.fireplace.model.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class AddCommentDTO {

    @Column(nullable = false)
    private LocalDateTime created;

    @NotNull(message = "Content cannot be null.")
    @Length(min = 5, max = 100, message = "Content should be between 5 and 100 symbols.")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @NotNull
    private UserEntity author;
    @NotNull
    private ArticleEntity article;

    public AddCommentDTO() {
    }

    public static AddCommentDTO empty() {
        return new AddCommentDTO();
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
