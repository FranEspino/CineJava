package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Director {
    private  String  nombre_dir, nacionalidad_dir, profesion_dir;

    public String getNombre_dir() {
        return nombre_dir;
    }

    public void setNombre_dir(String nombre_dir) {
        this.nombre_dir = nombre_dir;
    }

    public String getNacionalidad_dir() {
        return nacionalidad_dir;
    }

    public void setNacionalidad_dir(String nacionalidad_dir) {
        this.nacionalidad_dir = nacionalidad_dir;
    }

    public String getProfesion_dir() {
        return profesion_dir;
    }

    public void setProfesion_dir(String profesion_dir) {
        this.profesion_dir = profesion_dir;
    }

    public  Director(String nombre_dir, String nacionalidad_dir, String profesion_dir ){
            this.nombre_dir = nombre_dir;
            this.nacionalidad_dir = nacionalidad_dir;
            this.profesion_dir = nacionalidad_dir;
        }
    public  Director(){

    }

    public   ObservableList<Director> getDirectors() {
        ObservableList<Director> obs = FXCollections.observableArrayList();

        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/cine","root","");
            PreparedStatement ps = cn.prepareStatement("SELECT nombre_dir, nacionalidad_dir, profesion_dir FROM director");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombre_dir  = rs.getNString("nombre_dir");
                String nacionalidad_dir = rs.getNString("nacionalidad_dir");
                String prefesion_dir  = rs.getNString("profesion_dir");

                Director d = new Director(nombre_dir,nacionalidad_dir,prefesion_dir);
                obs.add(d);
            }
        }catch ( SQLException e) {
            e.printStackTrace();
        }

        return obs;
    }
}
