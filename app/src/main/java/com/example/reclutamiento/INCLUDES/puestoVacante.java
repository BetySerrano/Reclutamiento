package com.example.reclutamiento.INCLUDES;
//clase para puesto de vacante
public class puestoVacante {
    private String puestoVacante;
    private int idVacante;
    public puestoVacante(){

    }

    public puestoVacante(String puestoVacante) {
        this.puestoVacante = puestoVacante;
    }

    public puestoVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public String getPuestoVacante() {
        return puestoVacante;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setPuestoVacante(String puestoVacante) {
        this.puestoVacante = puestoVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    @Override
    public String toString() {
        return puestoVacante;
    }
}
