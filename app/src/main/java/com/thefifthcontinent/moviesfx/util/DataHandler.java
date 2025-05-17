package com.thefifthcontinent.moviesfx.util;

import java.util.HashMap;
import java.util.Map;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.model.Movie;

public class DataHandler
{
	private static DataHandler instance;

	private Map<String, Actor> actors;
	private Map<String, Director> directors;
	private Map<String, Movie> movies;
	
	private DataHandler() 
	{
		actors = new HashMap<>();
		directors = new HashMap<>();
		movies = new HashMap<>();
	}
	
	public static DataHandler getInstance()
	{
		if (instance == null) {
			instance = new DataHandler();
		}
		
		return instance;
	}
	
	public Map<String, Actor> getActors()
	{
		return actors;
	}
	
	public Map<String, Director> getDirectors()
	{
		return directors;
	}
	
	public Map<String, Movie> getMovies()
	{
		return movies;
	}
}
