// package vttp5_ssf_b2_practiceAssessment.restController;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import vttp5_ssf_b2_practiceAssessment.model.Article;
// import vttp5_ssf_b2_practiceAssessment.service.NewsService;

// // FOR TESTING TASK 1 SERVICE ONLY

// @RestController
// public class TestRestController {

//     @Autowired
//     NewsService newsService; 

//     @GetMapping
//     public ResponseEntity<List<Article>> getAllArticles() {

//         List<Article> articles = new ArrayList<>(); 
//         articles = newsService.getArticles(); 

//         return ResponseEntity.ok().body(articles);

//     }
    
// }
