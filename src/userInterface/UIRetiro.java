/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import beans.*;
import controller.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro8.JMetro;

/**
 *
 * @author Juan José Ramos
 */
public class UIRetiro {
    /*SINGLETON*/
    private static final JMetro.Style style = JMetro.Style.LIGHT;
    private static final UIRetiro UIRetiroAgencia = new UIRetiro();

    private UIRetiro() {
    }

    public static UIRetiro getUI() {
        return UIRetiroAgencia;
    }
    
    public void start(Stage primaryStage, Cliente cliente) {

        primaryStage.setTitle("MODULO ADMINISTRATIVO");
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: white");
        Scene scene = new Scene(vbox, 700, 700, Color.WHITE);

        
        
        HBox hBoxVista = new HBox();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 30, 30, 30));

        Text textTitle = new Text("  RETIRO DE AGENCIA ");
        textTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        gridPane.add(textTitle, 0, 0);
        
        
        Label labelAgencia = new Label("Agencia bancaria: ");
        labelAgencia.setId("labelTexto");
        gridPane.add(labelAgencia, 1, 0);

        ComboBox comboAgencia = new ComboBox();
          /* BUSCA LAS AGENCIAS Y LOS METE EN UN COMBOBOX*/
        ObservableList agencias = FXCollections.observableArrayList(AgenciaController.getAgenciaController().getNombreAgencia());

        comboAgencia.getItems().addAll(agencias);
        comboAgencia.setEditable(true);
        gridPane.add(comboAgencia, 2, 0);

        
        
        Label labelCombo = new Label("Cuenta de retiro: ");
        gridPane.add(labelCombo, 1, 1);

        ComboBox comboCuenta = new ComboBox();
        comboCuenta.setEditable(true);
        /* BUSCA LAS CUENTAS  DEL CLIENTE Y LAS METE EN UN COMBOBOX*/
        ObservableList cuentas = FXCollections.observableArrayList(CuentasCliente.getCuentasCliente().getArrayCuentasCliete(cliente.getId()));

        comboCuenta.getItems().addAll(cuentas);

        gridPane.add(comboCuenta, 2, 1);

        
            

        
        
        Label labelMonto = new Label("Monto: ");
        gridPane.add(labelMonto, 1, 2);

        ComboBox comboMonto = new ComboBox();
        comboMonto.getItems().addAll("100", "200","500","1000","5000");
        comboMonto.setPromptText("Cantidad de retiro");
        comboMonto.setEditable(true);
        gridPane.add(comboMonto, 2, 2);

        
        
     

    
        Button buttonAceptar = new Button("Aceptar");
        
        buttonAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (comboAgencia.getSelectionModel().getSelectedItem() != null && comboMonto.getSelectionModel().getSelectedItem() != null &&
                        comboCuenta.getSelectionModel().getSelectedItem() != null) {
                
                    if (CuentasCliente.getCuentasCliente().buscarCuentaAhorros(Integer.parseInt(comboCuenta.getSelectionModel().getSelectedItem().toString())) != null) {
                        
                        CuentaAhorro cuenta=  CuentasCliente.getCuentasCliente().buscarCuentaAhorros(Integer.parseInt(comboCuenta.getSelectionModel().getSelectedItem().toString()));
                        if (cuenta.getMontoInicial() >= Double.parseDouble(comboMonto.getSelectionModel().getSelectedItem().toString())) {
                            
                            
                            RetiroController.getRetiroController().agregar(comboCuenta.getSelectionModel().getSelectedItem().toString(), 
                            Double.parseDouble(comboMonto.getSelectionModel().getSelectedItem().toString()), cliente.getId(), 
                            comboAgencia.getSelectionModel().getSelectedItem().toString());
                            
                                                getAlert("Retiro realizado con exito");

                        } else {
                            getAlert("El saldo de su cuenta no es suficiente");
                        }
                        
                        
                    } else if (CuentasCliente.getCuentasCliente().buscarCuentaMonetaria(Integer.parseInt(comboCuenta.getSelectionModel().getSelectedItem().toString()))!= null) {
                        CuentaMonetaria cuenta =  CuentasCliente.getCuentasCliente().buscarCuentaMonetaria(Integer.parseInt(comboCuenta.getSelectionModel().getSelectedItem().toString()));
                        if (cuenta.getMontoInicial() >= Double.parseDouble(comboMonto.getSelectionModel().getSelectedItem().toString())) {
                            RetiroController.getRetiroController().agregar(comboCuenta.getSelectionModel().getSelectedItem().toString(), 
                            Double.parseDouble(comboMonto.getSelectionModel().getSelectedItem().toString()), cliente.getId(), 
                            comboAgencia.getSelectionModel().getSelectedItem().toString());
                                                getAlert("Retiro realizado con exito");

                        } else {
                            getAlert("El saldo de su cuenta no es suficiente");
                        }
                    }
                    
                } else {
                    UIDeposito.getUI().getAlert("No puede dejar en blanco");
                }

            }
        });

        Button buttonCancelar = new Button("Cancelar");

        
        buttonCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UIOperacionesCliente.getUI().start(primaryStage, cliente);

            }
        });
        
        gridPane.add(buttonAceptar, 1, 7);

        hBoxVista.getChildren().add(gridPane);
        hBoxVista.setAlignment(Pos.CENTER_LEFT);
        hBoxVista.setMinSize(5000, 5000);

        
        
        vbox.getChildren().addAll(getMenuBar(primaryStage, cliente), hBoxVista);
        scene.getStylesheets().addAll("/resources/root.css");
        //scene.getStylesheets().addAll("/resources/jmetro.css");
        new JMetro(style).applyTheme(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    
    
    private MenuBar getMenuBar(Stage primaryStage, Cliente cliente) {
        MenuBar menuBar = new MenuBar();

        javafx.scene.control.Menu menuArchivo = new javafx.scene.control.Menu("Opciones");

        MenuItem menuItemRegresar = new MenuItem("_Regresar");
        MenuItem menuItemSalir = new MenuItem("_Salir");

        menuItemSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        menuItemRegresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UIOperacionesCliente.getUI().start(primaryStage, cliente);
            }
        });

        menuArchivo.getItems().addAll(menuItemRegresar, menuItemSalir);

        menuBar.getMenus().addAll(menuArchivo);
        return menuBar;
    }
    
    
    public void getAlert(String cadena) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Error!");
        alerta.setHeaderText(null);
        alerta.setContentText(cadena);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
    }
}



