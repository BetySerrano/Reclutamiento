package com.example.reclutamiento.INCLUDES;

public class area {
    private  String tituloVacante;

    public area(){

    }

    public area(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String gettituloVacante() {
        return this.tituloVacante;
    }

    public void settituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    @Override
    public String toString() {
        return tituloVacante ;
    }
}
