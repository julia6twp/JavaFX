package com.example.lab4;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;

public class Employee implements Comparable<Employee> {
String imie;
String nazwisko;
EmployeeCondition stan_pracownika;
int rok;
int wynagrodzenie;

    private final ReadOnlyStringWrapper lastNameProperty = new ReadOnlyStringWrapper();

Employee(String i, String n, EmployeeCondition e, int r,int w){
    this.imie=i;
    this.nazwisko=n;
    this.stan_pracownika=e;
    this.rok=r;
    this.wynagrodzenie=w;
    this.lastNameProperty.set(nazwisko);
}
    Employee(String i, String n, String e, int r,int w){
        this.imie=i;
        this.nazwisko=n;
        this.stan_pracownika= stan_pracownika.valueOf(e);
        this.rok=r;
        this.wynagrodzenie=w;
        this.lastNameProperty.set(nazwisko);
    }

    @Override
    public String toString() {
        return  imie + " " +
                " " + nazwisko
                ;
    }

public void printing(){
    System.out.println("Imie: "+imie+"\nNaziwsko: "+nazwisko+"\nStan: "+stan_pracownika+"\nRok urodzenia: "+rok+"\nWynagrodzenie: "+wynagrodzenie+"\n");
}

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public ReadOnlyStringWrapper lastNameProperty() {

        return lastNameProperty;
    }

    public EmployeeCondition getStan_pracownika() {
        return stan_pracownika;
    }

    public void setStan_pracownika(EmployeeCondition e) {
        this.stan_pracownika = e;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    @Override
    public int compareTo(Employee o) {
        return o.nazwisko.compareTo(nazwisko);
    }
}
