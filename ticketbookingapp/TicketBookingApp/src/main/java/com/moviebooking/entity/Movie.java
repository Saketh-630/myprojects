package com.moviebooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    
private String posterUrl;

    @Column(length = 1000)
    private String description;

    public String getPosterUrl() {
		return posterUrl;
	}

	public Movie(Long id, String title, String posterUrl, String description, String language, String genre,
			Integer durationMinutes, String rating, String status) {
		super();
		this.id = id;
		this.title = title;
		this.posterUrl = posterUrl;
		this.description = description;
		this.language = language;
		this.genre = genre;
		this.durationMinutes = durationMinutes;
		this.rating = rating;
		this.status = status;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	private String language;
    private String genre;
    private Integer durationMinutes;
    private String rating;       // e.g. U, UA, A

    private String status;       // NOW_SHOWING, COMING_SOON

    public Movie() {}

 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

  
}
