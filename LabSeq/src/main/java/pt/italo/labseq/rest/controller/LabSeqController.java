package pt.italo.labseq.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.italo.labseq.service.LabSeqService;

@RestController
@RequestMapping(path = "labseq", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class LabSeqController {

    @Autowired
    private LabSeqService labSeqService;

    @GetMapping(value = "/{number}", name = "Calc Function")
    public ResponseEntity<Long> calcFunction(@PathVariable Long number) {
        Long labSeqResult = labSeqService.calcFunction(number);
        return new ResponseEntity<Long>(labSeqResult, HttpStatus.OK);
    }

}


