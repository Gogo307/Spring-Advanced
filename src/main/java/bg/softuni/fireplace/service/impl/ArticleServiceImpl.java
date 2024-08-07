package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.dto.ArticleDetailsDTO;
import bg.softuni.fireplace.repository.ArticleRepository;
import bg.softuni.fireplace.repository.UserRepository;
import bg.softuni.fireplace.service.ArticleService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleServiceImpl(ModelMapper modelMapper, ArticleRepository articleRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ArticleDetailsDTO addArticle(ArticleDetailsDTO articleDetailsDTO) {
        Ar

        return null;
    }

    @Override
    public ArticleServiceModel addArticle(ArticleServiceModel articleServiceModel) {
        Article article = this.modelMapper.map(articleServiceModel, Article.class);
        try{
            this.articleRepository.saveAndFlush(article);
            return this.modelMapper.map(article, ArticleServiceModel.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ArticleDetailsDTO> findAllArticles() {
        return null;
    }

    @Override
    public List<ArticleDetailsDTO> findAllArticlesByUserId(Long userId) {
        return null;
    }

    @Override
    public ArticleDetailsDTO findArticleById(Long id) {
        return null;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
