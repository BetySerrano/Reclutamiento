package com.example.reclutamiento.INCLUDES;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DocsEmpleadoRequest extends StringRequest {
    //colocar url de web service
    private static final String insert_empleadodocs_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_empleadoDocs.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public DocsEmpleadoRequest(String cedula, String titulo, String ine, String curp, String acta, String seguro, String cartilla, String domicilio, String nopenales,int idempe, Response.Listener<String> listener) {
        super(Request.Method.POST,insert_empleadodocs_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("cedula", cedula);
        params.put("titulo",titulo);
        params.put("ine",ine);
        params.put("curp",curp);
        params.put("actaNacimiento",acta); //transformación de entero a cadena de texto
        params.put("numSeguroSocial",seguro);
        params.put("cartilla",cartilla);
        params.put("comprobanteDomicilio", domicilio);
        params.put("antecedentesNoPenales",nopenales);
        params.put("idEmpleado",idempe+"");
    }

    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}
