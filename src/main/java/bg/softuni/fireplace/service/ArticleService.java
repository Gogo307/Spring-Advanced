package bg.softuni.fireplace.service;

import bg.softuni.fireplace.model.dto.AddArticleDTO;
import bg.softuni.fireplace.model.dto.ArticleDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    AddArticleDTO addArticleDTO(AddArticleDTO addArticleDTO);

    ArticleDetailsDTO editArticle(Long id, ArticleDetailsDTO articleDetailsDTO);

    List<ArticleDetailsDTO> findAllArticles();

    List<ArticleDetailsDTO> findAllArticlesByUserId(Long userId);

    ArticleDetailsDTO findArticleById(Long id);

    void deleteArticle(Long id);

    ArticleDetailsDTO getArticleDetails(Long id);
    List<ArticleDetailsDTO> getAllArticleDetails();
}
