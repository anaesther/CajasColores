package basico.android.cctic.edu.cajascolor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PuntuacionHolder extends RecyclerView.ViewHolder{

    private TextView caja_tiempo_jugador;
    private TextView caja_nombre_jugador;
    private static final String APP = "coloresAPP";

    public PuntuacionHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(APP, "Class PuntuacionHolder, constructor");
        caja_nombre_jugador = (TextView) itemView.findViewById(R.id.nombre_jugador);
        caja_tiempo_jugador = (TextView) itemView.findViewById(R.id.tiempo_jugador);
        Log.d(APP, "XX");
    }

    public void cargarPuntuacion(Puntuacion p, int position) {
        Log.d(APP, "Class PuntuacionHolder, cargarPuntuacion");
        caja_tiempo_jugador.setText(p.getPuntuacion() + "");
        caja_nombre_jugador.setText(p.getNombre());
        if (position%2 == 0){//para que me pinte de colores diferentes los campos pares, pero no repinta bien al desplazar
            itemView.findViewById(R.id.contenedorPuntuacion).setBackgroundColor(itemView.getResources().getColor(R.color.colorGrisClaro));
            Log.d(APP, "Repinto con otro color!!");
        }
        Log.d(APP, "XX");
    }
}
