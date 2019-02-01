package basico.android.cctic.edu.cajascolor;

import android.util.Log;

public class Puntuacion  implements Comparable {

    private String nombre;
    private long puntuacion;
    private static final String APP = "coloresAPP";

    public Puntuacion(){}

    public Puntuacion(String nombre, long puntuacion){
        Log.d(APP, "Class Puntuacion, constructor");
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        Log.d(APP, "XX");
    }

    public String getNombre() {
        Log.d(APP, "Class Puntuacion, getNombre XX");
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPuntuacion() {
        Log.d(APP, "Class Puntuacion, getPuntuacion XX");
        return puntuacion;
    }

    public void setPuntuacion(long puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public int compareTo(Object o) { // si this se tiene que ordenar después se devuelve un nº positivo, si va antes un negativo y si son iguales devuelve 0
        Log.d(APP, "Class Puntuacion, compareTo");
        int comparacion = 0;
        /*
        if (this.puntuacion < ((Puntuacion)o).getPuntuacion()){
            comparacion = -1;
        } else if (this.puntuacion > ((Puntuacion)o).getPuntuacion()){
            comparacion = 1;
        }
        */
        comparacion = (int)this.puntuacion - (int)((Puntuacion)o).getPuntuacion();
        //comparacion = this.nombre.compareTo(((Puntuacion)o).getNombre()); // compara por orden alfabético
        Log.d(APP, "XX");
        return comparacion;
    }
}
