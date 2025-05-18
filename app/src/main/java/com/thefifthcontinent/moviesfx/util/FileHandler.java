package com.thefifthcontinent.moviesfx.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.model.Category;
import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.model.Format;
import com.thefifthcontinent.moviesfx.model.Movie;

import javafx.collections.ObservableList;

public class FileHandler
{
	private final String directory;
	private final String filename;
	
	public FileHandler(String directory, String filename)
	{
		this.directory = directory;
		this.filename = filename;
	}
	
	public void saveData(ObservableList<Actor> actors, ObservableList<Director> directors, ObservableList<Movie> movies)
	{
		try {
			Files.createDirectories(Paths.get(directory));
	   	} catch(IOException e) {
	   		throw new RuntimeException(e.getMessage());
	   	}
			
		
	   	try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename, false))) {
	   		for (Actor actor: actors) {
				String data = "ACTOR::" + actor.getName() + "\n";
				writer.write(data);
			}
	
			for (Director director: directors) {
				String data = "DIRECTOR::" + director.getName() + "\n";
				writer.write(data);
			}
	
			for (Movie movie: movies) {
				String data = "MOVIE::" + movie.getTitle() + "," +
										movie.getCategory() + "," +
										movie.getFormat() + "," +
										movie.getCertificate() + "," +
										movie.getRunningTime() + "\n";
				writer.write(data);
				
	    		for (Actor actor: movie.getActors()) {
	    			data = "MOVIEACTOR::" + actor.getName() + "\n";
	    			writer.write(data);
	    		}
	
	    		for (Director director: movie.getDirectors()) {
	    			data = "MOVIEDIRECTOR::" + director.getName() + "\n";
	    			writer.write(data);
	    		}
			}
	   	} catch(IOException e) {
	   		throw new RuntimeException(e.getMessage());
	   	}

	}

	public void loadData(ObservableList<Actor> actors, ObservableList<Director> directors, ObservableList<Movie> movies)
	{
	   	try (BufferedReader reader = new BufferedReader(new FileReader(directory + filename))) {
	   		String line;
	   		Movie movie = null;
	        while ((line = reader.readLine()) != null) {
	        	String[] parts = line.split("::");
	        	if (parts[0].equals("ACTOR")) {
	        		Actor actor = new Actor(parts[1]);
	        		actors.add(actor);
	        	} else if (parts[0].equals("DIRECTOR")) {
	        		Director director = new Director(parts[1]);
	        		directors.add(director);
	        	} else if (parts[0] .equals("MOVIE")) {
	        		String[] fields = parts[1].split(",");
	        		movie = new Movie(fields[0], 
	        				Enum.valueOf(Category.class, fields[1]), 
	        				Enum.valueOf(Format.class, fields[2]),
	        				fields[3], Integer.parseInt(fields[4]));
	        		movies.add(movie);
	        	} else if (parts[0].equals("MOVIEACTOR")) {
	        		Actor actor = null;
	        		for (int i=0; i<actors.size(); ++i) {
	        			if (actors.get(i).getName().equals(parts[1])) {
	        				actor = actors.get(i);
	        				break;
	        			}
	        		}
	        		
	        		movie.addActor(actor);
	        	} else if (parts[0].equals("MOVIEDIRECTOR")) {
	        		Director director = null;
	        		for (int i=0; i<directors.size(); ++i) {
	        			if (directors.get(i).getName().equals(parts[1])) {
	        				director = directors.get(i);
	        				break;
	        			}
	        		}
	        		movie.addDirector(director);
	        	}
	        }
	   	} catch (IOException e) {
	   		throw new RuntimeException(e.getMessage());
	   	}
	
	}
}
