package com.dh.serieservice.service;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;


    public SerieService(SerieRepository repository) {
        this.repository = repository;
    }

    public List<Serie> getSerieByGenre(String genre) {
        return repository.getSerieByGenre(genre);
    }

    public Serie create(Serie serie) {
        return repository.save(serie);
    }
}
