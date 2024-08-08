package bg.softuni.fireplace.repository;

import bg.softuni.fireplace.model.entity.ArticleEntity;
import bg.softuni.fireplace.model.entity.CommentEntity;
import bg.softuni.fireplace.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

   Optional<List<CommentEntity>> findCommentsEntitiesByArticle (Optional<ArticleEntity> articleOwner);

   Optional<List<CommentEntity>> findCommentsEntitiesByAuthor(Optional<UserEntity> author);

}
