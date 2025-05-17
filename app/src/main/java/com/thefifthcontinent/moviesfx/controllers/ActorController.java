package com.thefifthcontinent.moviesfx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ActorController
{
	private static Stage stage = new Stage();
	
	@FXML
	private Button addButton;
	
	@FXML
	public void handleAddButton()
	{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/actorform.fxml"));
    	
    	try {
			GridPane gridPane = loader.load();
			Scene scene = new Scene(gridPane);
			
			stage.initOwner(addButton.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Add New Actor - MoviesFX");
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
	}
	
	public static Stage getStage()
	{
		return stage;
	}
}
