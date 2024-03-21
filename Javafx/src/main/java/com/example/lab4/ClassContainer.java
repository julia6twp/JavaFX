package com.example.lab4;

import java.util.*;

public class ClassContainer {
    Map<String, ClassEmployee> grupy  = new HashMap<>();

    public void addClass(String x, double y){
        if(grupy.size()>0){
        Set<Map.Entry<String,ClassEmployee>> e = grupy.entrySet();
        Iterator<Map.Entry<String, ClassEmployee>> grupyI= e.iterator();
        Scanner scanner = new Scanner(System.in);
        while(grupyI.hasNext()){
            Map.Entry<String, ClassEmployee> entry = grupyI.next();
            if(x.compareTo(entry.getKey())==0) {
                System.out.println("Podana grupa już istnieje, czy chcesz ją nadpisać? 1 - tak, 0 - nie");
                int a=scanner.nextInt();
                if(a==1)
                    break;
                else {
                    System.out.println("Grupa nie została dodana do listy");
                    return;
                }
            }
        }
        }
        List<Employee> pracownicy1 = new ArrayList<>();
        ClassEmployee lista= new ClassEmployee(x,pracownicy1,(int)y);
        grupy.put(x,lista);

    }
    public void removeClass(String x){
    grupy.remove(x);
    }

    public List<ClassEmployee> findEmpty(){
        List<ClassEmployee> pracownicy1 = new ArrayList<>();
        Set<Map.Entry<String,ClassEmployee>> e = grupy.entrySet();
        Iterator<Map.Entry<String, ClassEmployee>> grupyI= e.iterator();
        while(grupyI.hasNext()){
           Map.Entry<String, ClassEmployee> entry = grupyI.next();
           // ClassEmployee a= grupy.get(entry.getKey());
            if(grupy.get(entry.getKey()).pracownicy.isEmpty()) pracownicy1.add(grupy.get(entry.getKey()));
           //entry.getKey();
        }
        return pracownicy1;
    }

    public void summary(){
        Set<Map.Entry<String,ClassEmployee>> e = grupy.entrySet();
        Iterator<Map.Entry<String, ClassEmployee>> grupyI= e.iterator();
        while(grupyI.hasNext()){
            Map.Entry<String, ClassEmployee> entry = grupyI.next();
            // ClassEmployee a= grupy.get(entry.getKey());
            int a=grupy.get(entry.getKey()).pracownicy.size();
            System.out.println("Nazwa grupy: "+grupy.get(entry.getKey()).nazwagrupy+"\tZapełnienie: "+((double)a/(double)grupy.get(entry.getKey()).max_prac)*100.+" %\n");
            //entry.getKey();
        }
    }
}
