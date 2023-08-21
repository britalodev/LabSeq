package pt.italo.labseq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import pt.italo.labseq.exception.ValueUnderZeroException;
import pt.italo.labseq.repository.configuration.CacheRedis;
import pt.italo.labseq.service.LabSeqService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LabSeqServiceImpl implements LabSeqService {


    @Autowired
    private CacheRedis cacheRedis;

    private final Map<Long, Long> cache = new HashMap<>();


    public Long calcFunction(Long numberToCalc) {

        if (Objects.isNull(numberToCalc) || numberToCalc < 0) {
            throw new ValueUnderZeroException(numberToCalc.toString());
        }

        Long cacheChecked = checkCache(numberToCalc);

        if (Objects.nonNull(cacheChecked)) {
            return cacheChecked;
        }

        Long result;

        result = calcFunction(numberToCalc - 4) + calcFunction(numberToCalc - 3);

        cache.put(numberToCalc, result);

        if (cache.size() == 1500) {

            cacheRedis.saveCache(cache);

            cache.clear();
        }

        return result;
    }

    private Long checkCache(Long numberToCalc) {

        if (cache.containsKey(numberToCalc)) {
            return cache.get(numberToCalc);
        } else {
            if (cacheRedis.getCache(numberToCalc) != null) {
                return cacheRedis.getCache(numberToCalc);
            }
        }
        return null;
    }
}
