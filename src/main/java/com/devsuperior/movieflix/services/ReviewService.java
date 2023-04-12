package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public List<ReviewDTO>  findByMovie(Long movieId) {
        try {
            Movie movie = movieRepository.getOne(movieId);
            List<Review> list = repository.findByMovie(movie);
            return list.stream().map(x ->  new ReviewDTO(x)).collect(Collectors.toList());
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + movieId);
        }

    }

    @Transactional
    public ReviewDTO inset(ReviewDTO dto) {

        User entity =  authService.authenticated();
        Review review = new Review();
        try {
            review.setMovie(movieRepository.getOne(dto.getMovieId()));
            review.setText(dto.getText());
            review.setUser(entity);
            repository.save(review);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Movie not found " + dto.getId());
        }
        return new ReviewDTO(review);
    }


}
