package com.thefifthcontinent.moviesfx.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AboutController
{
	@FXML
	private ImageView logo;
	
	public void initialize()
	{
		logo.setImage(new Image("/images/logo.png"));
	}
}
