package bg.softuni.fireplace.web;

import bg.softuni.fireplace.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticlesController {

    private final ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/all")
    public String getAllOffers(Model model) {

        model.addAttribute("allArticles", articleService.getAllArticleDetails());
        return "article/all-articles";
    }

}
