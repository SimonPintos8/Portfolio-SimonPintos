package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;

public interface CatalogService {

    Genre getCatalogByGenre(String genre);

    Movie saveMovie(Movie movie);

    Serie create(Serie serie);
}
