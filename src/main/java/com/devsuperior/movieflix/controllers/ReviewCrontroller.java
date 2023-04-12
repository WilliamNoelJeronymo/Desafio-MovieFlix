package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewCrontroller {

    @Autowired
    private ReviewService service;

    @PreAuthorize(value = "hasAnyRole('MEMBER')")
    @PostMapping
    public ResponseEntity<ReviewDTO> insert(@Valid @RequestBody ReviewDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto).toUri();
        dto = service.inset(dto);
        return ResponseEntity.created(uri).body(dto);
    }
}
