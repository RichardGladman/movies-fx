package com.thefifthcontinent.moviesfx.controllers;

import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DirectorFormController
{
	@FXML
	private TextField nameTextField;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button closeButton;
	
	private Director director = null;
	private String mode = "add";
	
	public DirectorFormController() {}
	
	public DirectorFormController(Director director, String mode)
	{
		this.director = director;
		this.mode = mode;
	}
	
	public void initialize()
	{
		if (mode.equals("edit")) {
			nameTextField.setText(director.getName());
		}
	}
	
	@FXML
	public void handleCloseButton()
	{
		DirectorController.getStage().close();
	}
	
	@FXML
	public void handleSaveButton()
	{
		String name = nameTextField.getText().trim();
		
		if (director == null) {
			director = new Director(name);
		} else {
			director.setName(name);
		}
		
		if (!name.equals("")) {
			if (mode.equals("add")) {
				DataHandler.getInstance().getDirectors().add(director);
			}
		}
		
		handleCloseButton();
	}
}
