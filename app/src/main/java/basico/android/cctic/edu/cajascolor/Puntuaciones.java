package basico.android.cctic.edu.cajascolor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class Puntuaciones {

    public final static String FILE = "puntuaciones";//nombre del fichero de preferences perfil.xml será
    private ArrayList<Integer> puntuaciones;
    private static final String APP = "coloresAPP";
    private static final String clavePuntuaciones = "puntos";

    public Puntuaciones(Context context) {
        puntuaciones = new ArrayList<Integer>();
        Log.d(APP, "He inicializado el arraylist de puntuaciones");
        SharedPreferences datosGuardados = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        Log.d(APP, "Recupero el xml");
        String listaPuntuaciones = datosGuardados.getString(clavePuntuaciones, "");
        Log.d(APP, "he recuperado a las puntuaciones guardadas: "+listaPuntuaciones);
        if (!listaPuntuaciones.equals("")) {
            // Separo los elementos por las comas
            String[] puntuacionesString = listaPuntuaciones.split(",");
            for (int i = 0; i < puntuacionesString.length; i++) {
                Log.d(APP, "Añado " + i);
                puntuaciones.add(Integer.parseInt(puntuacionesString[i]));
            }
        }
        Log.d(APP, "He terminado el constructor de puntuaciones");
    }

    public ArrayList<Integer> getPuntuaciones() {
        return puntuaciones;
    }

    public void nuevaPuntuacion(int segundos, Context context){
        Log.d(APP, "He terminado el constructor de puntuaciones");
        puntuaciones.add(segundos);
        for (int i=0; i<puntuaciones.size()-1; i++){
            for (int j=0; j<puntuaciones.size()-(i+1); j++){
                if (puntuaciones.get(j) > puntuaciones.get(j+1)){
                    Integer p = puntuaciones.get(j);
                    puntuaciones.remove(j);
                    puntuaciones.add(j+1, p);
                }
            }
        }
        Log.d(APP, "Segundos "+segundos+" puntuaciones en el array "+puntuaciones.size());
        guardarPuntuaciones(context);
    }

    private void guardarPuntuaciones(Context context){
        Log.d(APP, "Voy a guardar la puntuación");
        String puntuacionesString = "";
        SharedPreferences datosGuardados = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        Log.d(APP, "He recuperado el archivo xml");
        SharedPreferences.Editor editor = datosGuardados.edit();
        Log.d(APP, "He creado el editor");
        for (int i=0; i<puntuaciones.size(); i++){
            Log.d(APP, "Guardo una puntuación");
            puntuacionesString += puntuaciones.get(i)+",";
        }
        if (puntuacionesString.length() > 0) {
            puntuacionesString = puntuacionesString.substring(0, puntuacionesString.length() - 1);
        }
        Log.d(APP, "El string de puntuaciones es "+puntuacionesString);
        // puntuacionesString = ""; para resetear las puntuaciones
        editor.putString(clavePuntuaciones, puntuacionesString);
        editor.commit();
        Log.d(APP, "Commit hecho");
    }
}
