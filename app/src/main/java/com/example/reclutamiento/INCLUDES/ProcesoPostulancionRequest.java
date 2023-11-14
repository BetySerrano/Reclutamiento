package com.example.reclutamiento.INCLUDES;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProcesoPostulancionRequest extends StringRequest {
    //colocar url de web service
    private static final String insert_proceso_URL= "http://45.40.160.177/APPRH/reclutamiento/procesoPostulacion.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public  ProcesoPostulancionRequest(String estatusproceso, String fechainicio, String fechafin, String resultadop1, String resultadop2, int idproceso, Response.Listener<String> listener){
        super(Method.POST,insert_proceso_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("estatusProceso", estatusproceso);
        params.put("fechaInicio",fechainicio);
        params.put("fechaFin",fechafin);
        params.put("resultadoP1",resultadop1);
        params.put("resultadoP2",resultadop2);
        params.put("idPostulante",idproceso+""); //transformación de entero a cadena de texto
    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}
