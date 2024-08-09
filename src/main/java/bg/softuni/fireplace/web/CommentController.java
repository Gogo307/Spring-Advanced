package bg.softuni.fireplace.web;

import bg.softuni.fireplace.model.dto.AddArticleDTO;
import bg.softuni.fireplace.model.dto.AddCommentDTO;
import bg.softuni.fireplace.service.ArticleService;
import bg.softuni.fireplace.service.CommentService;
import bg.softuni.fireplace.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final ModelMapper modelMapper;
    private final CommentService commentService;
    private final UserService userService;
    private final ArticleService articleService;

    @Autowired
    public CommentController(ModelMapper modelMapper, CommentService commentService, UserService userService, ArticleService articleService) {
        this.modelMapper = modelMapper;
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/add")
    public String newComment(Model model) {

        if (!model.containsAttribute("addCommentDTO")) {
            model.addAttribute("addCommentDTO", AddCommentDTO.empty());
        }

        return "comment/add-comment";
    }

    @PostMapping("add")
    public String addComment(@Valid AddCommentDTO addCommentDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("addCommentDTO", addCommentDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addCommentDTO", bindingResult);
            return "redirect:/comments/add";
        }

        commentService.addCommentDTO(addCommentDTO);

        return "redirect:/articles/all";
    }

    /*
    @PutMapping("editComment")
    public String editComment(@Valid  )


     */

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return "redirect:/articles/all";
    }
}
