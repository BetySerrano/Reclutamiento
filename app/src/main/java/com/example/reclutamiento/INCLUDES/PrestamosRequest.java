package com.example.reclutamiento.INCLUDES;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PrestamosRequest extends StringRequest {
    //colocar url de web service
    private static final String insert_prestamos_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_prestamos.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public  PrestamosRequest(String imss, String prestmo, String cartas, String infonavit, String guarderia, int idEmpl, Response.Listener<String> listener){
        super(Method.POST,insert_prestamos_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("altaIMSS", imss);
        params.put("prestamo",prestmo);
        params.put("cartasPatronales",cartas);
        params.put("infonavit",infonavit);
        params.put("guarderia",guarderia);
        params.put("idEmpleado",idEmpl+""); // transformación de entero a cadena de texto
    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}
