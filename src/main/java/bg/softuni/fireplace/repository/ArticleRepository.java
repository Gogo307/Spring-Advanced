package bg.softuni.fireplace.repository;

import bg.softuni.fireplace.model.entity.ArticleEntity;
import bg.softuni.fireplace.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<List<ArticleEntity>> findArticlesByAuthor(UserEntity author);
}
