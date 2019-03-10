package com.network.company;

import com.network.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = "com.network.company")
@EntityScan(basePackages = "com.network.domain.company")
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class,args);
    }

    @Bean
    public IdWorker getIdWorker(){

        return new IdWorker();
    }
}
