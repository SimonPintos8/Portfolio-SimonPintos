package com.dh.catalogservice.client;

import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "serie-service")
public interface ISerieClient {
    @GetMapping("/api/v1/series/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/api/v1/series/save")
    public ResponseEntity<Serie> create(@RequestBody Serie serie);
}
