package com.dh.movieservice.queue;

import com.dh.movieservice.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieSender {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    // Envía el nombre de la cola definido en el application.yml junto a la movie.
    public void send(Movie movie){
        this.rabbitTemplate.convertAndSend(this.queue.getName(), movie);
    }
}