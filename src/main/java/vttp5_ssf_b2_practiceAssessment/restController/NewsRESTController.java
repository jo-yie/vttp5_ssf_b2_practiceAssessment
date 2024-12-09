package vttp5_ssf_b2_practiceAssessment.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import vttp5_ssf_b2_practiceAssessment.Exception.ArticleNotFoundException;
import vttp5_ssf_b2_practiceAssessment.model.Article;
import vttp5_ssf_b2_practiceAssessment.service.NewsService;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
public class NewsRESTController {

    @Autowired
    NewsService newsService; 

    // task 4 
    // retrieve an article with id 
    // GET /news/<id> Accept: application/json
    @GetMapping("/news/{id}")
    public ResponseEntity<Object> getArticleWithID(@PathVariable String id) {

        try {

            Article article = newsService.retrieveArticle(id);
            return ResponseEntity.status(200).body(article);

        } catch (ArticleNotFoundException e) {

            throw new ArticleNotFoundException(id);

        }
        
    }
    
}
