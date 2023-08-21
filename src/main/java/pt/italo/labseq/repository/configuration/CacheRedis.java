package pt.italo.labseq.repository.configuration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CacheRedis {
    private final RedisTemplate<String, Long> redisTemplate;

    public CacheRedis(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.opsForValue().set("0", 0L);
        redisTemplate.opsForValue().set("2", 0L);
        redisTemplate.opsForValue().set("1", 1L);
        redisTemplate.opsForValue().set("3", 1L);
    }

    public void saveCache(Map<Long, Long> cache) {
        cache.forEach((k,v) -> {
            redisTemplate.opsForValue().set(Long.toString(k), v);
        });
    }

    public Long getCache(Long numberToCalc) {
        return redisTemplate.opsForValue().get(Long.toString(numberToCalc));
    }
}
