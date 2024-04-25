package com.dh.catalogservice.client;


import com.dh.catalogservice.model.Movie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "movie-service")
public interface IMovieClient {
    @GetMapping("/api/v1/movies/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/movies/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie);

}
