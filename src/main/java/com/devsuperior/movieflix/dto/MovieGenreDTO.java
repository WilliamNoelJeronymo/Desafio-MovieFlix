package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;

import java.io.Serializable;

public class MovieGenreDTO implements Serializable {

    private static final long serialVersionUID = 1L;
	private Long id;
    private String title;

    private String subTitle;
    private Integer year;
    private String imgUrl;
    private String synopsis;
    private GenreDTO genre;

    public MovieGenreDTO() {
    }

    public MovieGenreDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis, GenreDTO genre) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.year = year;
        this.imgUrl = imgUrl;
        this.synopsis = synopsis;
        this.genre = genre;
    }

    public MovieGenreDTO(Movie entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.subTitle = entity.getSubTitle();
        this.year = entity.getYear();
        this.imgUrl = entity.getImgUrl();
        this.synopsis = entity.getSynopsis();
        this.genre = new GenreDTO(entity.getGenre().getId(), entity.getGenre().getName());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Integer getYear() {
        return year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public GenreDTO getGenre() {
        return genre;
    }
}
