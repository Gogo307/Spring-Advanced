package bg.softuni.fireplace.model.entity;

import bg.softuni.fireplace.model.enums.ArticleCategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    @Length(min = 5, max = 100, message = "Title should be between 5 and 100 symbols.")
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Category cannot be null.")
    @Enumerated(EnumType.STRING)
    private ArticleCategoryEnum articleCategory;

    @NotNull(message = "Content cannot be null.")
    @Length(min = 10, message = "Minimal length is 10 symbols.")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "views", columnDefinition = "int default 0")
    private Integer views;
    @ManyToOne
  //  @MapsId("id")
    private UserEntity author;

    @OneToMany //(targetEntity = CommentEntity.class, mappedBy = "route", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany//(mappedBy = "route", fetch = FetchType.EAGER)
    private List<PictureEntity> pictures;


    public ArticleEntity() {
        this.comments = new ArrayList<>();
        this.pictures = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public ArticleCategoryEnum getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(ArticleCategoryEnum articleCategory) {
        this.articleCategory = articleCategory;
    }
}
