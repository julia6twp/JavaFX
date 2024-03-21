package com.example.lab4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class employee_controller implements Initializable {
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee,String> imie;
    @FXML
    private TableColumn<Employee,String> nazwisko;
    @FXML
    private TableColumn<Employee,EmployeeCondition> stan;
    @FXML
    private TableColumn<Employee,Integer> rok;
    @FXML
    private TableColumn<Employee,Integer> wynagrodzenie;


    @FXML
    private TextField name;
    @FXML
    private TextField lastname;
    @FXML
    private TextField condition;
    @FXML
    private TextField birthday;
    @FXML
    private TextField salary;
    @FXML
    //private TextField test;
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private Button myButton;

    @FXML
    private TextField searchText;

    private boolean mod;
    ObservableList<Employee> ver1 ;

    ClassEmployee a;


@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    mod=false;
        imie.setCellValueFactory(new PropertyValueFactory<Employee, String>("imie"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<Employee, String>("nazwisko"));
        stan.setCellValueFactory(new PropertyValueFactory<Employee, EmployeeCondition>("stan_pracownika"));
        rok.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("rok"));
        wynagrodzenie.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("wynagrodzenie"));

    tableView.setItems(getEmployee());
    searchText.setOnKeyPressed(this::search);
//    tableView.setEditable(true);
    imie.setCellFactory(TextFieldTableCell.forTableColumn());
    nazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
    stan.setCellFactory(ComboBoxTableCell.forTableColumn(EmployeeCondition.values()));
    rok.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    wynagrodzenie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    nazwisko.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    ver1= tableView.getItems();

    }

    //dodaj
    @FXML
    void submit(ActionEvent event) {
        Employee e = new Employee(name.getText(),lastname.getText(),condition.getText(),Integer.parseInt(birthday.getText()),
                Integer.parseInt(salary.getText()));
        ObservableList<Employee> es = tableView.getItems();
        es.add(e);
        //dataList.addAll(e);
       // dataList.add(e);
        tableView.setItems(es);
        ver1= tableView.getItems();
    }

    //usun
    @FXML
    void removeEmployee(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        tableView.getItems().remove(selectedID);
        ver1 = tableView.getItems();
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
        ver1 = tableView.getItems();
    }

    //zmien
    @FXML
    public void changeName(CellEditEvent edittedCell)
    {
        Employee personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setImie(edittedCell.getNewValue().toString());
        ver1 = tableView.getItems();
    }
    @FXML
    public void changeCondition(CellEditEvent edittedCell)
    {
        Employee personSelected =  tableView.getSelectionModel().getSelectedItem();
        //personSelected.setNazwisko(edittedCell.getNewValue().toString());
        stan.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setStan_pracownika(event.getNewValue());
            // Dodatkowe działania po edycji
        });
        ver1 = tableView.getItems();
    }

    @FXML
    public void changeBirthday(CellEditEvent edittedCell)
    {
        Employee personSelected =  tableView.getSelectionModel().getSelectedItem();
//        personSelected.setRok((Integer) edittedCell.getNewValue());
        if (edittedCell.getNewValue() != null && edittedCell.getNewValue() instanceof Integer) {
            personSelected.setRok((Integer) edittedCell.getNewValue());
        } else {
            // Obsługa przypadku, gdy newValue jest null lub nie jest instancją Integer
        }
        ver1 = tableView.getItems();
    }

    @FXML
    public void changesalary(CellEditEvent edittedCell)
    {
        Employee personSelected =  tableView.getSelectionModel().getSelectedItem();
//        personSelected.setWynagrodzenie((Integer) edittedCell.getNewValue());

        if (edittedCell.getNewValue() != null && edittedCell.getNewValue() instanceof Integer) {
            personSelected.setWynagrodzenie((Integer) edittedCell.getNewValue());
        } else {
            // Obsługa przypadku, gdy newValue jest null lub nie jest instancją Integer
        }
        ver1 = tableView.getItems();
    }

    @FXML
    public void changeLastname(CellEditEvent edittedCell)
    {
        Employee personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setNazwisko(edittedCell.getNewValue().toString());
        ver1 = tableView.getItems();
    }

    public ObservableList<Employee>  getEmployee()
    {
        ObservableList<Employee> people = FXCollections.observableArrayList();
        people.add(new Employee("Julia","Kluk",EmployeeCondition.obecny,2002,1342));
       // dataList.addAll(people);
        people.add(new Employee("Wiktor","Kluk",EmployeeCondition.chory,2002,4342));
       // dataList.addAll(people);
        people.add(new Employee("Wiktor","Kowalski",EmployeeCondition.chory,2002,4312));
       // dataList.addAll(people);
        return people;
    }

    @FXML
    public void btn_menu(ActionEvent actionEvent) throws IOException {
        //   primaryStage.setScene(nowaScena);
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public ObservableList<Employee> searchPartial(String x) {
       // ver1 = tableView.getItems();
        ObservableList<Employee> pom1 = FXCollections.observableArrayList();
        for (Employee i : ver1) {
            if(i.nazwisko.length() - x.length() == i.nazwisko.compareTo(x)){
                System.out.println(i.imie+" "+i.nazwisko+"\n");
                pom1.add(i);
            }
        }
        return pom1;
    }
    @FXML
    void search(KeyEvent event){

        if(event.getCode() == KeyCode.ENTER){
            if(searchText.getText().isEmpty()) {
                System.out.println("Test 1");
                tableView.setItems(ver1);

            }
            else {
                System.out.println("Test 2");
                ObservableList<Employee> es = searchPartial(searchText.getText());
                tableView.setItems(es);
            }



        }
    }



}
