package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class Venta {
    private String nombre_cliente, titulo_peli;
    private  int num_sala;
    private  float promocion_venta;
    private Date hora_inicio;

    public Venta(String nombre_cliente, String titulo_peli, int num_sala, float promocion_venta, Date hora_inicio) {
        this.nombre_cliente = nombre_cliente;
        this.titulo_peli = titulo_peli;
        this.num_sala = num_sala;
        this.promocion_venta = promocion_venta;
        this.hora_inicio = hora_inicio;
    }
    public Venta(){

    }
//PRUEBAAAAAAdfdfdfdsfdsfds
    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTitulo_peli() {
        return titulo_peli;
    }

    public void setTitulo_peli(String titulo_peli) {
        this.titulo_peli = titulo_peli;
    }

    public int getNum_sala() {
        return num_sala;
    }

    public void setNum_sala(int num_sala) {
        this.num_sala = num_sala;
    }

    public float getPromocion_venta() {
        return promocion_venta;
    }

    public void setPromocion_venta(float promocion_venta) {
        this.promocion_venta = promocion_venta;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public ObservableList<Venta> getDirectors() {
        ObservableList<Venta> obs = FXCollections.observableArrayList();

        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/cine","root","");
            PreparedStatement ps = cn.prepareStatement("SELECT *from listarventas");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombre_cliente  = rs.getNString("nombre_cliente");
                String titulo_peli = rs.getNString("titulo_peli");
                int num_sala  = rs.getInt("num_sala");
                float promocion_venta = rs.getFloat("promocion_venta");
                Date prefesion_dir  = rs.getTime("hora_inicio");

                Venta v = new Venta(nombre_cliente,titulo_peli,num_sala,promocion_venta,prefesion_dir);
                obs.add(v);
            }
        }catch ( SQLException e) {
            e.printStackTrace();
        }

        return obs;
    }



}
