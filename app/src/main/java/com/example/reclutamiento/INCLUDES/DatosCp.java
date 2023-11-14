package com.example.reclutamiento.INCLUDES;

public class DatosCp {
   private String error;
   private int code_error;
   private String error_message;
   private int cp;
   private String asentamiento;
   private String municipio;
   private String estado;

    public  DatosCp() {}
    public DatosCp(String error, int code_error, String error_message, int cp, String asentamiento, String municipio, String estado) {
        this.error = error;
        this.code_error = code_error;
        this.error_message = error_message;
        this.cp = cp;
        this.asentamiento = asentamiento;
        this.municipio = municipio;
        this.estado = estado;
    }

    public String geterror() {
        return error;
    }

    public int getcode_error() {
        return code_error;
    }

    public String geterror_message() {
        return error_message;
    }

    public int getcp() {
        return cp;
    }

    public String getasentamiento() {
        return asentamiento;
    }

    public String getmunicipio() {
        return municipio;
    }

    public String getestado() {
        return estado;
    }

}
