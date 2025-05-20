package com.thefifthcontinent.moviesfx.controllers;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.model.Category;
import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.model.Format;
import com.thefifthcontinent.moviesfx.model.Movie;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MovieFormController
{
	@FXML private TextField titleTextField;
	@FXML private ComboBox<Category> categoryComboBox;
	@FXML private ComboBox<Format> formatComboBox;
	@FXML private TextField certificateTextField;
	@FXML private TextField runningTimeTextField;
	@FXML private ListView<Actor> actorsListView;
	@FXML private ComboBox<Actor> actorsComboBox;
	@FXML private Button addActorButton;
	@FXML private ListView<Director> directorsListView;
	@FXML private ComboBox<Director> directorsComboBox;
	@FXML private Button addDirectorButton;
	@FXML private Button saveButton;
	@FXML private Button closeButton;
	
	private Movie movie;
	private String mode;
	
	public MovieFormController(Movie movie, String mode)
	{
		this.movie = movie;
		this.mode = mode;
	}
	
	public void initialize()
	{
		categoryComboBox.setItems(FXCollections.observableArrayList(Category.values()));
		categoryComboBox.getSelectionModel().selectFirst();
		
		formatComboBox.setItems(FXCollections.observableArrayList(Format.values()));
		formatComboBox.getSelectionModel().selectFirst();
		
		ObservableList<Actor> actors = FXCollections.observableArrayList(DataHandler.getInstance().getActors());
		SortedList<Actor> sortedActors = new SortedList<>(actors, (o1, o2)-> o1.getName().compareTo(o2.getName()));
		actorsComboBox.setItems(sortedActors);
		actorsComboBox.getSelectionModel().selectFirst();
		
		ObservableList<Director> directors = FXCollections.observableArrayList(DataHandler.getInstance().getDirectors());
		SortedList<Director> sortedDirectors = new SortedList<>(directors, (o1, o2)-> o1.getName().compareTo(o2.getName()));
		directorsComboBox.setItems(sortedDirectors);
		directorsComboBox.getSelectionModel().selectFirst();
		
		if (mode.equals("edit")) {
			titleTextField.setText(movie.getTitle());
			categoryComboBox.getSelectionModel().select(movie.getCategory());
			formatComboBox.getSelectionModel().select(movie.getFormat());
			certificateTextField.setText(movie.getCertificate());
			runningTimeTextField.setText("" + movie.getRunningTime());
			
			actorsListView.setItems(FXCollections.observableArrayList(movie.getActors()));
			actors.removeAll(movie.getActors());
			
			directorsListView.setItems(FXCollections.observableArrayList(movie.getDirectors()));
			directors.removeAll(movie.getDirectors());
		}
	}
}
