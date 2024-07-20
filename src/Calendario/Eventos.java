/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calendario;

/**
 *
 * @author usuario
 */
public class Eventos {
    private String dia;
    private String mes;
    private String año;
    private String descripcion;
    public Eventos(){
        this.dia = "";
        this.mes = "";
        this.año = "";
        this.descripcion = "";
    }
    
    public void setDatos(String dia, String mes, String año, String descripcion){
        this.dia = dia;
        this.mes = mes;
        this.año = año;
        this.descripcion = descripcion;
    }   
    
    @Override
    public String toString(){
        return dia + " / " + mes + " / " + año+ "      " + descripcion;
    }
}
