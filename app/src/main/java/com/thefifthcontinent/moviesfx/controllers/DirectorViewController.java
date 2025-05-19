package com.thefifthcontinent.moviesfx.controllers;

import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.model.Movie;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DirectorViewController
{
	@FXML
	private Label nameLabel;
	
	@FXML
	private VBox moviesVBox;
	
	private Director director;
	
	public DirectorViewController(Director director)
	{
		this.director = director;
	}
	
	public void initialize()
	{
		nameLabel.setText(director.getName());
		
		for (Movie movie: DataHandler.getInstance().getMovies()) {
			if (movie.getDirectors().contains(director)) {
				Label label = new Label(movie.getTitle());
				moviesVBox.getChildren().add(label);
			}
		}
	}
	
	@FXML
	public void handleCloseButton()
	{
		DirectorController.getStage().close();
	}
}
