package vttp5_ssf_b2_practiceAssessment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp5_ssf_b2_practiceAssessment.model.Article;
import vttp5_ssf_b2_practiceAssessment.service.NewsService;

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    // task 2
    // handler for GET / request 
    // shows all articles
    @GetMapping
    public String showAllArticlesPage(Model model) { 

        List<Article> articles = new ArrayList<>(); 
        articles = newsService.getArticles();

        model.addAttribute("articles", articles);

        // return "all-articles.html"
        return "all-articles";

    }

    // task 3
    // handler for POST /articles request 
    // send "saved" artcles to redis 
    @PostMapping("/articles")
    public String saveArticlesPage(
        @RequestParam(name = "articleIds", required = false) 
        List<String> articleIds) {

        // if no articles are selected 
        if (articleIds == null || articleIds.isEmpty()) {
            System.out.println("No articles selected");
            return "articles";

        }

        // System.out.println(articleIds);

        // get list of articles from API using article ID
        List<Article> articles = new ArrayList<>();

        for (String articleID : articleIds) {

            Article article = newsService.getArticleFromAPI(articleID);

            articles.add(article);

        }

        newsService.saveArticles(articles);

        // return original list of articles again
        // TODO html view 
        return "articles";

    }
    
}
