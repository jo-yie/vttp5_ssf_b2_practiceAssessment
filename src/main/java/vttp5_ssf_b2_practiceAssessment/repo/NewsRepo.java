package vttp5_ssf_b2_practiceAssessment.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp5_ssf_b2_practiceAssessment.constant.Constant;

@Repository
public class NewsRepo {

    @Autowired
    @Qualifier("redis-string")
    RedisTemplate<String, String> redisTemplate; 

    // save one article to redis 
    public void saveToRedis(String hashKey, String articleStringData) {

        // save new article in "articles" hash
        redisTemplate.opsForHash().put(Constant.redisKey, hashKey, articleStringData);

    }

    // retrieve an article with id from redis
    public String getArticleFromRedis(String id) {

        return (String) redisTemplate.opsForHash().get(Constant.redisKey, id);

    }
    
}
