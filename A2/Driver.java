/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//public class Driver {
public class Driver extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(AdminPane.createPane(), 720, 450);
        
        primaryStage.setTitle("Mini Twitter");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    
    public static void main(String[] args) {
        launch(args);  
    }
    
}
