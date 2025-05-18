package com.thefifthcontinent.moviesfx.util;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.model.Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataHandler
{
	private static DataHandler instance;

	private ObservableList<Actor> actors;
	private ObservableList<Director> directors;
	private ObservableList<Movie> movies;
	
	private DataHandler() 
	{
		actors = FXCollections.observableArrayList();
		directors = FXCollections.observableArrayList();
		movies = FXCollections.observableArrayList();
	}
	
	public static DataHandler getInstance()
	{
		if (instance == null) {
			instance = new DataHandler();
		}
		
		return instance;
	}
	
	public ObservableList<Actor> getActors()
	{
		return actors;
	}
	
	public ObservableList<Director> getDirectors()
	{
		return directors;
	}
	
	public ObservableList<Movie> getMovies()
	{
		return movies;
	}
}
