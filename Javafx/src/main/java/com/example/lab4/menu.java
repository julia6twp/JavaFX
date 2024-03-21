package com.example.lab4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class menu {

    @FXML
    //private TextField test;
    private  Stage stage;
    private  Scene scene;
    private Parent root;


    public void btn_pracownicy(ActionEvent actionEvent) throws IOException {
        //  primaryStage.setScene(nowaScena);
        //fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        root = FXMLLoader.load(getClass().getResource("pracownicy.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

    }
    public void btn_grupy(ActionEvent actionEvent) throws IOException {
        //  primaryStage.setScene(nowaScena);
        //fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        root = FXMLLoader.load(getClass().getResource("grupy.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

    }
    public void btn_menu(ActionEvent actionEvent) throws IOException {
        //   primaryStage.setScene(nowaScena);
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
