package vttp5_ssf_b2_practiceAssessment.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// automatically return 404
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends RuntimeException {

    // constructor accepts id to be displayed in message 
    public ArticleNotFoundException(String id) {

        super("Cannot find news article " + id);

    }
    
}