package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findPaged(
            @RequestParam(name = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieDTO> dtos = service.findByGenreAndSortTitle(pageable, genreId);
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieGenreDTO> findById(@PathVariable Long id) {
        MovieGenreDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{movieId}/reviews")
    public ResponseEntity<List<ReviewDTO>> findByMovie(@PathVariable Long movieId) {
        List<ReviewDTO> dtos = reviewService.findByMovie(movieId);
        return ResponseEntity.ok().body(dtos);
    }

}
