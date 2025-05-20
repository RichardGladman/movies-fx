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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DirectorController
{
	private static Stage stage = null;
	
	@FXML
	private ListView<Director> directorsListView;
	
	@FXML
	private TextField searchTextField;
	
	private FilteredList<Director> filteredList;
	
	public void initialize()
	{
		filteredList = new FilteredList<>(DataHandler.getInstance().getDirectors(), (i) -> !i.getName().isEmpty());
		SortedList<Director> sortedList = new SortedList<>(filteredList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		directorsListView.setItems(sortedList);
		directorsListView.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void handleAddButton()
	{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/directorform.fxml"));
    	
    	try {
			GridPane gridPane = loader.load();
			Scene scene = new Scene(gridPane);
			
			stage =  new Stage();
			
			stage.initOwner(directorsListView.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Add New Director - MoviesFX");
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
	}
	
	@FXML
	public void handleEditButton()
	{
		Director director = directorsListView.getSelectionModel().getSelectedItem();
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/directorform.fxml"));
    	loader.setControllerFactory(controllerClass -> new DirectorFormController(director, "edit"));
   	
    	try {
			GridPane gridPane = loader.load();
			Scene scene = new Scene(gridPane);
			
			stage  = new Stage();
			
			stage.initOwner(directorsListView.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("Edit Director - MoviesFX");
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
	}
	
	@FXML
	public void handleDeleteButton()
	{
		Director selectedDirector = directorsListView.getSelectionModel().getSelectedItem();
		
		boolean okToDelete = true;
		for (Movie movie: DataHandler.getInstance().getMovies()) {
			if (movie.getDirectors().contains(selectedDirector)) {
				okToDelete = false;
				break;
			}
		}
				
		if (okToDelete) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Director");
			alert.setHeaderText("Delete director: " + selectedDirector.getName());
			alert.setContentText("Are you sure? Press OK to confirm or cancel to back out.");
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				DataHandler.getInstance().getDirectors().remove(selectedDirector);
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Delete Director");
			alert.setContentText("Cannot delete " + selectedDirector.getName() + ". Present in movie.");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleViewButton()
	{
		Director director = directorsListView.getSelectionModel().getSelectedItem();
		
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/directorview.fxml"));
    	loader.setControllerFactory(controllerClass -> new DirectorViewController(director));
    	
    	try {
			BorderPane borderPane = loader.load();
			Scene scene = new Scene(borderPane);
			
			stage =  new Stage();
			
			stage.initOwner(directorsListView.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setTitle("View Director - MoviesFX");
			
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}    	
	}
	
	@FXML
	public void handleSearchButton()
	{
		Director director = directorsListView.getSelectionModel().getSelectedItem();
		Predicate<Director> searchPredicate = (d) -> d.getName().toLowerCase().contains(searchTextField.getText().trim().toLowerCase());
		Predicate<Director> allPredicate = (d) -> true;
		
		if (searchTextField.getText().trim().isEmpty()) {
			filteredList.setPredicate(allPredicate);
		} else {
			filteredList.setPredicate(searchPredicate);
		}
		
		if (filteredList.contains(director)) {
			directorsListView.getSelectionModel().select(director);
		} else {
			directorsListView.getSelectionModel().selectFirst();
		}
	}
	
	public static Stage getStage()
	{
		return stage;
	}
}
