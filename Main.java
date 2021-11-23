package com.company;

// Code by: August Sivkjær Ohm-Hieronymussen & Jakob Høfsten

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {
    public void start(Stage primaryStage)  {
        String url="jdbc:sqlite:C:/Users/Jakob/Desktop/SQL_JDB/testdatabase";
       StudentView view=new StudentView();
       StudentModel model=new StudentModel(url);
       try {
            new StudentController(view, model);
       }    catch (SQLException e){
           e.printStackTrace();
           System.out.println(e.getMessage());
       }
        primaryStage.setTitle("Student Database");
        primaryStage.setScene(new Scene(view.asParent(),600,475));
        primaryStage.show();
}
    public static void main(String[] args){
        launch(args);  }
}