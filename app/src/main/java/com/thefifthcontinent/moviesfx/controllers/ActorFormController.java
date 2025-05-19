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
	
	private Actor actor = null;
	private String mode = "add";
	
	public ActorFormController() {}
	
	public ActorFormController(Actor actor, String mode)
	{
		this.actor = actor;
		this.mode = mode;
	}
	
	public void initialize()
	{
		if (mode.equals("edit")) {
			nameTextField.setText(actor.getName());
		}
	}
	
	@FXML
	public void handleCloseButton()
	{
		ActorController.getStage().close();
	}
	
	@FXML
	public void handleSaveButton()
	{
		String name = nameTextField.getText().trim();
		
		if (actor == null) {
			actor = new Actor(name);
		} else {
			actor.setName(name);
		}
		
		if (!name.equals("")) {
			if (mode.equals("add")) {
				DataHandler.getInstance().getActors().add(actor);
			}
		}
		
		handleCloseButton();
	}
}
