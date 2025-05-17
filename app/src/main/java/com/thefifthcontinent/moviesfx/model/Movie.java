package com.thefifthcontinent.moviesfx.model;

import java.util.ArrayList;
import java.util.List;

public class Movie
{
	private String title;
	private Category category;
	private String certificate;
	private int runningTime;
	private List<Director> directors;
	private List<Actor> actors;
	
	public Movie(String title, Category category, String certificate, int runningTime)
	{
		super();
		this.title = title;
		this.category = category;
		this.certificate = certificate;
		this.runningTime = runningTime;
		this.directors = new ArrayList<>();
		this.actors = new ArrayList<>();
	}
	
	public void addDirector(Director director)
	{
		directors.add(director);
	}
	
	public void removeDirector(Director director)
	{
		directors.remove(director);
	}
	
	public void addActor(Actor actor)
	{
		actors.add(actor);
	}
	
	public void removeActor(Actor actor)
	{
		actors.remove(actor);
	}

	public String getTitle()
	{
		return title;
	}

	public Category getCategory()
	{
		return category;
	}

	public String getCertificate()
	{
		return certificate;
	}

	public int getRunningTime()
	{
		return runningTime;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public void setCertificate(String certificate)
	{
		this.certificate = certificate;
	}

	public void setRunningTime(int runningTime)
	{
		this.runningTime = runningTime;
	}

	public void setDirectors(List<Director> directors)
	{
		this.directors = directors;
	}

	public void setActors(List<Actor> stars)
	{
		this.actors = stars;
	}

	public List<Director> getDirectors()
	{
		return directors;
	}

	public List<Actor> getActors()
	{
		return actors;
	}
	
	@Override
	public String toString()
	{
		String data = "%-50s %-10s %-4s %d".formatted(title, category, certificate, runningTime);

		if (actors.size() > 0) {
			data += "\nActors:\n";
			for (Actor a: actors) {
				data += a.getName() + "\n";
			}
		}
		
		if (directors.size() > 0) {
			data += "Directors:\n";
			for (Director d: directors) {
				data += d.getName() + "\n";
			}
		}
		
		return data;
	}
}
