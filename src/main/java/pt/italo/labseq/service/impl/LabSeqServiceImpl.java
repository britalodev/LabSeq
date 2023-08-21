package pt.italo.labseq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pt.italo.labseq.exception.ValueUnderZeroException;
import pt.italo.labseq.service.LabSeqService;

import java.util.Objects;

@Component
public class LabSeqServiceImpl implements LabSeqService {

    @Autowired
    private ApplicationContext applicationContext;

    @Cacheable(value = "func")
    public Long calcFunction(Long numberToCalc) {

        if (Objects.isNull(numberToCalc) || numberToCalc < 0) {
            throw new ValueUnderZeroException(numberToCalc.toString());
        }

        Long result;

        if (numberToCalc == 0 || numberToCalc == 2) {
            result = 0L;
        } else if (numberToCalc == 1 || numberToCalc == 3) {
            result = 1L;
        } else {
            result = calc(numberToCalc);
        }
        return result;
    }

    private Long calc(Long numberTocalc) {
        LabSeqServiceImpl bean = applicationContext.getBean(LabSeqServiceImpl.class);
        return bean.calcFunction(numberTocalc - 4) + bean.calcFunction(numberTocalc - 3);
    }

}
