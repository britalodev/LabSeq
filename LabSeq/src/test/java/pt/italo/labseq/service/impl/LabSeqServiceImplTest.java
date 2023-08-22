package pt.italo.labseq.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pt.italo.labseq.repository.CacheRedis;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
public class LabSeqServiceImplTest {


    private static final Map<String, Long> cache = new HashMap<>();

    @Autowired
    private LabSeqServiceImpl labSeqService;

    @MockBean
    CacheRedis cacheRedis;

    @BeforeAll
    public static void setup(){
        cache.put("0", 0L);
        cache.put("2", 0L);
        cache.put("1", 1L);
        cache.put("3", 1L);
    }

    @Test
    @DisplayName("Teste para verificar os valores retornados pelo cálculo")
    public void calcFunctionTest() {

        Mockito.when(cacheRedis.getCache("0")).thenReturn(checkCache("0"));
        Mockito.when(cacheRedis.getCache("1")).thenReturn(checkCache("1"));
        Mockito.when(cacheRedis.getCache("2")).thenReturn(checkCache("2"));
        Mockito.when(cacheRedis.getCache("3")).thenReturn(checkCache("3"));
        Mockito.when(cacheRedis.getCache("4")).thenReturn(checkCache("4"));

        Long calcFunction = labSeqService.calcFunction(4L);

        addToCache("4", calcFunction);


        Assertions.assertEquals(1L, calcFunction);

    }


    private Long checkCache(String key) {
        return cache.getOrDefault(key, null);
    }

    private Long addToCache(String key, Long value) {
        return cache.put(key, value);
    }
}
