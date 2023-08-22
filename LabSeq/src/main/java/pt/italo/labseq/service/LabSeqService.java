package pt.italo.labseq.service;

import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

@Service
public interface LabSeqService {
    public Long calcFunction(Long n);
}
