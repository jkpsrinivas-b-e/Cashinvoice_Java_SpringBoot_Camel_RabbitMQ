package com.example.cashinvoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CashinvoiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CashinvoiceApplication.class, args);
    }
}
