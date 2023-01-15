package com.example.main;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "text")
    public String text;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    public Movie movie;

    public Comment() {}

    public Comment(String text, Movie movie) {
        this.text = text;
        this.movie = movie;
    }

    public Comment(Comment comment) {
        this.text = comment.getText();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                '}';
    }
}
