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
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp5_ssf_b2_practiceAssessment.Exception.ArticleNotFoundException;
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
    // helper method 
    // get articles from API using id 
    public Article getArticleFromAPI(String id) {

        RestTemplate restTemplate = new RestTemplate(); 

        String articleData = restTemplate.getForObject(Constant.cryptoUrl, String.class);

        StringReader sReader = new StringReader(articleData);
        JsonReader jReader = Json.createReader(sReader);
        JsonObject jObject = jReader.readObject();
        
        JsonArray jDataArray = jObject.getJsonArray("Data");

        for (JsonValue jValue : jDataArray) {
            
            JsonObject jEntry = jValue.asJsonObject();

            if (jEntry.getString("id").equals(id)) {

                Article article = new Article(); 

                article.setId(jEntry.getString("id"));
                article.setPublished_on(jEntry.getInt("published_on"));
                article.setTitle(jEntry.getString("title"));
                article.setUrl(jEntry.getString("url"));
                article.setImageurl(jEntry.getString("imageurl"));
                article.setBody(jEntry.getString("body"));
                article.setTags(jEntry.getString("tags"));
                article.setCategories(jEntry.getString("categories"));

                // System.out.println(article);

                return article;

            }

        }

        System.out.println("Article with ID " + id + "not found. :((");
        return null;

    }

    // task 3 
    // saves articles to Redis 
    public void saveArticles(List<Article> articles) {

        // // testing statement
        // System.out.println(articles);

        // for each Article a in articles 
        for (Article a : articles) {

            // Article POJO --> JSON formatted string
            String articleStringData = POJOToJsonString(a); 

            // get ID to save as hashkey 
            String hashKey = a.getId();

            // save to redis 
            newsRepo.saveToRedis(hashKey, articleStringData);

            System.out.printf("Saved Article with ID: %s to Redis \n", hashKey);

        }

    }

    // task 3 
    // helper method 
    // Article POJO --> JSON Formatted String (saving to Redis)
    public String POJOToJsonString(Article article) { 

        JsonObjectBuilder builder = Json.createObjectBuilder()
                                        .add("id", article.getId())
                                        .add("published_on", article.getPublished_on())
                                        .add("title", article.getTitle())
                                        .add("url", article.getUrl())
                                        .add("imageurl", article.getImageurl())
                                        .add("body", article.getBody())
                                        .add("tags", article.getTags())
                                        .add("categories", article.getCategories());

        JsonObject articleObject = builder.build(); 
        return articleObject.toString(); 

    }

    // task 4 
    // retrieve article from redis with ID
    public Article retrieveArticle(String id) {

        String jArticleString = newsRepo.getArticleFromRedis(id);

        // if article doesn't exist in redis 
        if (jArticleString == null || jArticleString.isEmpty()) {
            throw new ArticleNotFoundException(id);
        }

        // JSON String from redis --> Article POJO 
        Article article = JsonStringToPOJO(jArticleString);

        return article;

    }

    // task 4 
    // helper method 
    // JSON formatted String --> Article POJO 
    public Article JsonStringToPOJO(String articleRaw) {

        StringReader sr = new StringReader(articleRaw);
        JsonReader jr = Json.createReader(sr);
        JsonObject jo = jr.readObject();

        // map JSON fields into Article POJO 
        Article article = new Article(
            jo.getString("id"),
            jo.getInt("published_on"),
            jo.getString("title"),
            jo.getString("url"),
            jo.getString("imageurl"),
            jo.getString("body"),
            jo.getString("tags"),
            jo.getString("categories")
        ); 

        return article;

    }


    
}
