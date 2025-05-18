package com.thefifthcontinent.moviesfx.controllers;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.model.Movie;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ActorViewController
{
	@FXML
	private Label nameLabel;
	
	@FXML
	private VBox moviesVBox;
	
	private Actor actor;
	
	public ActorViewController(Actor actor)
	{
		this.actor = actor;
	}
	
	public void initialize()
	{
		nameLabel.setText(actor.getName());
		
		for (Movie movie: DataHandler.getInstance().getMovies()) {
			if (movie.getActors().contains(actor)) {
				Label label = new Label(movie.getTitle());
				moviesVBox.getChildren().add(label);
			}
		}
	}
	
	@FXML
	public void handleCloseButton()
	{
		ActorController.getStage().close();
	}
}
