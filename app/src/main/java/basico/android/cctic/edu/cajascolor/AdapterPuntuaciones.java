package basico.android.cctic.edu.cajascolor;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterPuntuaciones extends RecyclerView.Adapter<PuntuacionHolder> implements View.OnClickListener {

    List<Puntuacion> datos;
    private static final String APP = "coloresAPP";
    Context context;

    public AdapterPuntuaciones (List<Puntuacion> lista_puntuaciones, Context context) {
        this.datos = lista_puntuaciones;
        this.context = context;
    }

    @NonNull
    @Override
    public PuntuacionHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d(APP, "Class AdapterPuntuaciones, onCreateViewHolder");
        PuntuacionHolder puntuacionHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fila_puntuacion, parent, false);
        // Este listener se tiene que asociar en tiempo de ejecución porque se generan al vuelo, no existen antes de ver la pantalla de puntuaciones
        itemView.setOnClickListener(this);//asocio el listener
        puntuacionHolder = new PuntuacionHolder(itemView);
        Log.d(APP, "XX");
        return puntuacionHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PuntuacionHolder holder, int position) {
        Log.d(APP, "Class AdapterPuntuaciones, onBindViewHolder");
        Puntuacion puntacion = datos.get(position);
        holder.cargarPuntuacion(puntacion, position);
        Log.d(APP, "XX");
    }

    @Override
    public int getItemCount() {
        Log.d(APP, "Class AdapterPuntuaciones, getItemCount XX");
        return datos.size();
    }

    @Override
    public void onClick(View v) {
        Log.d(APP, "Class AdapterPuntuaciones, onClick");
        Intent i = new Intent(context, VerPuntuacion.class);
        ////////// Hurgar en el view para recuperar los datos navegando
        //String nombre = ((TextView)((ViewGroup)((ViewGroup)v).getChildAt(1)).getChildAt(0)).getText().toString();
        //String puntuacion = ((TextView)((ViewGroup)((ViewGroup)v).getChildAt(2)).getChildAt(0)).getText().toString();
        ////////// buscar en el view directamente con el id de las cajas
        Log.d(APP, "1");
        String nombre = ((TextView)v.findViewById(R.id.nombre_jugador)).getText().toString();
        Log.d(APP, "2");
        //Log.d(APP, ((TextView)v.findViewById(R.id.nombre_jugador)).getText().toString());
        Long puntuacion = Long.parseLong(((TextView)v.findViewById(R.id.tiempo_jugador)).getText().toString());
        Log.d(APP, "3");
        Puntuacion p = new Puntuacion(nombre, puntuacion);
        Log.d(APP, "4");
        i.putExtra("puntuacion", p);
        Log.d(APP, "5");
        context.startActivity(i);
        Log.d(APP, "XX");
    }
}
