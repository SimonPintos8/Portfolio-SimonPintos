package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.repository.MovieRepository;
import com.dh.catalogservice.service.MovieService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    // obtiene las películas por genero y, si no hay error,
    // las almacena en el repo de películas de catalog-service
    @Override
    public List<Movie> getMoviesByGenre(String genre, Boolean throwError) throws RuntimeException {
        if (throwError) {
            throw new RuntimeException();
        }
        return movieRepository.findByGenre(genre);
    }

}
