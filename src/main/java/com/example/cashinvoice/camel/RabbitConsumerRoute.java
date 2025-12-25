package com.example.cashinvoice.camel;

import com.example.cashinvoice.model.Order;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("spring-rabbitmq:ORDER.CREATED.QUEUE")
                .routeId("rabbit-consumer")
                .unmarshal().json(JsonLibrary.Jackson, Order.class)
                .log("Order consumed from RabbitMQ | OrderId=${body.orderId}");
    }
}
