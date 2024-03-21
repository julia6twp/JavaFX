package com.example.lab4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


import java.io.IOException;
import java.net.URL;
import java.util.*;


public class container_controller implements Initializable {

    private boolean mod;
    @FXML
    private TableView<Employee> tableView2;
    @FXML
    private TableColumn<Employee,String> imie;
    @FXML
    private TableColumn<Employee,String> nazwisko;

    @FXML
    private TextField group;
    @FXML
    private TextField max_;
    //
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    //tabela
    @FXML
    private TableView<ClassEmployee> tableView;

    @FXML
    private TableColumn<ClassEmployee,String> nazwa_grupy;
    @FXML
    private TableColumn<ClassEmployee,Integer> ilosc_osob;
    @FXML
    private TableColumn<ClassEmployee,Integer> max_osob;
    @FXML
    private TableColumn<ClassEmployee,Double> zapelnienie;

    @FXML
    private TextField group_name;
    @FXML
    private TextField num_of_pp;
    @FXML
    private TextField max_pp;
    @FXML
    private TextField filling;

    //tworzenie grup pomocnicze
    ObservableList<Employee> pom1 = FXCollections.observableArrayList();
    ObservableList<Employee> pom2 = FXCollections.observableArrayList();
    ObservableList<Employee> pom3 = FXCollections.observableArrayList();

    //lista pracownikow
    ObservableList<Employee> people;

    //
    ObservableList<ClassEmployee> cont1 ;

    //listview
    @FXML
    private ListView listView;
    @FXML
    private Button myButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nazwa_grupy.setCellValueFactory(new PropertyValueFactory<ClassEmployee, String>("nazwagrupy"));
        ilosc_osob.setCellValueFactory(new PropertyValueFactory<ClassEmployee, Integer>("ilosc"));
        max_osob.setCellValueFactory(new PropertyValueFactory<ClassEmployee, Integer>("max_prac"));
        zapelnienie.setCellValueFactory(new PropertyValueFactory<ClassEmployee, Double>("zapelnienie"));

        nazwa_grupy.setCellFactory(TextFieldTableCell.forTableColumn());
        ilosc_osob.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        max_osob.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        zapelnienie.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        imie.setCellValueFactory(new PropertyValueFactory<Employee, String>("imie"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<Employee, String>("nazwisko"));
        //tableView2.setItems();
        imie.setCellFactory(TextFieldTableCell.forTableColumn());
        nazwisko.setCellFactory(TextFieldTableCell.forTableColumn());

        mod=false;

        //tworzenie grup i dodawania pracownikow
        getEmployee();
        tableView.setItems(getClassEmployee());

        cont1= tableView.getItems();
        //tableView.setEditable(true);

        //listview
       // listView.getItems().addAll("Golf Balls","Wedges","Irons","Tees","Driver","Putter");
        listView.getItems().addAll(people);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void listViewButtonPushed()
    {
        String textAreaString = "";

        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();


    }

    public ObservableList<ClassEmployee> getClassEmployee()
    {
        ObservableList<ClassEmployee> people = FXCollections.observableArrayList();
        //List<Employee> pracownicy1=new ArrayList<>();
        people.add(new ClassEmployee("Alfa",pom1,8));
        people.add(new ClassEmployee("Beta",pom2,10));

        return people;
    }


    @FXML
    void modify(ActionEvent event) {


        if (mod == false) {
            myButton.setStyle("-fx-background-color: yellow;");
            tableView.setEditable(true); mod=true;
        }
        else if(mod==true) {
            myButton.setStyle("-fx-background-color: #ffffa1;");
            tableView.setEditable(false); mod=false;
        }
        cont1 = tableView.getItems();
    }

    ObservableList<Employee> pobierz(){
        ObservableList<Employee> xx = listView.getSelectionModel().getSelectedItems();
        return  xx;
    }

    //dodaj
    @FXML
    void submit(ActionEvent event) {
      //  ObservableList<Employee> xx = listView.getSelectionModel().getSelectedItems();
      //  ObservableList<Employee> xx2 =pobierz();
        ObservableList<Employee> xx2 =pom3;
        ClassEmployee e = new ClassEmployee(group.getText(),xx2,Integer.parseInt(max_.getText()));

        ObservableList<ClassEmployee> es = tableView.getItems();
        es.add(e);
        //dataList.addAll(e);
        // dataList.add(e);
        tableView.setItems(es);
        cont1= tableView.getItems();
    }

    @FXML
    public void changeGroupName(TableColumn.CellEditEvent edittedCell)
    {
        ClassEmployee personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setNazwagrupy(edittedCell.getNewValue().toString());
    }
    @FXML
    public void changemax_pp(TableColumn.CellEditEvent edittedCell)
    {
        ClassEmployee personSelected =  tableView.getSelectionModel().getSelectedItem();

        if (edittedCell.getNewValue() != null && edittedCell.getNewValue() instanceof Integer) {
            personSelected.setMax_prac((Integer) edittedCell.getNewValue());
            personSelected.setZapelnienie((double) 100*personSelected.ilosc/personSelected.max_prac);
        } else {

        }

    }

    @FXML
    void removeClassEmployee(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
        cont1 = tableView.getItems();
    }

    @FXML
    void Pokaz(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        ClassEmployee personSelected =tableView.getItems().get(selectedID);
        tableView2.setItems(personSelected.getPracownicy());
    }


    public void getEmployee()
    {
        people = FXCollections.observableArrayList();
        people.add(new Employee("Julia","Kluk",EmployeeCondition.obecny,2002,1342));

        people.add(new Employee("Wiktor","Kluk",EmployeeCondition.chory,2002,4342));

        people.add(new Employee("Wiktor","Kowalski",EmployeeCondition.chory,2002,4312));

        people.add(new Employee("Jan","Kowalski",EmployeeCondition.chory,2002,4302));

        people.add(new Employee("Mateusz","Sanicki",EmployeeCondition.obecny,2002,4012));

        //uzupelnianie jednej grupy
        pom1.add((new Employee("Julia","Kluk",EmployeeCondition.obecny,2002,1342)));
        pom1.add((new Employee("Wiktor","Kluk",EmployeeCondition.chory,2002,4342)));

        pom3.add(new Employee("Wiktor","Kowalski",EmployeeCondition.chory,2002,4312));
        pom3.add(new Employee("Jan","Kowalski",EmployeeCondition.chory,2002,4302));
//        return people;

    }
    @FXML
    void btn_menu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
