package com.dh.serieservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQSenderConfig {
    //Este value accede al valor que se encuentra en la ruta "queue.rabbitmessage.serie", en este caso es SerieWorking
    @Value("${queue.rabbitmessage.serie}")
    private String queue;

    @Bean
    public Queue queue(){
        return new Queue(this.queue, false);
    }
}