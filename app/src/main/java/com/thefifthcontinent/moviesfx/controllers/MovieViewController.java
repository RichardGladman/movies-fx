package com.thefifthcontinent.moviesfx.controllers;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.model.Movie;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MovieViewController
{
	@FXML private Label titleLabel;
	@FXML private Label categoryLabel;
	@FXML private Label formatLabel;
	@FXML private Label certificateLabel;
	@FXML private Label runningTimeLabel;
	@FXML private VBox listsVBox;
	
	private Movie movie;
	
	public MovieViewController(Movie movie)
	{
		this.movie = movie;
	}
	
	public void initialize()
	{
		titleLabel.setText(movie.getTitle());
		categoryLabel.setText(movie.getCategory().toString());
		formatLabel.setText(movie.getFormat().toString());
		certificateLabel.setText(movie.getCertificate());
		runningTimeLabel.setText("" + movie.getRunningTime());
		
		listsVBox.getChildren().add(new Label("Starring"));
		
		for (Actor actor: movie.getActors()) {
			Label label = new Label(actor.getName());
			listsVBox.getChildren().add(label);
		}
		
		listsVBox.getChildren().add(new Label("Directed By"));
		
		for (Director director: movie.getDirectors()) {
			Label label = new Label(director.getName());
			listsVBox.getChildren().add(label);
		}
	}
}
