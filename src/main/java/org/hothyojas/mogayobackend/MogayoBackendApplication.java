package org.hothyojas.mogayobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MogayoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MogayoBackendApplication.class, args);
    }

}
