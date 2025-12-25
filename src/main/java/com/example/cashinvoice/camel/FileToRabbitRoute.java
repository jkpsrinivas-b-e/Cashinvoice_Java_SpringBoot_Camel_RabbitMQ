package com.example.cashinvoice.camel;

import com.example.cashinvoice.model.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class FileToRabbitRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("file:input/orders?move=../processed")
                .routeId("file-to-rabbit")
                .unmarshal().json(JsonLibrary.Jackson, Order.class)
                .log("Sending order ${body.orderId} to RabbitMQ")
                .to("spring-rabbitmq:ORDER.CREATED.QUEUE");
    }
}

