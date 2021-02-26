package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Director;
import model.Venta;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Observable;
import java.util.ResourceBundle;


public class Menu_controller implements Initializable {

    @FXML
    private TableView <Venta> Table_venta;
    @FXML
    TableColumn <Venta, String> colum_cliente;
    @FXML
    TableColumn <Venta, String> colum_pelicula;
    @FXML
    TableColumn <Venta, String> colum_sala;
    @FXML
    TableColumn <Venta, String> colum_importe;
    @FXML
    TableColumn <Venta, String> colum_hora;


    @FXML
    private Button irVenta;
    @FXML
    private BorderPane bp;
    private AnchorPane ap;
    @FXML
    private ImageView cartelera;
    @FXML
    private ImageView usericon;
    @FXML
    private Label email_user;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("resources/cartelera.jpg");
        cartelera.setImage(image);

        Image image2 = new Image("resources/user.png");
        usericon.setImage(image2);

        Venta v = new Venta();
        ObservableList<Venta> items = v.getDirectors();
        this.Table_venta.setItems(items);
        this.colum_cliente.setCellValueFactory(new PropertyValueFactory("nombre_cliente"));
        this.colum_pelicula.setCellValueFactory(new PropertyValueFactory("titulo_peli"));
        this.colum_sala.setCellValueFactory(new PropertyValueFactory("num_sala"));
        this.colum_importe.setCellValueFactory(new PropertyValueFactory("promocion_venta"));
        this.colum_hora.setCellValueFactory(new PropertyValueFactory("hora_inicio"));

    }





    public  void recibeParametros(String  user){
        email_user.setText(user);
    }


    public void closeWindows(){

    }


    @FXML
    public void home(MouseEvent mouseEvent) {




    }

    public void sale(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Ventas.fxml"));
        try {
            Parent root  = loader.load();
            Ventas controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void billboard(MouseEvent mouseEvent) {
    }
    @FXML
    public void movies(MouseEvent mouseEvent) {
    }
    @FXML
    public void settings(MouseEvent mouseEvent) {
    }





}
