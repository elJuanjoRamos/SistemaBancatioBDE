/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import controller.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.scene.control.ButtonType;

import beans.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Juan José Ramos
 */
public class UIAgenciaAutobanco {

    private static final UIAgenciaAutobanco UIAgenciaAutobanco = new UIAgenciaAutobanco();

    private UIAgenciaAutobanco() {
    }

    public static UIAgenciaAutobanco getAgencia() {
        return UIAgenciaAutobanco;
    }
    
    ImageView view = new ImageView();
    
    
    private HBox hBoxCRUD;
    private GridPane gridPane;
    private Text textTitle;
    private HBox hBoxBuscar;
    private TextField textFieldBuscar;
    private Button buttonBuscar;
    private HBox hBoxButtons;
    private Button buttonNuevo;
    private Button buttonModificar;
    private Button buttonEliminar;
    private Button buttonReestablecer;

    private TableColumn<AgenciaBancaria, Integer> tableColumnIdAgenciaBancariaBancaria;
    private TableColumn<AgenciaBancaria, String> tableColumnNombreAgenciaBancariaBancaria;
    private TableColumn<AgenciaBancaria, String> tableColumnDireccionAgenciaBancariaBancaria;
    private TableColumn<AgenciaBancaria, String> tableColumnTelefonoAgenciaBancariaBancaria;
    private TableColumn<AgenciaBancaria, Integer> tableColumnNoCajasAgenciaBancariaBancaria;
    private TableColumn<AgenciaBancaria, Integer> tableColumnNoEscritoriosAgenciaBancariaBancaria;
    private TableColumn<AgenciaBancaria, Integer> tableColumnAutoBancoAgenciaBancariaBancaria;
    private TableColumn<AgenciaBancaria, Double> tableColumnEfectivoAgenciaBancariaBancaria;

    private TableView<AgenciaBancaria> tableView;
    private ObservableList<AgenciaBancaria> observableList;

    public void restarthBoxCRUD() {
        hBoxCRUD.getChildren().clear();
        hBoxCRUD.getChildren().add(gridPane);
    }

    public HBox getViewAgencia() {
        hBoxCRUD = new HBox();
        hBoxButtons = new HBox(10);
        hBoxBuscar = new HBox(10);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30, 30, 30, 30));

        textTitle = new Text("  Agencia Bancaria con Auto Banco  ");
        textTitle.setFont(Font.font("Century Gothic Bold", FontWeight.BOLD, 35));
        gridPane.add(textTitle, 0, 2);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar AgenciaBancariaBancaria");

        buttonBuscar = new Button("Bucar");
        buttonBuscar.setId("btnVerdeV");
        buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textFieldBuscar.getText().length() != 0) {
                    actualizarTablabusqueda(textFieldBuscar.getText().trim());
                }
            }
        });

        
        buttonReestablecer = new Button("Reestablecer");
        buttonReestablecer.setId("btnVerdeV");
        buttonReestablecer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textFieldBuscar.clear();
                actualizarDatosTabla();
            }
        });
        hBoxBuscar.getChildren().addAll(textFieldBuscar, buttonBuscar, buttonReestablecer);
        gridPane.add(hBoxBuscar, 0, 6);

        buttonNuevo = new Button("Nuevo");
        buttonNuevo.setId("btnAzulA");
        buttonNuevo.setGraphic(new ImageView("/resources/1.png"));
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().addAll(gridPane, Animacion.getAnimatedGridPane().getAnimatedGridPane(CrearAgenciaBancaria.getCrearAgenciaBancaria().getGridPane()));
            }
        });

        buttonModificar = new Button("Modificar");
        buttonModificar.setId("btnNaranja");
        buttonModificar.setGraphic(new ImageView("/resources/3.png"));
        
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().clear();
                    
                    hBoxCRUD.getChildren().addAll(gridPane, Animacion.getAnimatedGridPane().getAnimatedGridPane(ActualizarAgenciaBancaria.getActualizarAgenciaBancaria()
                            .getGridPane(tableView.getSelectionModel().getSelectedItem())));
                    tableView.getSelectionModel().clearSelection();
                    actualizarDatosTabla();
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("No ha seleccionado ninguna AgenciaBancaria para modificar");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                }
            }
        });
        
        buttonEliminar = new Button("Eliminar");
        buttonEliminar.setId("btnRojoR");
        buttonEliminar.setGraphic(new ImageView("/resources/2.png"));
        
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.initStyle(StageStyle.DECORATED);
                    alert.setTitle("Cofirmacion");
                    alert.setHeaderText("Seguro que desea eliminar?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        for (AgenciaBancaria Agencia : tableView.getSelectionModel().getSelectedItems()) {
                            AgenciaController.getAgenciaController().borrar(Agencia.getId());
                            tableView.getSelectionModel().clearSelection();
                        }
                        restarthBoxCRUD();
                        actualizarDatosTabla();
                    }

                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Materialgo de Error");
                    alert.setHeaderText("No Se Selecciono un Elemento para Eliminar");
                    alert.showAndWait();
                }
            }
        });
        Button buttonReportes = new Button("Generar Reporte");
            buttonReportes.setGraphic(new ImageView("/resources/6.png"));
        
        buttonReportes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ReporteController.getReporteController().CrearPDFAgencias("Auto");
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonReportes);
        gridPane.add(hBoxButtons, 0, 9);

        tableColumnIdAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnIdAgenciaBancariaBancaria.setText("ID");
        tableColumnIdAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnIdAgenciaBancariaBancaria.setMinWidth(40);

        tableColumnNombreAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnNombreAgenciaBancariaBancaria.setText("Nombre");
        tableColumnNombreAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnNombreAgenciaBancariaBancaria.setMinWidth(300);

        tableColumnTelefonoAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnTelefonoAgenciaBancariaBancaria.setText("Telefono");
        tableColumnTelefonoAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tableColumnTelefonoAgenciaBancariaBancaria.setMinWidth(100);

        tableColumnDireccionAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnDireccionAgenciaBancariaBancaria.setText("Direccion");
        tableColumnDireccionAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableColumnDireccionAgenciaBancariaBancaria.setMinWidth(200);

        tableColumnNoCajasAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnNoCajasAgenciaBancariaBancaria.setText("No Cajas");
        tableColumnNoCajasAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("noCajas"));
        tableColumnNoCajasAgenciaBancariaBancaria.setMinWidth(100);

        tableColumnNoEscritoriosAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnNoEscritoriosAgenciaBancariaBancaria.setText("Esc. SC");
        tableColumnNoEscritoriosAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("escritorios"));
        tableColumnNoEscritoriosAgenciaBancariaBancaria.setMinWidth(100);

        
        tableColumnAutoBancoAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnAutoBancoAgenciaBancariaBancaria.setText("Cajas AB");
        tableColumnAutoBancoAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("auto"));
        tableColumnAutoBancoAgenciaBancariaBancaria.setMinWidth(100);

        
        tableColumnEfectivoAgenciaBancariaBancaria = new TableColumn<>();
        tableColumnEfectivoAgenciaBancariaBancaria.setText("Efectivo Q.00");
        tableColumnEfectivoAgenciaBancariaBancaria.setCellValueFactory(new PropertyValueFactory<>("efectivo"));
        tableColumnEfectivoAgenciaBancariaBancaria.setMinWidth(150);

        tableView = new TableView<>();
        tableView.setItems(getObservableList());

        tableView.getColumns().addAll(tableColumnIdAgenciaBancariaBancaria, tableColumnNombreAgenciaBancariaBancaria,
                tableColumnTelefonoAgenciaBancariaBancaria, tableColumnDireccionAgenciaBancariaBancaria,
                tableColumnNoCajasAgenciaBancariaBancaria, tableColumnNoEscritoriosAgenciaBancariaBancaria,
                tableColumnAutoBancoAgenciaBancariaBancaria, tableColumnEfectivoAgenciaBancariaBancaria);
        
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        

        gridPane.add(tableView, 0, 11);
        gridPane.setMaxWidth(1152);
        hBoxCRUD.getChildren().add(gridPane);
        hBoxCRUD.setAlignment(Pos.CENTER_LEFT);
        
        return hBoxCRUD;
    }

    private ObservableList<AgenciaBancaria> getObservableList() {
        observableList = FXCollections.observableArrayList(AgenciaController.getAgenciaController().getArray2());
        return observableList;
    }

    public void actualizarDatosTabla() {
        actualizarDatos();
        tableView.setItems(observableList);
    }

    public void actualizarDatos() {
        observableList.clear();
        observableList = FXCollections.observableArrayList(AgenciaController.getAgenciaController().getArray2());

    }

    public void actualizarTablabusqueda(String nombre) {
        if (!AgenciaController.getAgenciaController().buscar(nombre).isEmpty()) {
            observableList = FXCollections.observableArrayList(AgenciaController.getAgenciaController().buscar(nombre));
            tableView.setItems(observableList);
        } else {
            actualizarDatosTabla();
        }
    }
}

class CrearAgenciaBancaria {

    private static final CrearAgenciaBancaria CrearAgenciaBancaria = new CrearAgenciaBancaria();
    private GridPane gridPane;
    private Text textTitle;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Label labelTelefono;
    private TextField textFieldTelefono;

    private Label labelcajas;
    private TextField textFieldcajas;

    private Label labelescritorio;
    private TextField textFieldescritorio;

    
    private Label labelCajasAuto;
    private TextField textFieldCajasAuto;

    
    private Label labelefectivo;
    private TextField textFieldefectivo;

    private Button buttonAgregar;
    private Button buttonCerrar;

    private CrearAgenciaBancaria() {
    }

    public static CrearAgenciaBancaria getCrearAgenciaBancaria() {
        return CrearAgenciaBancaria;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        textTitle = new Text("Agregar");
        textTitle.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        gridPane.add(textTitle, 0, 0, 3, 1);

        labelNombre = new Label("Nombre: ");
        gridPane.add(labelNombre, 0, 3);

        textFieldNombre = new TextField();
        gridPane.add(textFieldNombre, 1, 3, 2, 1);

        labelDireccion = new Label("Direccion: ");
        gridPane.add(labelDireccion, 0, 4);

        textFieldDireccion = new TextField();
        gridPane.add(textFieldDireccion, 1, 4, 2, 1);

        labelTelefono = new Label("Telefono: ");
        gridPane.add(labelTelefono, 0, 5);

        textFieldTelefono = new TextField();
        gridPane.add(textFieldTelefono, 1, 5, 2, 1);

        labelcajas = new Label("No. Cajas: ");
        gridPane.add(labelcajas, 0, 6);

        textFieldcajas = new TextField();
        gridPane.add(textFieldcajas, 1, 6, 2, 1);

        labelescritorio = new Label("Esc. SC: ");
        gridPane.add(labelescritorio, 0, 7);

        textFieldescritorio = new TextField();
        gridPane.add(textFieldescritorio, 1, 7, 2, 1);

        labelCajasAuto = new Label("No. Cajas Autobanco: ");
        gridPane.add(labelCajasAuto, 0, 8);

        textFieldCajasAuto = new TextField();
        gridPane.add(textFieldCajasAuto, 1, 8, 2, 1);

        labelefectivo = new Label("Efectivo neto: ");
        gridPane.add(labelefectivo, 0, 9);

        textFieldefectivo = new TextField();
        gridPane.add(textFieldefectivo, 1, 9, 2, 1);

        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UIAgenciaAutobanco.getAgencia().restarthBoxCRUD();
            }
        });

        buttonAgregar = new Button("Agregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (textFieldNombre.getText().length() != 0 && textFieldDireccion.getText().length() != 0 && textFieldTelefono.getText().length() != 0
                        && textFieldcajas.getText().length() != 0 && textFieldescritorio.getText().length() != 0 && textFieldefectivo.getText().length() != 0) {

                    if (AgenciaController.getAgenciaController().esNumero(textFieldcajas.getText(), textFieldescritorio.getText(), textFieldefectivo.getText(), 
                            textFieldCajasAuto.getText())) {

                        if (AgenciaController.getAgenciaController().buscarAgencia(textFieldNombre.getText())) {
                            getAlert("Ya existe una agencia o agencia con autobanco asociada con ese nombre");
                        } else {
                            if (Double.parseDouble(textFieldefectivo.getText()) >= 10000.00) {
                                if (Integer.parseInt(textFieldcajas.getText()) > 0) {
                                    AgenciaController.getAgenciaController().agregar(textFieldNombre.getText(), textFieldDireccion.getText(), textFieldTelefono.getText(),
                                    Integer.parseInt(textFieldcajas.getText()), Integer.parseInt(textFieldescritorio.getText()), 
                                    Integer.parseInt(textFieldCajasAuto.getText()),
                                    Double.parseDouble(textFieldefectivo.getText()));
                                    textFieldNombre.clear();
                                    textFieldDireccion.clear();
                                    textFieldTelefono.clear();
                                    textFieldcajas.clear();
                                    textFieldescritorio.clear();
                                    textFieldCajasAuto.clear();
                                    textFieldefectivo.clear();
                                    UIAgenciaAutobanco.getAgencia().actualizarDatosTabla();

                                }
                                
                            } else {
                                getAlert("El monto del efectivo debe ser mayor o igual a Q10,000.00");
                            }
                        }

                    } else {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Error!");
                        alerta.setHeaderText(null);
                        alerta.setContentText("No puede introducir letras donde van valores numericos");
                        alerta.initStyle(StageStyle.UTILITY);
                        alerta.showAndWait();
                    }
                } else {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Error!");
                    alerta.setHeaderText(null);
                    alerta.setContentText("No puede dejar campos en blanco.");
                    alerta.initStyle(StageStyle.UTILITY);
                    alerta.showAndWait();

                }

            }
        });

        gridPane.add(buttonAgregar, 1, 10);
        gridPane.add(buttonCerrar, 2, 10);
        gridPane.setMinSize(200, 400);
        gridPane.getStyleClass().add("gridPane");

        return gridPane;
    }
    
    public void getAlert(String cadena) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Information!");
        alerta.setHeaderText(null);
        alerta.setContentText(cadena);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
    }
}

class ActualizarAgenciaBancaria {

    private static final ActualizarAgenciaBancaria ActualizarAgenciaBancaria = new ActualizarAgenciaBancaria();
    private GridPane gridPane;
    private Text textTitle;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Label labelTelefono;
    private TextField textFieldTelefono;
    private Button buttonModificar;

    private Label labelcajas;
    private TextField textFieldcajas;

    private Label labelescritorio;
    private TextField textFieldescritorio;

    private Label labelefectivo;
    private TextField textFieldefectivo;

    private Button buttonAgregar;
    private Button buttonCerrar;

    private Alert alert = new Alert(AlertType.INFORMATION);

    private ActualizarAgenciaBancaria() {
    }

    public static synchronized ActualizarAgenciaBancaria getActualizarAgenciaBancaria() {
        return ActualizarAgenciaBancaria;
    }

    public GridPane getGridPane(AgenciaBancaria agenciaBancaria) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        textTitle = new Text("Actualizar Datos");
        textTitle.setFont(Font.font("Montserrat", FontWeight.BOLD, 20));
        gridPane.add(textTitle, 0, 0, 3, 1);

        labelNombre = new Label("Nombre: ");
        gridPane.add(labelNombre, 0, 3);

        textFieldNombre = new TextField(agenciaBancaria.getNombre());
        gridPane.add(textFieldNombre, 1, 3, 3, 1);

        labelDireccion = new Label("Direccion: ");
        gridPane.add(labelDireccion, 0, 4);

        textFieldDireccion = new TextField(agenciaBancaria.getDireccion());
        gridPane.add(textFieldDireccion, 1, 4, 3, 1);

        labelTelefono = new Label("Telefono: ");
        gridPane.add(labelTelefono, 0, 5);

        textFieldTelefono = new TextField(agenciaBancaria.getTelefono());
        gridPane.add(textFieldTelefono, 1, 5, 3, 1);

        
        labelcajas = new Label("No. Cajas: ");
        gridPane.add(labelcajas, 0, 6);

        textFieldcajas = new TextField(String.valueOf(agenciaBancaria.getNoCajas()));
        gridPane.add(textFieldcajas, 1, 6, 3, 1);

        labelescritorio = new Label("Esc. SC: ");
        gridPane.add(labelescritorio, 0, 7);

        textFieldescritorio = new TextField(String.valueOf(agenciaBancaria.getEscritorios()));
        gridPane.add(textFieldescritorio, 1, 7, 3, 1);

        Label labelCajasAuto = new Label("No. Cajas AB: ");
        gridPane.add(labelCajasAuto, 0, 8);

        TextField textFieldCajasAuto = new TextField(String.valueOf(agenciaBancaria.getAuto()));
        gridPane.add(textFieldCajasAuto, 1, 8, 2, 1);

        
        
        labelefectivo = new Label("Efectivo neto: ");
        gridPane.add(labelefectivo, 0, 9);

        textFieldefectivo = new TextField(String.valueOf(agenciaBancaria.getEfectivo()));
        gridPane.add(textFieldefectivo, 1, 9, 3, 1);

        
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setDefaultButton(true);
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if (textFieldNombre.getText().length() != 0 && textFieldDireccion.getText().length() != 0 && textFieldTelefono.getText().length() != 0
                        && textFieldcajas.getText().length() != 0 && textFieldescritorio.getText().length() != 0 && textFieldefectivo.getText().length() != 0) {

                    if (AgenciaController.getAgenciaController().esNumero(textFieldcajas.getText(), textFieldescritorio.getText(), textFieldCajasAuto.getText(),  textFieldefectivo.getText())) {

                        if (AgenciaController.getAgenciaController().buscarAgencia(textFieldNombre.getText())) {
                            getAlert("Ya existe una agencia o agencia con autobanco asociada con ese nombre");
                        } else {

                            if (Double.parseDouble(textFieldefectivo.getText()) >= 10000.00 )  {
                                if (Integer.parseInt(textFieldcajas.getText()) > 0) {
                                    AgenciaController.getAgenciaController().actualizar(agenciaBancaria.getId(), textFieldNombre.getText(), textFieldDireccion.getText(), textFieldTelefono.getText(),
                                    Integer.parseInt(textFieldcajas.getText()), Integer.parseInt(textFieldescritorio.getText()), Integer.parseInt(textFieldCajasAuto.getText()), Double.parseDouble(textFieldefectivo.getText()));
                            
                                    UIAgenciaAutobanco.getAgencia().actualizarDatosTabla();
                                    UIAgenciaAutobanco.getAgencia().restarthBoxCRUD();
                                }
                            } else {
                                getAlert("El monto del efectivo debe ser mayor o igual a Q10,000.00");
                            }
                           
                        }

                    } else {
                        getAlert("No puede introducir letras donde van valores numericos");
                    }
                } else {
                    getAlert("No puede dejar campos en blanco.");
                }

            }

        });

        gridPane.add(buttonModificar, 1, 10);

        Button buttonCerrar = new Button("Cerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UIAgenciaAutobanco.getAgencia().restarthBoxCRUD();
            }
        });
         Button buttonReporte = new Button("Reporte de Empleados");
        buttonReporte.setGraphic(new ImageView("/resources/6.png"));
        
        buttonReporte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ReporteController.getReporteController().CrearPDFEmpleadoAgencia(agenciaBancaria.getId());
            }
        });
        
        
        gridPane.add(buttonReporte, 1, 12, 3, 1);
        gridPane.add(buttonCerrar, 2, 10);
        gridPane.getStyleClass().add("gridPane");
        gridPane.setMinSize(200, 400);

        return gridPane;
    }
    public void getAlert(String cadena) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Information!");
        alerta.setHeaderText(null);
        alerta.setContentText(cadena);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
    }
}
