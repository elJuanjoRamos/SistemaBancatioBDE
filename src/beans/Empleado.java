/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Ana Ramos
 */
public class Empleado {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String departamento;
    private String idAgencia;
    private String agencia;
    
    
    public Empleado(int id,String nombre,String direccion,String telefono, String departamento, String agencia, String idAgencia){
       
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.departamento = departamento;
        this.idAgencia = idAgencia;
        this.agencia = agencia;
    }
    public Empleado(){
        
    }
        
    //Metodo Get
    public Integer getId() {
        return this.id;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getDireccion() {
        return this.direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public String getDepartamento() {
        return this.departamento;
    }
    
    //Metodos Set
    public void setId(int var) {
        this.id = var;
    }
    public void setNombre(String var) {
        this.nombre = var;
    }
    public void setDireccion(String var) {
        this.direccion = var;
    }
    public void setTelefono(String var) {
        this.telefono = var;
    }
    public void setDepartamento(String var) {
        this.departamento = var;
    }
    
    
    public String getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(String idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    
}
