package com.example.reclutamiento.INCLUDES;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ModifcaVacanteRequest extends StringRequest {
    //colocar url de web service
    private static final String modifica_vacante_URL= "http://45.40.160.177/APPRH/reclutamiento/editaVacante.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public  ModifcaVacanteRequest(int idVacante,String titulovacante, String puestovacante, String descripcion, String sueldo, String prestaciones, String horarios, String diasLaborables, Response.Listener<String> listener){
        super(Request.Method.POST,modifica_vacante_URL,listener,null);
        //envio de datos
        params = new HashMap<>();
        params.put("idVacante",idVacante+"");
        params.put("tituloVacante", titulovacante);
        params.put("puestoVacante",puestovacante);
        params.put("descripcion",descripcion);
        params.put("sueldo",sueldo); //("sueldo",sueldo+"");  transformación de entero a cadena de texto
        params.put("prestaciones",prestaciones);
        params.put("horarios",horarios);
        params.put("diasLaborables",diasLaborables);
    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}
