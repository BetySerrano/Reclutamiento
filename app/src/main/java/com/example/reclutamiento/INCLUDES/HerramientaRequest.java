package com.example.reclutamiento.INCLUDES;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class HerramientaRequest  extends StringRequest {
    //colocar url de web service
    private static final String insert_herramienta_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_herramienta.php";
    private Map<String,String> params;
    public HerramientaRequest(String nomHerramienta, String uniHerramienta, String dateentrega, String dateEntregaRH, int idEmpleado, Response.Listener<String> listener) {
        super(Method.POST,insert_herramienta_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("nombreHerramienta", nomHerramienta);
        params.put("unidad",uniHerramienta);
        params.put("fechaEntregaEmp",dateentrega);
        params.put("fechaEntregaRh",dateEntregaRH);
        params.put("idEmpleado",idEmpleado+""); // transformación de entero a cadena de texto
    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}

