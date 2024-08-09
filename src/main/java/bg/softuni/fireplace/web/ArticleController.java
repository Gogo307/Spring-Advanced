package bg.softuni.fireplace.web;


import bg.softuni.fireplace.model.dto.AddArticleDTO;
import bg.softuni.fireplace.model.dto.ArticleDetailsDTO;
import bg.softuni.fireplace.model.enums.ArticleCategoryEnum;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ModelMapper modelMapper;
    private final ArticleService articleService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public ArticleController(ModelMapper modelMapper, ArticleService articleService, UserService userService, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.articleService = articleService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @ModelAttribute("allArticleCategories")
    public ArticleCategoryEnum[] allArticleCategories() {
        return ArticleCategoryEnum.values();
    }

    @GetMapping("/add")
    public String newArticle(Model model) {

        //TODO fix .empty
        if (!model.containsAttribute("addArticleDTO")) {
            model.addAttribute("addArticleDTO", AddArticleDTO.empty());
        }

        return "add-article";
    }

    @PostMapping("add")
    public String addArticle(@Valid AddArticleDTO addArticleDTO, BindingResult bindingResult, RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("addArticleDTO", addArticleDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addArticleDTO", bindingResult);
            return "redirect:/articles/add";
        }


        articleService.addArticleDTO(addArticleDTO);

        return "redirect:/articles/all";
    }
/*



    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editArticle(@PathVariable("id") Long id, @Valid AddArticleDTO addArticleDTO, BindingResult bindingResult, RedirectAttributes rAtt){
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("editArticleDTO", addArticleDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.editArticleDTO", bindingResult);
            return "redirect:/articles/all";
        }
        ArticleDetailsDTO readyModel = this.modelMapper.map(addArticleDTO, ArticleDetailsDTO.class);

        this.articleService.editArticle(id,readyModel);

        return "index";
    }

 */

    @GetMapping("/details/{id}")
    public String articleDetails(@PathVariable("id") Long id, Model model) {

        model.addAttribute("articleDetails", articleService.getArticleDetails(id));

        return "article-details";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteArticle (@PathVariable("id") Long id) {

        articleService.deleteArticle(id);

        return "redirect:/articles/all";
    }
}