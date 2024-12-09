package vttp5_ssf_b2_practiceAssessment.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.json.Json;
import jakarta.json.JsonObject;


// task 4 
// helper method 
// build JSON 404 response 
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handleArticleNotFoundException(ArticleNotFoundException e) {

        // build json response
        JsonObject errorObj = Json.createObjectBuilder()
                                .add("error", e.getMessage())
                                .build();

        // return json response with 404 status
        return ResponseEntity
                .status(404)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(errorObj.toString());
        
    }

}