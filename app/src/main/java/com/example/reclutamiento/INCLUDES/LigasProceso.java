package com.example.reclutamiento.INCLUDES;
//clase de ligas de proceso para reclutamiento
public class LigasProceso {
    private String ligas;

    public LigasProceso(){

    }
    public LigasProceso(String ligas) {
        this.ligas = ligas;
    }

    public String getLigas() {
        return ligas;
    }

    public void setLigas(String ligas) {
        this.ligas = ligas;
    }

    @Override
    public String toString() {
        return ligas;
    }
}
