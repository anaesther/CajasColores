package basico.android.cctic.edu.cajascolor;

public class Puntuacion {

    private String nombre;
    private long puntuacion;

    public Puntuacion(){}

    public Puntuacion(String nombre, long puntuacion){
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(long puntuacion) {
        this.puntuacion = puntuacion;
    }
}
