package bg.softuni.fireplace.model.dto;

import bg.softuni.fireplace.model.entity.CommentEntity;
import bg.softuni.fireplace.model.entity.PictureEntity;
import bg.softuni.fireplace.model.entity.UserEntity;
import bg.softuni.fireplace.model.enums.ArticleCategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddArticleDTO {

    @NotNull(message = "Title cannot be null.")
    @Length(min = 5, max = 100, message = "Title should be between 5 and 100 symbols.")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Content cannot be null.")
    @Length(min = 10, message = "Minimal length is 10 symbols.")
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A category has to be chosen.")
    private ArticleCategoryEnum articleCategoryEnum;

    private UserEntity author;
    //TODO check if the comments need to be involved
    private List<CommentEntity> comments;

    private List<PictureEntity> pictures;

    public AddArticleDTO() {
        this.pictures = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ArticleCategoryEnum getArticleCategoryEnum() {
        return articleCategoryEnum;
    }

    public void setArticleCategoryEnum(ArticleCategoryEnum articleCategoryEnum) {
        this.articleCategoryEnum = articleCategoryEnum;
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
    /*
    public static AddArticleDTO empty() {
        return new AddArticleDTO(null);
    }

     */
}
/*
    @NotNull(message = "{add.offer.description.length}")
    @Size(message = "{add.offer.description.length}",
            min = 5,
            max = 500) String description,//not necessarily from message source
    @NotNull @PositiveOrZero Integer mileage,
    @NotNull @PositiveOrZero Integer price,
    @NotNull EngineTypeEnum engineType

 */