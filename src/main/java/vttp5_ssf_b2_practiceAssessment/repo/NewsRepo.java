package vttp5_ssf_b2_practiceAssessment.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepo {

    @Autowired
    @Qualifier("redis-string")
    RedisTemplate<String, String> redisTemplate; 

    // save one article to redis 
    public void saveToRedis(String hashKey, String articleStringData) {

        // save new article in "articles" hash
        redisTemplate.opsForHash().put("articles", hashKey, articleStringData);

    }
    
}
