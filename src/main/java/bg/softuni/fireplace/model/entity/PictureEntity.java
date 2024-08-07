package bg.softuni.fireplace.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity{
    private String title;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private ArticleEntity article;

    public PictureEntity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserEntity getUser() {
        return author;
    }

    public void setUser(UserEntity author) {
        this.author = author;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }
}
