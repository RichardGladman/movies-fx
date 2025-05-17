package com.thefifthcontinent.moviesfx.controllers;

import java.io.IOException;

import com.thefifthcontinent.moviesfx.App;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/actors.fxml"));
    	
    	try {
			BorderPane centerPane = loader.load();
			borderPane.setCenter(centerPane);
			App.getStage().setTitle("Manage Actors - MoviesFX");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
	}
	
	@FXML
	public void onDirectorsClicked()
	{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/directors.fxml"));
    	
    	try {
			BorderPane centerPane = loader.load();
			borderPane.setCenter(centerPane);
			App.getStage().setTitle("Manage Directors - MoviesFX");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void onMoviesClicked()
	{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/movies.fxml"));
    	
    	try {
			BorderPane centerPane = loader.load();
			borderPane.setCenter(centerPane);
			App.getStage().setTitle("Manage Movies - MoviesFX");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void onAboutClicked()
	{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/about.fxml"));
    	
    	try {
			VBox centerPane = loader.load();
			borderPane.setCenter(centerPane);
			App.getStage().setTitle("About - MoviesFX");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
