package basico.android.cctic.edu.cajascolor;

import android.util.Log;

import java.util.Comparator;

public class ComparadorPuntuaciones implements Comparator<Puntuacion> {

    private static final String APP = "coloresAPP";

    public int compare(Puntuacion p1, Puntuacion p2){
        Log.d(APP, "Class ComparadorPuntuaciones, compare");
        int orden = 0;
        orden = p1.getNombre().toLowerCase().compareTo(p2.getNombre().toLowerCase());
        if (orden == 0){
            orden = p1.compareTo(p2);
        }
        Log.d(APP, "XX");
        return orden;
    }
}
