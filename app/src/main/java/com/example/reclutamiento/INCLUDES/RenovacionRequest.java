package com.example.reclutamiento.INCLUDES;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RenovacionRequest extends StringRequest {
    //colocar url de web service
    private static final String insert_renovacion_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_renovacion.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public RenovacionRequest(int idemple, String tipoC,int vacaciones, Response.Listener<String> listener) {
        super(Method.POST,insert_renovacion_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("idEmpleado", idemple+"");
        params.put("tipocontrato",tipoC);
        params.put("vacaciones",vacaciones+"");
    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }

}
