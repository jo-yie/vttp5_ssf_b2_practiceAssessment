package vttp5_ssf_b2_practiceAssessment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String saveArticlesPage(@ModelAttribute("article") Article article, Model model) {

        

        
        List<Article> articles = new ArrayList<>();

        newsService.saveArticles(articles);

        // redirect to show all articles
        return null;

    }
    
}
