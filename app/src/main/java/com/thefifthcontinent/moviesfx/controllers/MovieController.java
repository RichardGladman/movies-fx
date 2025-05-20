package com.thefifthcontinent.moviesfx.controllers;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

import com.thefifthcontinent.moviesfx.model.Director;
import com.thefifthcontinent.moviesfx.model.Movie;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MovieController
{
	private static Stage stage = null;

	@FXML
	private ListView<Movie> moviesListView;
	
	@FXML
	private TextField searchTextField;
	
	private FilteredList<Movie> filteredList;
	
	public void initialize()
	{
		filteredList = new FilteredList<>(DataHandler.getInstance().getMovies(), (i) -> true);
		SortedList<Movie> sortedList = new SortedList<>(filteredList, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
		moviesListView.setItems(sortedList);
		moviesListView.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void handleAddButton()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/movieform.fxml"));
		loader.setControllerFactory(controllerClass -> new MovieFormController(null, "add"));
		
		try {
			GridPane gridPane = loader.load();
			Scene scene = new Scene(gridPane);
			
			stage = new Stage();
			
			stage.initOwner(moviesListView.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Add New Movie - MoviesFX");
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleEditButton()
	{
		Movie movie = moviesListView.getSelectionModel().getSelectedItem();
		if (movie == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Movie Selected - MoviesFX");
			alert.setHeaderText(null);
			alert.setContentText("Select the movie you want to edit");
			alert.showAndWait();
			return;
		}
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/movieform.fxml"));
		loader.setControllerFactory(controllerClass -> new MovieFormController(movie, "edit"));
		
		try {
			GridPane gridPane = loader.load();
			Scene scene = new Scene(gridPane);
			
			stage = new Stage();
			
			stage.initOwner(moviesListView.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Edit Movie - MoviesFX");
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleDeleteButton()
	{
		Movie selectedMovie = moviesListView.getSelectionModel().getSelectedItem();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Movie");
		alert.setHeaderText("Delete movie: " + selectedMovie.getTitle());
		alert.setContentText("Are you sure? Press OK to confirm or cancel to back out.");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			DataHandler.getInstance().getMovies().remove(selectedMovie);
		}
	}
	
	@FXML
	public void handleSearchButton()
	{
		Movie movie = moviesListView.getSelectionModel().getSelectedItem();
		Predicate<Movie> searchPredicate = m -> m.getTitle().toLowerCase().contains(searchTextField.getText().trim().toLowerCase());
		Predicate<Movie> allPredicate = m -> true;
		
		if (searchTextField.getText().trim().isEmpty()) {
			filteredList.setPredicate(allPredicate);
		} else {
			filteredList.setPredicate(searchPredicate);
		}
		
		if (filteredList.contains(movie)) {
			moviesListView.getSelectionModel().select(movie);
		} else {
			moviesListView.getSelectionModel().selectFirst();
		}
	}
	
	@FXML
	public void handleViewButton()
	{
		Movie movie = moviesListView.getSelectionModel().getSelectedItem();
		
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/movieview.fxml"));
    	loader.setControllerFactory(controllerClass -> new MovieViewController(movie));
    	
    	try {
			BorderPane borderPane = loader.load();
			Scene scene = new Scene(borderPane);
			
			stage =  new Stage();
			
			stage.initOwner(moviesListView.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("View Movie - MoviesFX");
			
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
