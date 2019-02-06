package basico.android.cctic.edu.cajascolor;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable; // dejamos de usar serializable porque parcelable es más eficiente

public class Puntuacion  implements Comparable, Parcelable {

    private String nombre;
    private long puntuacion;
    private static final String APP = "coloresAPP";

    public Puntuacion(){}

    public Puntuacion(String nombre, long puntuacion){
        Log.d(APP, "Class Puntuacion, constructor(String, long)");
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        Log.d(APP, "XX");
    }

    protected Puntuacion(Parcel in) {
        Log.d(APP, "Class Puntuacion, constructor(Parcel)");
        nombre = in.readString();
        puntuacion = in.readLong();
        Log.d(APP, "XX");
    }

    public static final Creator<Puntuacion> CREATOR = new Creator<Puntuacion>() {
        @Override
        public Puntuacion createFromParcel(Parcel in) {
            return new Puntuacion(in);
        }

        @Override
        public Puntuacion[] newArray(int size) {
            return new Puntuacion[size];
        }
    };

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

    @Override
    public String toString() {
        return "Nombre: "+nombre+", puntuación: "+puntuacion;
    }

    //Este método y el siguiente hay que ponerlos porque implementamos parcelable
    // parcelable es como Serializable, pero más eficiente
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(APP, "Class Puntuacion, writeToParcel");
        dest.writeString(nombre);
        dest.writeLong(puntuacion);
        Log.d(APP, "XX");
    }

    private void readFromParcel(Parcel in) {
        nombre = in.readString();
        puntuacion = in.readLong();
    }
}
