package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.dto.ArticleDetailsDTO;
import bg.softuni.fireplace.model.entity.UserEntity;
import bg.softuni.fireplace.repository.ArticleRepository;
import bg.softuni.fireplace.repository.UserRepository;
import bg.softuni.fireplace.service.ArticleService;
import bg.softuni.fireplace.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticleServiceImpl implements ArticleService {

    private final RestController articleRestController;
    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleServiceImpl(@Qualifier("articleRestClient")RestController articleRestController, ModelMapper modelMapper,
                              ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRestController = articleRestController;
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ArticleDetailsDTO addArticle(ArticleDetailsDTO articleDetailsDTO) {
        Ar

        return null;
    }
/*
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

 */

    @Override
    public List<ArticleDetailsDTO> findAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(article -> this.modelMapper.map(article,ArticleDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDetailsDTO> findAllArticlesByUserId(Long userId) {
        Optional<UserEntity> author = this.userRepository.findById(userId);

        return this.articleRepository.findArticlesByAuthor(author)
                .stream()
                .map(a -> this.modelMapper.map(a, ArticleDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDetailsDTO findArticleById(Long id) {
        return this.articleRepository.findById(id)
                .map(p -> {
                    ArticleDetailsDTO articleDetailsDTO = this.modelMapper.map(p, ArticleDetailsDTO.class);
                    this.articleRepository.findById(articleDetailsDTO.getId());

                    return articleDetailsDTO;
                })
                .orElseThrow(() -> new ObjectNotFoundException("Article with the given id was not found!",id));
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
    @Override
    public ArticleServiceModel findArticleById(String id) {
        return this.articleRepository.findById(id)
                .map(p -> {
                    ArticleServiceModel articleServiceModel = this.modelMapper.map(p, ArticleServiceModel.class);
                    this.articleRepository.findById(articleServiceModel.getId());

                    return articleServiceModel;
                })
                .orElseThrow(() -> new ArticleNotFoundException("Article with the given id was not found!"));
    }

}
