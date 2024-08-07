package bg.softuni.fireplace.model.entity;

import bg.softuni.fireplace.model.enums.ArticleCategoryEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private ArticleCategoryEnum articleCategoryEnum;

    @ManyToOne
  //  @MapsId("id")
    private UserEntity author;

    @OneToMany //(targetEntity = CommentEntity.class, mappedBy = "route", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany//(mappedBy = "route", fetch = FetchType.EAGER)
    private List<PictureEntity> pictures;

    @ManyToMany
    private List<CategoryEntity> categories;

    public ArticleEntity() {
        this.comments = new ArrayList<>();
        this.pictures = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}
