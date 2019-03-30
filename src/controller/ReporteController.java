/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import beans.*;
import java.util.Arrays;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.HorizontalAlignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.StringTokenizer;
import controller.*;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.StringTokenizer;
import javafx.event.EventType;

/**
 *
 * @author Juan José Ramos
 */
public class ReporteController {

    /*SINGLETON*/
    private static ReporteController instance;

    public static ReporteController getReporteController() {
        if (instance == null) {
            instance = new ReporteController();
        }
        return instance;
    }

    /*RUTAS*/
    public String ruta = String.valueOf(System.getProperty("user.dir")) + "\\pdf\\";

    public void CrearPDFClientes() {
        String contenido = "ID" + "'" + "Nombre" + "'" + "Direccion" + "'" + "Telefono" + "\n";
        try {
            Cliente[] clientes = ClienteController.getClienteController().getArregloCliente();

            for (Cliente c : clientes) {
                if (c != null) {
                    int id = c.getId();
                    String nombre = c.getNombre();
                    String direccion = c.getDireccion();
                    String telefono = c.getTelefono();

                    contenido = contenido + id + "'" + nombre + "'" + direccion + "'" + telefono + "\n";;
                }
            }
        } catch (Exception a) {
        }
        String nombrePdf = "pdfClientes.pdf";
        String title = "Clientes Registrados";
        float[] size = {2, 10, 6, 6};
        CrearTH(nombrePdf, contenido, title, size);

    }

    public void CrearPDFEmpleado() {
        String contenido = "ID" + "'" + "Nombre" + "'" + "Direccion" + "'" + "Telefono" + "'" + " Departamento" + "'" + " Agencia" + "\n";
        try {
            Empleado[] empleado = EmpleadoController.getEmpleadoController().obtenerEmpleados();

            for (Empleado c : empleado) {
                if (c != null) {
                    int id = c.getId();
                    String nombre = c.getNombre();
                    String direccion = c.getDireccion();
                    String telefono = c.getTelefono();
                    String departamento = c.getDepartamento();
                    String agencia = c.getAgencia();
                    contenido = contenido + id + "'" + nombre + "'" + direccion + "'" + telefono + "'" + departamento + "'" + agencia + "\n";;

                }
            }
        } catch (Exception a) {
        }
        String nombrePdf = "pdfEmpleados.pdf";
        String title = "Empleados Registrados";
        float[] size = {2, 10, 6, 6, 6, 6};
        CrearTH(nombrePdf, contenido, title, size);

    }

    public void CrearPDFAgencias(String cadena) {
        String contenido = "ID" + "'" + "Nombre" + "'" + "Direccion" + "'" + "Telefono" + "'" + "Numero Cajas" + "'" + "Escritorios SC" + "'" + "Efectivo" + "\n";
        double total = 0;
        AgenciaBancaria[] agencias = AgenciaController.getAgenciaController().obtenerAgencias();
        String nombrePdf = "";
        String title = "";
        float[] size = {};

        try {
            for (AgenciaBancaria c : agencias) {
                if (c != null) {
                    if (cadena.equals("NoAuto")) {
                        title = "Agencias Registradas";

                        if (c.getAuto() == 0) {

                            int id = c.getId();
                            String nombre = c.getNombre();
                            String direccion = c.getDireccion();
                            String telefono = c.getTelefono();
                            int noCajas = c.getNoCajas();
                            int escritorios = c.getEscritorios();
                            double efectivo = c.getEfectivo();

                            total = total + efectivo;
                            contenido = contenido + id + "'" + nombre + "'" + direccion + "'" + telefono
                                    + "'" + noCajas + "'" + escritorios + "'" + efectivo + "\n";

                            size = new float[]{2, 10, 6, 6, 3, 3, 3};
                            nombrePdf = "pdfAgenciasSinAutoBanco.pdf";
                        }
                    } else if (cadena.equals("Auto")) {
                        title = "Agencias con Autobanco Registradas";

                        if (c.getAuto() != 0) {

                            int id = c.getId();
                            String nombre = c.getNombre();
                            String direccion = c.getDireccion();
                            String telefono = c.getTelefono();
                            int noCajas = c.getNoCajas();
                            int escritorios = c.getEscritorios();
                            double efectivo = c.getEfectivo();

                            total = total + efectivo;
                            contenido = contenido + id + "'" + nombre + "'" + direccion + "'" + telefono
                                    + "'" + noCajas + "'" + escritorios + "'" + efectivo + "\n";

                            size = new float[]{2, 10, 6, 6, 3, 3, 3};
                            nombrePdf = "pdfAgenciasConAutoBanco.pdf";
                        }
                    }
                }
            }
        } catch (Exception a) {
        }
        contenido = contenido + "-'-'-'-'-'TOTAL EFECTIVO'" + total + "'";
        CrearTH(nombrePdf, contenido, title, size);

    }

    public void CrearPDFCajeros() {
        String contenido = "ID" + "'" + "Ubicacion" + "'" + "Efectivo" + "'" + "Estado" + "'" + "Transacciones Procesadas" + "\n";

        try {
            Cajero[] cajero = CajeroController.getCajeroControler().obtenerCajeros();

            for (Cajero c : cajero) {
                if (c != null) {
                    int id = c.getId();
                    String ubicacion = c.getUbicacion();
                    Double efectivo = c.getEfectivo();
                    String estado = c.getEstado();
                    int transacciones = c.getTransaccion();

                    contenido = contenido + id + "'" + ubicacion + "'" + efectivo + "'" + estado + "'" + transacciones + "\n";

                }
            }

        } catch (Exception a) {
        }
        String nombrePdf = "pdfCajero.pdf";
        String title = "Cajeros Registrados";
        float[] size = {2, 10, 6, 6, 6};
        CrearTH(nombrePdf, contenido, title, size);

    }

    public void CrearPDFCliente(Cliente cliente) {
        System.out.println(cliente.getNombre());
        String contenido = "ID" + "'" + "Nombre" + "'" + "Direccion" + "'" + "Telefono" + "'" + "-" + "\n";
        String contenidoTarjeta = "Id Tarjeta" + "'" + "Fecha" + "'" + "Credito" + "'" + "Ultimo abono" + "'" + "-" + "\n";
        String contenidoPrestamo = "Id Prestamo" + "'" + "Fecha" + "'" + "Estado" + "'" + "Ultimo abono" + "'" + "Deuda" + "\n";;
        String contenidoCuentaAhorro = "Id Cuenta Ahorro" + "'" + "Fecha" + "'" + "Credito" + "'" + "-" + "'" + "-" + "\n";
        String contenidoCuentaMonetaria = "Id Cuenta Monetaria" + "'" + "Fecha" + "'" + "Credito" + "'" + "-" + "'" + "-" + "\n";

        PrestamoCliente[] p = TarjetasYPrestamosCliente.getInstancia().obtenerPrestamos(cliente.getId());
        TarjetaCliente[] t = TarjetasYPrestamosCliente.getInstancia().obtenerTarjetaCliente(cliente.getId());
        CuentaAhorroCliente[] ca = CuentasCliente.getCuentasCliente().obtenerCuentaAhorro(cliente.getId());
        CuentaMonetariaCliente[] cm = CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria(cliente.getId());

        try {
            Cliente c = ClienteController.getClienteController().buscar(cliente.getId());
            contenido = contenido + c.getId() + "'" + c.getNombre() + "'" + c.getDireccion() + "'" + c.getTelefono() + "'" + "-" + "\n";

            if (c != null) {
                if (p != null) {
                    for (PrestamoCliente pc : p) {
                        if (pc != null) {
                            contenidoPrestamo = contenidoPrestamo + "'" + pc.getCodigo() + "'" + pc.getFechaPrestamo() + "'" + pc.getEstado() + "'" + pc.getAbono() + "'" + pc.getDeuda() + "'" + "\n";
                        }

                    }
                } else {
                    contenidoPrestamo = contenidoPrestamo + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "\n";
                }

                if (t != null) {
                    for (TarjetaCliente tc : t) {
                        if (tc != null) {
                            contenidoTarjeta = contenidoTarjeta + tc.getNumero() + "'" + tc.getFechaVencimiento() + "'" + tc.getCredito() + "'" + tc.getDeuda() + "'" + "-" + "\n";
                        }
                    }

                } else {
                    contenidoTarjeta = contenidoTarjeta + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "\n";
                }

                if (cm != null) {
                    for (CuentaMonetariaCliente cm1 : cm) {
                        if (cm1 != null) {
                            contenidoCuentaMonetaria = contenidoCuentaMonetaria + cm1.getIdCuenta() + "'" + cm1.getFechaApertura() + "'" + cm1.getMontoInicial() + "'" + "-" + "'" + "-" + "\n";

                        }
                    }
                } else {
                    contenidoCuentaMonetaria = contenidoCuentaMonetaria + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "\n";

                }

                if (ca != null) {
                    for (CuentaAhorroCliente cm1 : ca) {
                        if (cm1 != null) {
                            contenidoCuentaAhorro = contenidoCuentaAhorro + cm1.getIdCuenta() + "'" + cm1.getFechaApertura() + "'" + cm1.getMontoInicial() + "'" + "-" + "'" + "-" + "\n";

                        }
                    }
                } else {
                    contenidoCuentaAhorro = contenidoCuentaAhorro + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "-" + "'" + "\n";

                }
            }

        } catch (Exception a) {
        }
        //
        String todo = contenido + "\n" + contenidoPrestamo + "\n" + contenidoTarjeta + "\n" + contenidoCuentaAhorro + "\n" + contenidoCuentaMonetaria;
        String nombrePdf = "pdfClienteUnico.pdf";
        String title = "Detalles del cliente";
        float[] size = {2, 10, 6, 6, 6};
        CrearTH(nombrePdf, todo, title, size);

    }

    public void CrearPdfTopClienteCuenta() {
        String contenido = "Nombre" + "'" + "Cuenas Monetarias" + "'" + "Cuentas Ahorro" + "'" + "Total Cuentas" + "\n";

        try {

            int[] result = new int[100];
            String[] texto = new String[100];
            Cliente[] a = ClienteController.getClienteController().getArregloCliente();
            int cuenta1 = 0;
            int cuenta2 = 0;
            int total = 0;
            for (int t = 0; t < a.length; t++) {
                if (a[t] != null) {
                    for (int i = 0; i < CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria().length; i++) {

                        if (CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria()[i] != null) {
                            if (String.valueOf(CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria()[i].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {

                                cuenta1 = cuenta1 + CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria()[i].getContador();
                            }
                        }
                    }

                    for (int r = 0; r < CuentasCliente.getCuentasCliente().obtenerCuentaAhorro().length; r++) {
                        if (CuentasCliente.getCuentasCliente().obtenerCuentaAhorro()[r] != null) {
                            if (String.valueOf(CuentasCliente.getCuentasCliente().obtenerCuentaAhorro()[r].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {
                                cuenta2 = cuenta2 + CuentasCliente.getCuentasCliente().obtenerCuentaAhorro()[r].getContador();

                            }
                        }
                    }
                    total = cuenta1 + cuenta2;
                    result[t] = total;
                    texto[t] = a[t].getNombre() + "'" + cuenta1 + "'" + +cuenta2 + "'" + total + "'" + "\n";

                    cuenta1 = 0;
                    cuenta2 = 0;

                }

            }

            for (int i = 0; i < result.length - 1; i++) {
                for (int j = 0; j < result.length - 1; j++) {
                    if (result[j] < result[j + 1]) {
                        int tmp = result[j + 1];
                        result[j + 1] = result[j];
                        result[j] = tmp;
                    }
                }
            }

            String uno = "";
            String dos = "";
            String tres = "";

            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[0]))) {
                        uno = texto[i];
                    }
                }
            }
            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[1]))) {
                        dos = texto[i];
                    }
                }
            }
            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[2]))) {
                        tres = texto[i];
                    }
                }
            }

            String[] abc = new String[3];

            abc[0] = uno;
            abc[1] = dos;
            abc[2] = tres;

            for (int i = 0; i < abc.length; i++) {
                contenido = contenido + abc[i];
            }

        } catch (Exception a) {
        }
        String nombrePdf = "pdfTopClienteCuentas.pdf";
        String title = "Top 3 clientes con más cuentas";
        float[] size = {10, 6, 6, 6};
        CrearTH(nombrePdf, contenido, title, size);
    }

    public void CrearPDFClienteMayorDinero() {

        String contenido = "ID" + "'" + "Nombre" + "'" + " Cuentas Monetaria " + "'" + " Cuentas Ahorro" + "'" + " Tarjetas Credito" + "'" + "Pestamos" + "'" + "TOTAL" + "\n";

        try {

            int[] result = new int[100];
            String[] texto = new String[100];
            Cliente[] a = ClienteController.getClienteController().getArregloCliente();
            Double dinero1 = 0.0;
            Double dinero2 = 0.0;
            Double dinero3 = 0.0;
            Double dinero4 = 0.0;

            for (int t = 0; t < a.length; t++) {
                if (a[t] != null) {
                    for (int i = 0; i < CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria().length; i++) {
                        if (CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria()[i] != null) {
                            if (String.valueOf(CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria()[i].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {
                                dinero1 = dinero1 + CuentasCliente.getCuentasCliente().obtenerCuentaMonetaria()[i].getMontoInicial();

                            }
                        }
                    }

                    for (int r = 0; r < CuentasCliente.getCuentasCliente().obtenerCuentaAhorro().length; r++) {
                        if (CuentasCliente.getCuentasCliente().obtenerCuentaAhorro()[r] != null) {
                            if (String.valueOf(CuentasCliente.getCuentasCliente().obtenerCuentaAhorro()[r].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {
                                dinero2 = dinero2 + CuentasCliente.getCuentasCliente().obtenerCuentaAhorro()[r].getMontoInicial();
                            }
                        }
                    }

                    for (int s = 0; s < TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente().length; s++) {

                        if (TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente()[s] != null) {
                            if (String.valueOf(TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente()[s].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {
                                dinero3 = dinero3 + TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente()[s].getCredito();

                            }

                        }
                    }

                    for (int i = 0; i < TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente().length; i++) {
                        if (TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente()[i] != null) {
                            if (String.valueOf(TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente()[i].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {
                                dinero4 = dinero4 + TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente()[i].getDeuda();
                            }
                        }
                    }

                    double d = Double.parseDouble(String.valueOf(dinero1 + dinero2 + dinero3 + dinero4));
                    int i = (int) d;
                    result[t] = i;
                    texto[t] = a[t].getId() + "'" + a[t].getNombre() + "'" + dinero1 + "'" + dinero2 + "'" + dinero3 + "'" + dinero4 + "'" + result[t] + "\n";

                    dinero1 = 0.0;
                    dinero2 = 0.0;
                    dinero3 = 0.0;
                    dinero4 = 0.0;

                }

            }
            for (int i = 0; i < result.length - 1; i++) {
                for (int j = 0; j < result.length - 1; j++) {
                    if (result[j] < result[j + 1]) {
                        int tmp = result[j + 1];
                        result[j + 1] = result[j];
                        result[j] = tmp;
                    }
                }
            }

            String uno = "";
            String dos = "";
            String tres = "";

            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[0]))) {
                        uno = texto[i];
                    }
                }
            }
            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[1]))) {
                        dos = texto[i];
                    }
                }
            }
            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[2]))) {
                        tres = texto[i];
                    }
                }
            }

            String[] abc = new String[3];

            abc[0] = uno;
            abc[1] = dos;
            abc[2] = tres;

            for (int i = 0; i < abc.length; i++) {
                contenido = contenido + abc[i];

                System.out.println(contenido);
            }

        } catch (Exception a) {
        }

        String nombrePdf = "pdfTopClienteMayorDinero.pdf";
        String title = "Top 3 clientes con mayor dinero";
        float[] size = {2, 10, 6, 6, 6, 6, 6};
        CrearTH(nombrePdf, contenido, title, size);
    }

    public void CrearPDFMayorDeuda() {

        String contenido = "ID" + "'" + "Nombre" + "'" + " Deuda Tarjetas Credito" + "'" + "Deuda Pestamos" + "'" + "TOTAL" + "\n";

        try {

            int[] result = new int[100];
            String[] texto = new String[100];
            Cliente[] a = ClienteController.getClienteController().getArregloCliente();
            Double dinero3 = 0.0;
            Double dinero4 = 0.0;

            for (int t = 0; t < a.length; t++) {
                if (a[t] != null) {
                    for (int s = 0; s < TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente().length; s++) {

                        if (TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente()[s] != null) {
                            if (String.valueOf(TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente()[s].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {
                                dinero3 = dinero3 + TarjetasYPrestamosCliente.getInstancia().obtenerTarjetasCliente()[s].getCredito();

                            }

                        }
                    }

                    for (int i = 0; i < TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente().length; i++) {
                        if (TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente()[i] != null) {
                            if (String.valueOf(TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente()[i].getCliente().getId()).equals(String.valueOf(a[t].getId()))) {
                                dinero4 = dinero4 + TarjetasYPrestamosCliente.getInstancia().obtenerPrestamoClente()[i].getDeuda();
                            }
                        }
                    }

                    double d = Double.parseDouble(String.valueOf(dinero3 + dinero4));
                    int i = (int) d;
                    result[t] = i;
                    texto[t] = a[t].getId() + "'" + a[t].getNombre() + "'" + dinero3 + "'" + dinero4 + "'" + result[t] + "\n";

                    dinero3 = 0.0;
                    dinero4 = 0.0;

                }

            }
            for (int i = 0; i < result.length - 1; i++) {
                for (int j = 0; j < result.length - 1; j++) {
                    if (result[j] < result[j + 1]) {
                        int tmp = result[j + 1];
                        result[j + 1] = result[j];
                        result[j] = tmp;
                    }
                }
            }

            String uno = "";
            String dos = "";
            String tres = "";

            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[0]))) {
                        uno = texto[i];
                    }
                }
            }
            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[1]))) {
                        dos = texto[i];
                    }
                }
            }
            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[2]))) {
                        tres = texto[i];
                    }
                }
            }

            String[] abc = new String[3];

            abc[0] = uno;
            abc[1] = dos;
            abc[2] = tres;

            for (int i = 0; i < abc.length; i++) {
                contenido = contenido + abc[i];

                System.out.println(contenido);
            }

        } catch (Exception a) {
        }

        String nombrePdf = "pdfTopClienteMayorDeuda.pdf";
        String title = "Top 3 clientes con mayor deuda al banco";
        float[] size = {2, 10, 6, 6, 6};
        CrearTH(nombrePdf, contenido, title, size);

    }

    public void CrearPDFEmpleadoAgencia(int idAgencia) {

        String contenido = "ID" + "'" + "Nombre" + "'" + "Direccion" + "'" + "Telefono" + "'" + " Departamento" + "'" + " Agencia" + "\n";
        String nagencia = "";
        try {
            Empleado[] empleado = EmpleadoController.getEmpleadoController().obtenerEmpleados();

            for (Empleado c : empleado) {
                if (c != null) {
                    if (c.getIdAgencia().equals(String.valueOf(idAgencia))) {
                        int id = c.getId();
                        String nombre = c.getNombre();
                        String direccion = c.getDireccion();
                        String telefono = c.getTelefono();
                        String departamento = c.getDepartamento();
                        String agencia = c.getAgencia();
                        nagencia = agencia;
                        contenido = contenido + id + "'" + nombre + "'" + direccion + "'" + telefono + "'" + departamento + "'" + agencia + "\n";;

                    }
                }
            }
        } catch (Exception a) {
        }

        String nombrePdf = "pdfClienteAgencia.pdf";
        String title = "Empleado de la agencia " + nagencia;
        float[] size = {2, 10, 6, 6, 6, 6};
        CrearTH(nombrePdf, contenido, title, size);

    }

    public void CrearPDFAgenciaMayorEmpleado() {

        String contenido = "ID" + "'" + "Nombre" + "'" + "Direccion" + "'" + "Telefono" + "'" + "Numero Cajas" + "'" + "Escritorios SC" + "'" + "Efectivo" + "'" + "Total Empleados" + "\n";
        AgenciaBancaria[] agencias = AgenciaController.getAgenciaController().obtenerAgencias();
        int[] result = new int[100];
        String[] texto = new String[100];
        float[] size = new float[]{2, 10, 6, 6, 3, 3, 3};

        int contador = 0;

        int id = 0;
        String nombre = "";
        String direccion = "";
        String telefono = "";
        int noCajas = 0;
        int escritorios = 0;
        double efectivo = 0.0;

        
        /*id = AgenciaController.getAgenciaController().obtenerAgencias()[c].getId();
                            nombre = AgenciaController.getAgenciaController().obtenerAgencias()[c].getNombre();
                            direccion = AgenciaController.getAgenciaController().obtenerAgencias()[c].getDireccion();
                            telefono = AgenciaController.getAgenciaController().obtenerAgencias()[c].getTelefono();
                            noCajas = AgenciaController.getAgenciaController().obtenerAgencias()[c].getNoCajas();
                            escritorios = AgenciaController.getAgenciaController().obtenerAgencias()[c].getEscritorios();
                            efectivo = AgenciaController.getAgenciaController().obtenerAgencias()[c].getEfectivo();*/
        try {
            for (int i = 0; i < AgenciaController.getAgenciaController().obtenerAgencias().length; i++) {
                if (AgenciaController.getAgenciaController().obtenerAgencias()[i] != null) {
                    
                    for (int j = 0; j < EmpleadoController.getEmpleadoController().obtenerEmpleados().length; j++) {
                        if (EmpleadoController.getEmpleadoController().obtenerEmpleados()[j] != null) {
                            Empleado e = EmpleadoController.getEmpleadoController().obtenerEmpleados()[j];
                            
                            AgenciaBancaria a = AgenciaController.getAgenciaController().obtenerAgencias()[i];
                            
                            if (e.getId().equals(a.getId())) {
                                System.out.println("El empleado" + e.getNombre() +  "pertenece a la agencia " + a.getNombre());
                                
                            }
                        }
                    }
                }
            }
            
            
            
            /*for (int i = 0; i < result.length; i++) {
                System.out.println(result[i]);
            }*/
            
            
            for (int i = 0; i < result.length - 1; i++) {
                for (int j = 0; j < result.length - 1; j++) {
                    if (result[j] < result[j + 1]) {
                        int tmp = result[j + 1];
                        result[j + 1] = result[j];
                        result[j] = tmp;
                    }
                }
            }

            for (int i = 0; i < texto.length; i++) {
                if (texto[i] != null) {
                    if (texto[i].contains(String.valueOf(result[0]))) {
                        contenido = contenido + texto[i];
                    }
                }
            }

        } catch (Exception a) {
        }
        //String nombrePdf = "pdfMayorCantidadEmpleados.pdf";
        //String title = "Agencia bancaria con mayor cantidad de empleados";
        //CrearTH(nombrePdf, contenido, title, size);

    }

    //////////////CREAR PDF////////////////////////////////////
    public void CrearTH(String nombre, String contenido, String title, float[] size) {
        ruta = ruta + nombre;
        try {
            new ReporteController().crearTah(ruta, contenido, title, size);
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ruta);
                ruta = String.valueOf(System.getProperty("user.dir")) + "\\pdf\\";
            } catch (IOException b) {
                b.printStackTrace();
            }
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    private void crearTah(String dest, String contenido, String title, float[] size) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf, PageSize.A4.rotate());
        doc.setMargins(100, 50, 50, 50);

        Paragraph header = new Paragraph("Banco del Exterior");
        Paragraph titulo = new Paragraph(title);
        Table tabla = new Table(size);
        titulo.setFontSize(22f);
        titulo.setBold();

        header.setFixedPosition(700, 550, 150);
        titulo.setFixedPosition(320, 525, 300);

        BufferedReader br = new BufferedReader(new StringReader(contenido));
        String datos = br.readLine();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                try {
                    tabla.getCell(i, j).setBorder(Border.NO_BORDER);
                } catch (Exception a) {
                }

            }
        }

        crearTabla(datos, tabla, true);

        while ((datos = br.readLine()) != null) {
            crearTabla(datos, tabla, false);
        }

        tabla.getHeader().setBold();
        tabla.setWidth(725);

        doc.add(tabla);
        doc.add(titulo);
        doc.add(header);
        doc.close();

    }

    public void crearTabla(String datos, Table tabla, boolean header) {
        StringTokenizer tk = new StringTokenizer(datos, "'");

        while (tk.hasMoreTokens()) {
            if (header) {
                tabla.addHeaderCell(new Cell().add(new Paragraph(tk.nextToken())));
            } else {
                tabla.addCell(new Cell().add(new Paragraph(tk.nextToken())));
            }
        }

    }

    public void Top(String nombre, String contenido, String title, float[] size) {
        ruta = ruta + nombre;
        try {
            new ReporteController().crearTop(ruta, contenido, title, size);
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + ruta);
                ruta = String.valueOf(System.getProperty("user.dir")) + "\\pdf\\";
            } catch (IOException b) {
                b.printStackTrace();
            }
        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public void crearTop(String dest, String contenido, String title, float[] size) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);
        doc.setMargins(100, 50, 50, 50);

        Paragraph header = new Paragraph("Banco del Exterior");
        Paragraph titulo = new Paragraph(title);
        Table tabla = new Table(size);

        titulo.setFontSize(22f);
        titulo.setBold();
        header.setFixedPosition(500, 750, 150);
        titulo.setFixedPosition(250, 725, 300);

        BufferedReader br = new BufferedReader(new StringReader(contenido));
        String datos = br.readLine();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                try {
                    tabla.getCell(i, j).setBorder(Border.NO_BORDER);
                } catch (Exception a) {
                }

            }
        }

        crearTabla(datos, tabla, true);

        while ((datos = br.readLine()) != null) {
            crearTabla(datos, tabla, false);
        }

        tabla.getHeader().setBold();
        tabla.setWidth(425);

        doc.add(tabla);
        doc.add(titulo);
        doc.add(header);
        doc.close();
    }

}
