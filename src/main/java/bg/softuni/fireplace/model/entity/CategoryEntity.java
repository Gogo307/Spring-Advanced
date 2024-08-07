package bg.softuni.fireplace.model.entity;

import bg.softuni.fireplace.model.enums.ArticleCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ArticleCategoryEnum name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryEntity() {
    }

    public ArticleCategoryEnum getName() {
        return name;
    }

    public void setName(ArticleCategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
