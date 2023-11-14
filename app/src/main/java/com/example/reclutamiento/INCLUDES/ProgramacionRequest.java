package com.example.reclutamiento.INCLUDES;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ProgramacionRequest extends StringRequest {
    //colocar url de web service
    private static final String insert_programacion_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_programacion.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public ProgramacionRequest(String fechav, String tallas, int idpos, Response.Listener<String> listener) {
        super(Method.POST,insert_programacion_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("fechaVisita", fechav);
        params.put("tallas",tallas);
        params.put("idPostulante",idpos+"");  //transformación de entero a cadena de texto
    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }

}
