package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.sql.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Ventas implements Initializable {



    @FXML
    private ImageView img_ticket;
    @FXML
    private TextField txt_nombrecliente;
    @FXML
    private TextField text_edad;
    @FXML
    private TextField txt_peli;
    @FXML
    private TextField text_importe;
    @FXML
    private TextField text_numsala;
    @FXML
    private TextField text_horainicio;
    @FXML
    private TextField text_horafin;
    @FXML
    private TextField text_cartelera;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("resources/ticket.png");
        img_ticket.setImage(image);

    }


    @FXML
    public void cliente(MouseEvent mouseEvent) throws SQLException {
        int id_ultimocli = 0;
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/cine", "root", "");
            PreparedStatement ps = cn.prepareStatement("INSERT INTO cliente(nombre_cliente, edad_cliente) VALUES(?,?)");
            int edad = Integer.parseInt(text_edad.getText());
            ps.setString(1, txt_nombrecliente.getText());
            ps.setInt(2, edad);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente registrado.");


        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/cine", "root", "");
            PreparedStatement ps2 = cn.prepareStatement("SELECT MAX(id_cliente) AS ultimo FROM cliente ORDER BY id_cliente DESC");
            ResultSet r1 = ps2.executeQuery();
            if (r1.next()) {
                id_ultimocli = r1.getInt("ultimo");
                System.out.println(id_ultimocli);
            }

            PreparedStatement ps3 = cn.prepareStatement("INSERT INTO venta(id_sala , id_cliente, promocion_venta) VALUES(?,?,?)");
            int num_sala = Integer.parseInt(text_numsala.getText());
            float importe = Float.parseFloat(text_importe.getText());
            ps3.setInt(1,num_sala);
            ps3.setInt(2, id_ultimocli);
            ps3.setFloat(3,importe);

            ps3.executeUpdate();

            JOptionPane.showMessageDialog(null,"Venta registrada.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }




    }



    public void ticket(MouseEvent mouseEvent) throws SQLException {

        int id_ultimovent=0;

        try{
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/cine", "root", "");
            PreparedStatement ps4 = cn.prepareStatement("SELECT MAX(id_venta) AS ultimo FROM venta ORDER BY id_venta DESC");
            ResultSet r1 = ps4.executeQuery();
            if (r1.next()) {
                id_ultimovent = r1.getInt("ultimo");
            }

            PreparedStatement ps5 = cn.prepareStatement("INSERT INTO ticket(id_peli,id_venta ,hora_inicio, hora_fin,cartelera) VALUES(?,?,?,?,?)");
            int id_peli = Integer.parseInt(txt_peli.getText());
            ps5.setInt(1,id_peli);
            ps5.setInt(2,id_ultimovent);
            ps5.setString(3, text_horainicio.getText());
            ps5.setString(4, text_horafin.getText());
            ps5.setString(5,text_cartelera.getText());

            ps5.executeUpdate();

            JOptionPane.showMessageDialog(null,"Ticket generado.");




        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

}