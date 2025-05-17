package com.thefifthcontinent.moviesfx;

import com.thefifthcontinent.moviesfx.util.DataHandler;
import com.thefifthcontinent.moviesfx.util.FileHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	private static Stage stage;
	
    public static void run(String[] args) 
    {
    	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("/fxml/root.fxml"));
    			
        Parent root = loader.load();

        primaryStage.setTitle("Movies FX");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
        
        stage = primaryStage;
    }
    
    @Override
    public void init()
    {
    	DataHandler dataHandler = DataHandler.getInstance();
    	FileHandler fHandler = new FileHandler(System.getProperty("user.home") + "/Documents/moviesfx/", "movies.txt");
    	
    	try {
    		fHandler.loadData(dataHandler.getActors(), dataHandler.getDirectors(), dataHandler.getMovies());
    	} catch (RuntimeException e) {
    		System.err.println("Failed to load data " + e.getMessage());
    	}
    }
    
    @Override
    public void stop()
    {
    	DataHandler dataHandler = DataHandler.getInstance();
    	FileHandler fHandler = new FileHandler(System.getProperty("user.home") + "/Documents/moviesfx/", "movies.txt");
    	
    	try {
    		fHandler.saveData(dataHandler.getActors(), dataHandler.getDirectors(), dataHandler.getMovies());
    	} catch (RuntimeException e) {
    		System.err.println("Failed to save data " + e.getMessage());
    	}
    }
    
    public static Stage getStage() {
    	return stage;
    }
}
