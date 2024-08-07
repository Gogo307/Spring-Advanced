package bg.softuni.fireplace.service;

import bg.softuni.fireplace.model.dto.ArticleDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    ArticleDetailsDTO addArticle(ArticleDetailsDTO articleDetailsDTO);

    List<ArticleDetailsDTO> findAllArticles();

    List<ArticleDetailsDTO> findAllArticlesByUserId(Long userId);

    ArticleDetailsDTO findArticleById(Long id);

    void deleteArticle(Long id);
}
