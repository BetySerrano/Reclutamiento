package com.example.reclutamiento.INCLUDES;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EmpleadoRequest extends StringRequest {

    //colocar url de web service
    private static final String insert_empleado_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_empleado.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public EmpleadoRequest(String nombreEmpleado, String apPaterno, String apMaterno, String calle, int noExt, int noInt, int cp, String colonia, String municipio, String estado, String telefono, String email, String sexo, String edoCivil, String fechaNac, String area, String puesto, Response.Listener<String> listener) {
        super(Request.Method.POST,insert_empleado_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("nombre", nombreEmpleado);
        params.put("apPaterno",apPaterno);
        params.put("apMaterno",apMaterno);
        params.put("calle",calle);
        params.put("numeroExt",noExt+""); //transformación de entero a cadena de texto
        params.put("numeroInt",noInt+"");
        params.put("codigoPostal",cp+"");
        params.put("colonia", colonia);
        params.put("municipio",municipio);
        params.put("estado",estado);
        params.put("telefono",telefono);
        params.put("email",email);
        params.put("sexo",sexo);
        params.put("estadoCivil",edoCivil);
        params.put("fechaNacimiento",fechaNac);
        params.put("area",area);
        params.put("puesto",puesto);
        //params.put("fechaEntrada", fechaing);
    }

    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}
