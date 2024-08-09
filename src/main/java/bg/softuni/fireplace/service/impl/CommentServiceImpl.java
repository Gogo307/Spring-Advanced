package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.dto.AddCommentDTO;
import bg.softuni.fireplace.model.dto.CommentDetailsDTO;
import bg.softuni.fireplace.model.entity.ArticleEntity;
import bg.softuni.fireplace.model.entity.CommentEntity;
import bg.softuni.fireplace.model.entity.UserEntity;
import bg.softuni.fireplace.repository.ArticleRepository;
import bg.softuni.fireplace.repository.CommentRepository;
import bg.softuni.fireplace.repository.UserRepository;
import bg.softuni.fireplace.service.CommentService;
import bg.softuni.fireplace.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository, ArticleRepository articleRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddCommentDTO addCommentDTO(AddCommentDTO addCommentDTO) {
        CommentEntity comment = this.modelMapper.map(addCommentDTO, CommentEntity.class);
        try{
            this.commentRepository.saveAndFlush(comment);
            return this.modelMapper.map(comment, AddCommentDTO.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CommentDetailsDTO editComment(Long id, CommentDetailsDTO commentDetailsDTO) {
        CommentEntity comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Comment with the given id was not found!", 404));

        comment.setText(commentDetailsDTO.getText());
        return this.modelMapper.map(this.commentRepository.saveAndFlush(comment), CommentDetailsDTO.class);
    }

    @Override
    public List<CommentDetailsDTO> findAllCommentsByArticleId(Long articleId) {
        Optional<ArticleEntity> article = this.articleRepository.findById(articleId);
        return this.commentRepository.findCommentsEntitiesByArticle(article)
                .stream()
                .map(c -> this.modelMapper.map(c, CommentDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDetailsDTO> findAllCommentsByUserId(Long userId) {
        Optional<UserEntity> user = this.userRepository.findById(userId);
        return this.commentRepository.findCommentsEntitiesByAuthor(user)
                .stream()
                .map(c -> this.modelMapper.map(c, CommentDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDetailsDTO findCommentById(Long id) {

        return this.commentRepository.findById(id)
                .map(c -> {
                    CommentDetailsDTO commentDetailsDTO = this.modelMapper.map(c, CommentDetailsDTO.class);
                    this.commentRepository.findById(commentDetailsDTO.getId());

                    return commentDetailsDTO;
                })
                .orElseThrow(() -> new ObjectNotFoundException("Comment with the given id was not found!",404));
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
