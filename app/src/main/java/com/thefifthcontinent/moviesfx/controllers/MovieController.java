package com.thefifthcontinent.moviesfx.controllers;

import com.thefifthcontinent.moviesfx.model.Movie;
import com.thefifthcontinent.moviesfx.util.DataHandler;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
		
	}
	
	@FXML
	public void handleEditButton()
	{
		
	}
	
	@FXML
	public void handleDeleteButton()
	{
		
	}
	
	@FXML
	public void handleSearchButton()
	{
		
	}
	
	@FXML
	public void handleViewButton()
	{
		
	}
	
	public static Stage getStage()
	{
		return stage;
	}

}
