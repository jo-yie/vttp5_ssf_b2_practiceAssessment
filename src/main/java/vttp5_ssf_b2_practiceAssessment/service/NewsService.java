package vttp5_ssf_b2_practiceAssessment.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp5_ssf_b2_practiceAssessment.constant.Constant;
import vttp5_ssf_b2_practiceAssessment.model.Article;
import vttp5_ssf_b2_practiceAssessment.repo.NewsRepo;

@Service
public class NewsService {

    @Autowired
    NewsRepo newsRepo; 


    // task 1 
    // get all articles from API 
    public List<Article> getArticles() { 

        RestTemplate restTemplate = new RestTemplate(); 

        String articleData = restTemplate.getForObject(Constant.cryptoUrl, String.class);
        
        // // test API retrieval 
        // System.out.println(articleData);

        StringReader sReader = new StringReader(articleData);
        JsonReader jReader = Json.createReader(sReader);
        JsonObject jObject = jReader.readObject();
        
        JsonArray jDataArray = jObject.getJsonArray("Data");

        // // test "Data" array retrieval
        // System.out.println(jDataArray);

        List<Article> articles = new ArrayList<>();

        for (JsonValue jValue : jDataArray) {
            
            JsonObject jEntry = jValue.asJsonObject();

            Article article = new Article(); 

            article.setId(jEntry.getString("id"));
            article.setPublished_on(jEntry.getInt("published_on"));
            article.setTitle(jEntry.getString("title"));
            article.setUrl(jEntry.getString("url"));
            article.setImageurl(jEntry.getString("imageurl"));
            article.setBody(jEntry.getString("body"));
            article.setTags(jEntry.getString("tags"));
            article.setCategories(jEntry.getString("categories"));

            articles.add(article);

        }
        
        return articles;

    }

    // task 3 
    // saves articles to Redis 
    public void saveArticles(List<Article> articles) {

        

    }

    
}
