package com.thefifthcontinent.moviesfx.controllers;

import java.lang.management.PlatformLoggingMXBean;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainController
{
	@FXML
	private MenuItem menu_actors;
	
	@FXML
	private MenuItem menu_directors;
	
	@FXML
	private MenuItem menu_movies;
	
	@FXML
	private MenuItem menu_about;
	
	@FXML
	public void onSaveMoviesClicked()
	{
		
	}
	
	@FXML
	public void onQuitClicked()
	{
		Platform.exit();
	}
	
	@FXML
	public void onActorsClicked()
	{
		
	}
	
	@FXML
	public void onDirectorsClicked()
	{
		
	}
	
	@FXML
	public void onMoviesClicked()
	{
		
	}
	
	@FXML
	public void onAboutClicked()
	{
		
	}
}
