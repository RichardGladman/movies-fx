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
	
	ObservableList<Actor> deselectedActors;
	ObservableList<Actor> selectedActors; 
	ObservableList<Director> deselectedDirectors;
	ObservableList<Director> selectedDirectors; 
	
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
		
		deselectedActors = FXCollections.observableArrayList(DataHandler.getInstance().getActors());
		SortedList<Actor> sortedActors = new SortedList<>(deselectedActors , (o1, o2)-> o1.getName().compareTo(o2.getName()));
		actorsComboBox.setItems(sortedActors);
		actorsComboBox.getSelectionModel().selectFirst();
		
		deselectedDirectors = FXCollections.observableArrayList(DataHandler.getInstance().getDirectors());
		SortedList<Director> sortedDirectors = new SortedList<>(deselectedDirectors, (o1, o2)-> o1.getName().compareTo(o2.getName()));
		directorsComboBox.setItems(sortedDirectors);
		directorsComboBox.getSelectionModel().selectFirst();
		
		if (mode.equals("edit")) {
			titleTextField.setText(movie.getTitle());
			categoryComboBox.getSelectionModel().select(movie.getCategory());
			formatComboBox.getSelectionModel().select(movie.getFormat());
			certificateTextField.setText(movie.getCertificate());
			runningTimeTextField.setText("" + movie.getRunningTime());
			
			selectedActors = FXCollections.observableArrayList(movie.getActors());
			actorsListView.setItems(selectedActors);
			deselectedActors .removeAll(movie.getActors());
			
			selectedDirectors = FXCollections.observableArrayList(movie.getDirectors());
			directorsListView.setItems(selectedDirectors);
			deselectedDirectors.removeAll(movie.getDirectors());
		} else {
			selectedActors = FXCollections.observableArrayList();
			actorsListView.setItems(selectedActors);
			
			selectedDirectors  = FXCollections.observableArrayList();
			directorsListView.setItems(selectedDirectors);
			
		}
	}
	
	@FXML
	public void handleRemoveActorMenuItem()
	{
		Actor selectedActor = actorsListView.getSelectionModel().getSelectedItem();
		if (selectedActor == null) {
			return;
		}
		
		selectedActors.remove(selectedActor);
		deselectedActors.add(selectedActor);
		actorsComboBox.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void handleRemoveDirectorMenuItem()
	{
		Director selectedDirector = directorsListView.getSelectionModel().getSelectedItem();
		if (selectedDirector == null) {
			return;
		}
		
		selectedDirectors.remove(selectedDirector);
		deselectedDirectors.add(selectedDirector);
		actorsComboBox.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void handleAddActorButton()
	{
		Actor selectedActor = actorsComboBox.getSelectionModel().getSelectedItem();
		if (selectedActor == null) {
			return;
		}
		
		selectedActors.add(selectedActor);
		deselectedActors.remove(selectedActor);
		actorsComboBox.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void handleAddDirectorsButton()
	{
		Director selectedDirector = directorsComboBox.getSelectionModel().getSelectedItem();
		if (selectedDirector == null) {
			return;
		}
		
		selectedDirectors.add(selectedDirector);
		deselectedDirectors.remove(selectedDirector);
		directorsComboBox.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void handleSaveButton()
	{
		String title = titleTextField.getText().trim();
		Category category = categoryComboBox.getSelectionModel().getSelectedItem();
		Format format = formatComboBox.getSelectionModel().getSelectedItem();
		String certificate = certificateTextField.getText().trim();
		int runningTime = Integer.parseInt(runningTimeTextField.getText().trim());
		
		if (mode.equals("add")) {
			movie = new Movie(title, category, format, certificate, runningTime);
			DataHandler.getInstance().getMovies().add(movie);
		} else {
			movie.setTitle(title);
			movie.setCategory(category);
			movie.setFormat(format);
			movie.setCertificate(certificate);
			movie.setRunningTime(runningTime);
		}
		
		//  Simpler to clear and re-add actors and directors
		movie.getActors().clear();
		for (Actor actor: selectedActors) {
			movie.getActors().add(actor);
		}
		
		movie.getDirectors().clear();
		for (Director director: selectedDirectors) {
			movie.getDirectors().add(director);
		}
		
		handleCloseButton();
	}
	
	@FXML
	public void handleCloseButton()
	{
		MovieController.getStage().close();
	}
}
