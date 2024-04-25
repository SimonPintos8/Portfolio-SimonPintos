package com.dh.catalogservice.queue;

import com.dh.catalogservice.config.MQConfig;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.impl.CatalogServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CatalogListener {
    private final CatalogServiceImpl service;
    public CatalogListener(CatalogServiceImpl service){
        this.service = service;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(@Payload Movie movie){
        if(movie.getUrlStream() != null){
            service.saveMovie(movie);
        }
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(@Payload Serie serie){
        if(serie.getSeasons() != null){
            service.create(serie);
        }
    }
}