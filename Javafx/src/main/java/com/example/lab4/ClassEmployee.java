package com.example.lab4;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class ClassEmployee {

    String nazwagrupy;
    List<Employee> pracownicy = new ArrayList<>();
    int max_prac,ilosc=0;
    double zapelnienie;
    ClassEmployee(String x, List<Employee> y, int z){
        this.nazwagrupy=x;
        this.pracownicy=y;
        this.max_prac=z;
        if(y.size()>z) System.exit(1);
        this.ilosc=y.size();
        this.zapelnienie= (double) 100* ilosc /max_prac;
    }

//    ClassEmployee(String x, int z){
//        this.nazwagrupy=x;
//        //this.pracownicy=y;
//        this.max_prac=z;
//        this.ilosc=0;
//    }

    public void addEmployee(Employee x) {
        if(pracownicy.size()>=max_prac){
            //System.out.println("W grupie znajduje sie maksymalna ilosc pracownikow\n");
            return;
        }
        for (Employee i : pracownicy) {
            if (i.compareTo(x) == 0 && i.imie == x.imie) {
              //  System.out.println("Pracownik o personaliach "+x.imie+" "+x.nazwisko+ " widnieje już na liście\n");
                return;
            }
        }
            pracownicy.add(x);
           // System.out.println("Dodano praconwika: "+x.imie+" "+x.nazwisko+"\n");
        ilosc++;
        this.zapelnienie= ilosc/max_prac;
    }

    public void addSalary(Employee x, double y){
        x.wynagrodzenie+=y;
    }

    public void removeEmployee(Employee x){
        int pom=0;
        for (Employee i : pracownicy) {
            if (i.compareTo(x) == 0 && i.imie == x.imie) {
                System.out.println("Pracownik o personaliach "+x.imie+" "+x.nazwisko+ " został usunięty z listy\n");
                pracownicy.remove(pom);
                ilosc--;
                this.zapelnienie= ilosc/max_prac;
                return;
            }
            pom++;
        }

    }
    public void changeCondition(Employee x, EmployeeCondition y){
        x.stan_pracownika=y;
    }

    public List<Employee> search(String x){
        Comparator<String> h= new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        List<Employee> pom1=new ArrayList<>();
        for (Employee i : pracownicy) {
            if (h.compare(x,i.nazwisko)==0) {
               i.printing();
                pom1.add(i);
            }
        }
        return pom1;
    }

    public List<Employee> searchPartial(String x) {
        List<Employee> pom1=new ArrayList<>();
        for (Employee i : pracownicy) {
        if(i.nazwisko.length() - x.length() == i.nazwisko.compareTo(x)){
            System.out.println(i.imie+" "+i.nazwisko+"\n");
            pom1.add(i);
        }
        }
        return pom1;
    }

    public int countByCondition(EmployeeCondition x){
        int pom=0;
        for (Employee i : pracownicy) {
            if(i.stan_pracownika == x)
                pom++;
        }
        System.out.println("Liczba pracowników o stanie "+x+" to "+pom+"\n");
        return pom;
    }

    public void summary(){
        for (Employee i : pracownicy){
            i.printing();
        }
    }

    public List<Employee> sortByName(){
        Collections.sort(pracownicy, Collections.reverseOrder());
       // summary();
        return pracownicy;
    }

    public List<Employee> sortBySalary(){
        Comparator<Employee> h= new Comparator<Employee>() {
            @Override
            public int compare(Employee x, Employee y) {
                return Double.compare(x.wynagrodzenie, y.wynagrodzenie);
            }
        };
        Collections.sort(pracownicy,h);
       // summary();
        return pracownicy;
    }

    public Employee max(){
        Employee s2 = Collections.max(pracownicy, (w1, w2) -> {
            return Double.compare(w1.wynagrodzenie, w2.wynagrodzenie);
        });
        return s2;
    }

    public String getNazwagrupy() {
        return nazwagrupy;
    }

    public void setNazwagrupy(String nazwagrupy) {
        this.nazwagrupy = nazwagrupy;
    }

    public ObservableList<Employee> getPracownicy() {
        ObservableList<Employee> a= (ObservableList<Employee>) pracownicy;
        return a;
    }

    public void setPracownicy(List<Employee> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public int getMax_prac() {
        return max_prac;
    }

    public void setMax_prac(int max_prac) {
        this.max_prac = max_prac;

    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public double getZapelnienie() {
        return zapelnienie;
    }

    public void setZapelnienie(double zapelnienie) {
        this.zapelnienie = zapelnienie;
    }
}
