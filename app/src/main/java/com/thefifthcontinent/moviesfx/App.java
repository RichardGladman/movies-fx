package com.thefifthcontinent.moviesfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
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
    }
}
