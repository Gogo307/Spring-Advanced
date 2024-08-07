package bg.softuni.fireplace.web;


import bg.softuni.fireplace.model.dto.AddArticleDTO;
import bg.softuni.fireplace.model.enums.ArticleCategoryEnum;
import bg.softuni.fireplace.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ModelAttribute("allEngineTypes")
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


        articleService.createArticle(addArticleDTO);

        return "redirect:/articles/all";
    }

    @GetMapping("/{id}")
    public String articleDetails(@PathVariable("id") Long id, Model model) {

        model.addAttribute("articleDetails", articleService.getArticleDetails(id));

        return "details";
    }

    @DeleteMapping("/{id}")
    public String deleteArticle (@PathVariable("id") Long id) {

        articleService.deleteArticle(id);

        return "redirect:/articles/all";
    }
}