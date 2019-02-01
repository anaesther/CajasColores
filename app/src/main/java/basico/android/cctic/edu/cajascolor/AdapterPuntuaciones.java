package basico.android.cctic.edu.cajascolor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdapterPuntuaciones extends RecyclerView.Adapter<PuntuacionHolder> {

    List<Puntuacion> datos;
    private static final String APP = "coloresAPP";

    public AdapterPuntuaciones (List<Puntuacion> lista_puntuaciones) {
        this.datos = lista_puntuaciones;
    }

    @NonNull
    @Override
    public PuntuacionHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d(APP, "Class AdapterPuntuaciones, onCreateViewHolder");
        PuntuacionHolder puntuacionHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fila_puntuacion, parent, false);
        //itemView.setOnClickListener(this);//asociaría el listener
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
}
