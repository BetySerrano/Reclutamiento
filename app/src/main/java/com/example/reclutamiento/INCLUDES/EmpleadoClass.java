package com.example.reclutamiento.INCLUDES;

public class EmpleadoClass {
    private String nombre;
    private int idEmpleado;

    public EmpleadoClass(){

    }

    public EmpleadoClass(String nombre) {
        this.nombre = nombre;
    }

    public EmpleadoClass(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getnombre() {
        return nombre;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return  nombre;
    }
}
