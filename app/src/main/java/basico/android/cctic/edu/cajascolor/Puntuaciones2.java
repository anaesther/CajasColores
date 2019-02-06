package basico.android.cctic.edu.cajascolor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Puntuaciones2 {

    //////////////////////////////////
    // En esta clase vamos a guardar y recuperar la información en JSon
    // Primero lo hago metiendo todos los objetos en un arraylist
    // Luego metiendo objetos independientes para hacer un mapa clave-valor
    //////////////////////////////////

    private List<Puntuacion> puntuaciones;
    public final static String FILE = "puntuaciones2";//nombre del fichero de preferences perfil.xml será
    private static final String clavePuntuaciones = "puntuaciones";
    private static final String APP = "coloresAPP";

    public Puntuaciones2(String nombre, long puntuacion, Context context) throws JSONException {
        //puntuaciones = new ArrayList<Puntuacion>();
        puntuaciones = Collections.synchronizedList(new ArrayList<Puntuacion>());
        Log.d(APP, "longitud de las puntuaciones antes de leer el fichero "+puntuaciones.size());
        recuperarPuntuaciones(context);
        Log.d(APP, "longitud de las puntuaciones después de leer del fichero "+puntuaciones.size());
        puntuaciones.add(new Puntuacion(nombre, puntuacion));
        //puntuaciones.add(new Puntuacion("otravezyo", 12));
        guardarPuntuaciones(context);
    }

    public Puntuaciones2(Context context) throws JSONException {
        Log.d(APP, "Clase Puntuaciones2, constructor");
        puntuaciones = Collections.synchronizedList(new ArrayList<Puntuacion>());
        recuperarPuntuaciones(context);
        Log.d(APP, "XX");
    }

    public List<Puntuacion> getPuntuaciones(Context context) throws JSONException {
        Log.d(APP, "Clase Puntuaciones2, getPuntuaciones");
        recuperarPuntuaciones(context);
        Log.d(APP, "XX");
        return puntuaciones;
    }

    public void setPuntuacion(String nombre, int puntuacion, Context context) {
        Log.d(APP, "Class Puntuaciones2, setPuntuacion");
        puntuaciones.add(new Puntuacion(nombre, puntuacion));
        //this.puntuaciones = puntuaciones;
        guardarPuntuaciones(context);
        Log.d(APP, "XX");
    }

    private void guardarPuntuaciones(Context context){
        Log.d(APP,"Class Puntuaciones2, guardarPuntuaciones");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Puntuacion>>() {}.getType();
        String json = gson.toJson(puntuaciones, listType);
        SharedPreferences datosGuardados = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datosGuardados.edit();
        editor.putString(clavePuntuaciones, json);
        editor.commit();
        Log.d(APP, "XX");
    }

    private void recuperarPuntuaciones(Context context) throws JSONException {
        Log.d(APP, "Clase Puntuaciones2, método recuperarPuntuaciones");
        puntuaciones = Collections.synchronizedList(new ArrayList<Puntuacion>());//La vuelvo a inicializar para que no me duplique si se llama varias veces
        SharedPreferences datosGuardados = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String str_puntuaciones = datosGuardados.getString(clavePuntuaciones, "");
        if (!str_puntuaciones.equals("")) {
            JSONArray jsonarray = new JSONArray(str_puntuaciones);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                puntuaciones.add(new Puntuacion(jsonobject.getString("nombre"), jsonobject.getLong("puntuacion")));
            }
        }
        Log.d(APP, "XX");
    }


    // OPCIÓN 2, GUARDAR OBJETOS SEPARADOS EN EL XML EN VEZ DE GUARDAR UN ARRAY

    public List<Puntuacion> cargarListaRecords (Context context){
        List<Puntuacion> lp = null;
        String puntuacion_en_curso;
        Gson gson = null;
        Puntuacion punt_aux = null;

        SharedPreferences sp = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        Map<String, String> mapa = (Map<String, String>)sp.getAll(); // Map: estructura de datos clave-valor

        // hacen falta las claves para poder recorrer el mapa, porque no funcionan por posiciones, sino por claves
        Set<String> claves = mapa.keySet(); // obtengo todas las claves
        gson = new Gson();
        lp = new ArrayList<Puntuacion>();
        for (String clave: claves){
            puntuacion_en_curso = mapa.get(clave); //aquí recojo un objeto json serializado
            punt_aux = gson.fromJson(puntuacion_en_curso, Puntuacion.class);
            lp.add(punt_aux);
        }
        return lp;
    }

    /// Método sin hacer, ahora mismo es un copy-paste del anterior !!!!!!!!!
    public List<Puntuacion> grabarListaRecords (Context context){
        List<Puntuacion> lp = null;
        String puntuacion_en_curso;
        Gson gson = null;
        Puntuacion punt_aux = null;

        SharedPreferences sp = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        Map<String, String> mapa = (Map<String, String>)sp.getAll(); // Map: estructura de datos clave-valor

        // hacen falta las claves para poder recorrer el mapa, porque no funcionan por posiciones, sino por claves
        Set<String> claves = mapa.keySet(); // obtengo todas las claves
        gson = new Gson();
        lp = new ArrayList<Puntuacion>();
        for (String clave: claves){
            puntuacion_en_curso = mapa.get(clave); //aquí recojo un objeto json serializado
            punt_aux = gson.fromJson(puntuacion_en_curso, Puntuacion.class);
            lp.add(punt_aux);
        }
        return lp;
    }
}
