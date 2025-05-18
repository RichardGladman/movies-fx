package com.thefifthcontinent.moviesfx.controllers;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ActorFormController
{
	@FXML
	private TextField nameTextField;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button closeButton;
	
	@FXML
	public void handleCloseButton()
	{
		ActorController.getStage().close();
	}
	
	@FXML
	public void handleSaveButton()
	{
		String name = nameTextField.getText().trim();
		Actor actor = new Actor(name);
		
		if (!name.equals("")) {
			DataHandler.getInstance().getActors().add(actor);
		}
		
		nameTextField.clear();
	}
}
