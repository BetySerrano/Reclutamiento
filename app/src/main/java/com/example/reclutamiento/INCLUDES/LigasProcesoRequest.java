package com.example.reclutamiento.INCLUDES;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LigasProcesoRequest extends StringRequest {
    //colocar url de web service
    private static final String insert_ligaproceso_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_ligasProceso.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public  LigasProcesoRequest(String fechaRegistro, String fechaContacto, String fechaEntrevista, String liga, Response.Listener<String> listener){
        super(Request.Method.POST,insert_ligaproceso_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("fechaRegistro", fechaRegistro);
        params.put("fechaContacto",fechaContacto);
        params.put("fechaEntrevista",fechaEntrevista);
        params.put("liga",liga);
    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}
