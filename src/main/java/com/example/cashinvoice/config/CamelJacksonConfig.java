package com.example.cashinvoice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelJacksonConfig {

    @Bean
    public JacksonDataFormat orderJacksonDataFormat() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return new JacksonDataFormat(mapper, com.example.cashinvoice.model.Order.class);
    }
}
