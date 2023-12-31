package pt.italo.labseq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.italo.labseq.exception.ValueUnderZeroException;
import pt.italo.labseq.repository.CacheRedis;
import pt.italo.labseq.service.LabSeqService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.valueOf;

@Component
@Slf4j
public class LabSeqServiceImpl implements LabSeqService {


    @Autowired
    private final CacheRedis cacheRedis;

    private final Map<String, Long> cache = new HashMap<>();

    public LabSeqServiceImpl(CacheRedis cacheRedis) {
        this.cacheRedis = cacheRedis;
    }

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

        return checkCache(numberToCalc.toString());
    }

    private void preCalc(Long numberToCalc) {
        if (Objects.nonNull(checkCache(numberToCalc.toString()))) {
            return;
        }
        cacheRedis.preCharge();
        for (int i = 0; i <= numberToCalc; i++) {
            Long cacheChecked = cacheRedis.getCache("" + i);
            if (Objects.isNull(cacheChecked)) {
                String minusFour = "" + (i - 4);
                String minusThree = "" + (i - 3);
                long preCache = checkCache(minusFour) + checkCache(minusThree);
                cache.put("" + i, preCache);
            }

            if (cache.size() == 500) {
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
