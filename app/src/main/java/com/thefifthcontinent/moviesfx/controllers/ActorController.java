package com.thefifthcontinent.moviesfx.controllers;

import java.io.IOException;
import java.util.function.Predicate;

import com.thefifthcontinent.moviesfx.model.Actor;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ActorController
{
	private static Stage stage = new Stage();
	
	@FXML
	private ListView<Actor> actorsListView;
	
	@FXML
	private Button addButton;
	
	@FXML
	private TextField searchTextField;
	
	private FilteredList<Actor> filteredList;
	
	public void initialize()
	{
		filteredList = new FilteredList<>(DataHandler.getInstance().getActors(), (i) -> !i.getName().isEmpty());
		SortedList<Actor> sortedList = new SortedList<>(filteredList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
		actorsListView.setItems(sortedList);
		actorsListView.getSelectionModel().selectFirst();
	}
	
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
	
	@FXML
	public void handleSearchButton()
	{
		Actor selectedActor = actorsListView.getSelectionModel().getSelectedItem();
		Predicate<Actor> searchPredicate = (a) -> a.getName().toLowerCase().contains(searchTextField.getText().trim().toLowerCase());
		Predicate<Actor> allPredicate = (a) -> !a.getName().isEmpty();
		
		if (searchTextField.getText().trim().isEmpty()) {
			filteredList.setPredicate(allPredicate);
		} else {
			filteredList.setPredicate(searchPredicate);
		}
		
		if (filteredList.contains(selectedActor)) {
			actorsListView.getSelectionModel().select(selectedActor);
		} else {
			actorsListView.getSelectionModel().selectFirst();
		}
	}
	
	public static Stage getStage()
	{
		return stage;
	}
}
