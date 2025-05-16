package com.thefifthcontinent.moviesfx.controllers;

import java.lang.management.PlatformLoggingMXBean;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class MainController
{
	@FXML
	private BorderPane borderPane;
	
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
