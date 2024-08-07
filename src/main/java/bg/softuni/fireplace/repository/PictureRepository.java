package bg.softuni.fireplace.repository;

import bg.softuni.fireplace.model.entity.PictureEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
}
