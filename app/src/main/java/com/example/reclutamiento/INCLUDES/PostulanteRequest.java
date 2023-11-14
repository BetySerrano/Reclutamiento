package com.example.reclutamiento.INCLUDES;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PostulanteRequest  extends StringRequest {
    //colocar url de web service
    private static final String insert_postulante_URL= "http://45.40.160.177/APPRH/reclutamiento/insert_postulante.php";
    private Map<String,String> params;
    //clase que recibe las siguientes varibles que seran enviadas mediante el método POST
    public  PostulanteRequest(String nombrepos, String apPaternopos, String apMaternopos, String callepos, int numeroExtpos,int numeroIntpos, int codigoPostalpos, String coloniapos, String municipiopos, String estadopos, String telefonopos, String emailpos, String sexopos, String estadoCivilpos, String vacantepos, Response.Listener<String> listener){
        super(Request.Method.POST,insert_postulante_URL,listener,null);
//envio de datos
        params = new HashMap<>();
        params.put("nombre", nombrepos);
        params.put("apPaterno",apPaternopos);
        params.put("apMaterno",apMaternopos);
        params.put("calle",callepos);
        params.put("numeroExt",numeroExtpos+""); //transformación de entero a cadena de texto
        params.put("numeroInt",numeroIntpos+"");
        params.put("codigoPostal",codigoPostalpos+"");
        params.put("colonia",coloniapos);
        params.put("municipio",municipiopos);
        params.put("estado",estadopos);
        params.put("telefono",telefonopos);
        params.put("email",emailpos);
        params.put("sexo",sexopos);
        params.put("estadoCivil",estadoCivilpos);
        params.put("Vacante",vacantepos);

    }
    //retornamos los parametros
    public Map<String, String> getParams() {
        return params;
    }
}
