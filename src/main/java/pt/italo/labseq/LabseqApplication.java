package pt.italo.labseq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class LabseqApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabseqApplication.class, args);
	}

}
