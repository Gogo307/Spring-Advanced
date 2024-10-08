package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.dto.AddArticleDTO;
import bg.softuni.fireplace.model.dto.ArticleDetailsDTO;
import bg.softuni.fireplace.model.entity.ArticleEntity;
import bg.softuni.fireplace.model.entity.UserEntity;
import bg.softuni.fireplace.repository.ArticleRepository;
import bg.softuni.fireplace.repository.UserRepository;
import bg.softuni.fireplace.service.ArticleService;
import bg.softuni.fireplace.service.exception.ObjectNotFoundException;
import bg.softuni.fireplace.web.ArticleController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

import java.util.stream.Collectors;

@Service
@RequestMapping("/articles")
public class ArticleServiceImpl implements ArticleService {

    private final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private final RestClient articleRestClient;
    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleServiceImpl(@Qualifier("articleRestClient")RestClient articleRestClient, ModelMapper modelMapper,
                              ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRestClient = articleRestClient;
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddArticleDTO addArticleDTO(AddArticleDTO addArticleDTO) {
        ArticleEntity article = this.modelMapper.map(addArticleDTO, ArticleEntity.class);
        try{
            this.articleRepository.saveAndFlush(article);
            return this.modelMapper.map(article, AddArticleDTO.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createArticle(AddArticleDTO addArticleDTO) {
        LOGGER.info("Creating new offer...");

        articleRestClient
                .post()
                .uri("/offers")
                .body(addArticleDTO)
                .retrieve();
    }

    @Override
    public ArticleDetailsDTO editArticle(Long id, ArticleDetailsDTO articleDetailsDTO) {
        ArticleEntity article = this.articleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Article with the given id was not found!", 404));

        article.setViews(articleDetailsDTO.getViews());
        article.setContent(articleDetailsDTO.getContent());
        article.setTitle(articleDetailsDTO.getTitle());
        article.setArticleCategory(articleDetailsDTO.getArticleCategory());

        return this.modelMapper.map(this.articleRepository.saveAndFlush(article), ArticleDetailsDTO.class);
    }

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

        return this.articleRepository.findArticleEntitiesByAuthor(author)
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
                .orElseThrow(() -> new ObjectNotFoundException("Article with the given id was not found!",404));
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public ArticleDetailsDTO getArticleDetails(Long id) {

        return articleRestClient
                .get()
                .uri("/articles/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ArticleDetailsDTO.class);
    }

    @Override
    public List<ArticleDetailsDTO> getAllArticleDetails() {
        LOGGER.info("Get all articles...");

        return articleRestClient
                .get()
                .uri("/articles")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }
}
