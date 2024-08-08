package bg.softuni.fireplace.service;

import bg.softuni.fireplace.model.dto.AddCommentDTO;
import bg.softuni.fireplace.model.dto.CommentDetailsDTO;

import java.util.List;

public interface CommentService {

    AddCommentDTO addCommentDTO(AddCommentDTO addCommentDTO);

    CommentDetailsDTO editComment(Long id, CommentDetailsDTO commentDetailsDTO);

    List<CommentDetailsDTO> findAllCommentsByArticleId(Long articleId);

    List<CommentDetailsDTO> findAllCommentsByUserId(Long userId);

    CommentDetailsDTO findCommentById(Long id);

    void deleteComment(Long id);
}
