package com.dh.catalogservice.service.impl;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.SerieRepository;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.service.MovieService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CatalogServiceImpl implements CatalogService {
    ArrayList<Movie> moviesEmpty = new ArrayList<>();
    ArrayList<Serie> seriesEmpty = new ArrayList<>();
    private IMovieClient iMovieClient;
    private ISerieClient iSerieClient;
    private MovieService movieService;
    private SerieRepository serieRepository;

    public CatalogServiceImpl(IMovieClient iMovieClient, ISerieClient iSerieClient, MovieService movieService, SerieRepository serieRepository) {
        this.iMovieClient = iMovieClient;
        this.iSerieClient = iSerieClient;
        this.movieService = movieService;
        this.serieRepository = serieRepository;
    }

    @CircuitBreaker(name="catalog", fallbackMethod = "getCatalogFallbackMethod")
    @Retry(name="catalog")
    @Override
    public Genre getCatalogByGenre(String genre){
        log.info("Obteniendo peliculas y series por género...");
        ResponseEntity<List<Movie>> response = iMovieClient.getMovieByGenre(genre);
        ResponseEntity<List<Serie>> serieList = iSerieClient.getSerieByGenre(genre);
        // Si ambas listas fueron encontradas, el programa se ejecuta. Caso contrario, está definido el fallbackMethod.
        if (response.getStatusCode().is2xxSuccessful() && serieList.getStatusCode().is2xxSuccessful()) {
            return new Genre(response.getBody(), serieList.getBody());
        } else {
            return new Genre(movieService.getMoviesByGenre(genre, true), serieRepository.findByGenre(genre));
        }
    }


    public Genre getCatalogFallbackMethod(String genre, Throwable throwable) {
        log.info("Ingresando al fallbackMethod...");
        Genre resultsByGenre = new Genre(moviesEmpty, seriesEmpty);
        return resultsByGenre;
    }

    //Crear Movie
    @Override
    public Movie saveMovie(Movie movie) {
        Movie savedMovie;
        ResponseEntity<Movie> movieResponseEntity = iMovieClient.saveMovie(movie);
        savedMovie = movieResponseEntity.getBody();
        return savedMovie;
    }

    // Crear serie
    @Override
    public Serie create(Serie serie) {
        Serie newSerie;
        ResponseEntity<Serie> serieSaved = iSerieClient.create(serie);
        newSerie = serieSaved.getBody();
        return newSerie;
    }

}
