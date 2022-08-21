package com.megasoby.sellerbatchproc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SellerBatchProcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellerBatchProcApplication.class, args);
    }

}
