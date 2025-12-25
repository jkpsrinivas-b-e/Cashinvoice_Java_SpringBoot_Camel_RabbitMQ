package com.example.cashinvoice.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class OrderRoute extends RouteBuilder {

    private final JacksonDataFormat orderJacksonDataFormat;

    public OrderRoute(JacksonDataFormat orderJacksonDataFormat) {
        this.orderJacksonDataFormat = orderJacksonDataFormat;
    }

    @Override
    public void configure() throws Exception {
        from("file://input/orders?move=../processed")
                .unmarshal(orderJacksonDataFormat)
                .to("rabbitmq://localhost:5672/exchangeName?queue=orderQueue&autoDelete=false");
    }
}
