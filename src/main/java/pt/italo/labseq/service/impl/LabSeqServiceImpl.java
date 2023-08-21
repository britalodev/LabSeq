package pt.italo.labseq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.italo.labseq.exception.ValueUnderZeroException;
import pt.italo.labseq.repository.configuration.CacheRedis;
import pt.italo.labseq.service.LabSeqService;
import springfox.documentation.annotations.Cacheable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.valueOf;

@Component
public class LabSeqServiceImpl implements LabSeqService {


    @Autowired
    private CacheRedis cacheRedis;

    private final Map<String, Long> cache = new HashMap<>();

    @Cacheable(value = "func")
    public Long calcFunction(Long numberToCalc) {

        if (Objects.isNull(numberToCalc) || numberToCalc < 0) {
            throw new ValueUnderZeroException(numberToCalc.toString());
        }

        Long cacheChecked = checkCache(valueOf(numberToCalc));

        if (Objects.nonNull(cacheChecked)) {
            return cacheChecked;
        }

        preCalc(numberToCalc);

        cacheRedis.saveCache(cache);
        cache.clear();

        return checkCache(numberToCalc.toString());
    }

    private void preCalc(Long numberToCalc) {
        if (Objects.nonNull(checkCache(numberToCalc.toString()))) {
            return;
        }

        for (int i = 0; i <= numberToCalc; i++) {
            Long cacheChecked = cacheRedis.getCache("" + i);
            if (Objects.isNull(cacheChecked)) {
                String minusFour = "" + (i - 4);
                String minusThree = "" + (i - 3);
                long preCache = checkCache(minusFour) + checkCache(minusThree);
                cache.put("" + i, preCache);
            }

            if (cache.size() == 10) {
                cacheRedis.saveCache(cache);
            }
        }
    }

    private Long checkCache(String numberToCalc) {

        if (cache.containsKey(numberToCalc)) {
            return cache.get(numberToCalc);
        } else {
            return cacheRedis.getCache(numberToCalc);
        }
    }
}
