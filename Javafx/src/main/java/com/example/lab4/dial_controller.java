package com.example.lab4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class dial_controller implements Initializable {


    //listview
    @FXML
    private ListView listView;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Dziala>");
        //listview
         listView.getItems().addAll("Golf Balls","Wedges","Irons","Tees","Driver","Putter");
        // listView.getItems().addAll(people);

      //  listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    @FXML
    public void listViewButtonPushed()
    {
        String textAreaString = "";

        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();


    }




}
