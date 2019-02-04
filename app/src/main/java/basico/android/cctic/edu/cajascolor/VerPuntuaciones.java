package basico.android.cctic.edu.cajascolor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.util.Collections;
import java.util.List;

public class VerPuntuaciones extends AppCompatActivity {

    private RecyclerView recView;
    private AdapterPuntuaciones adaptador;
    private List<Puntuacion> datos;
    private static final String APP = "coloresAPP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(APP, "Class VerPuntuaciones, onCreate");
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_ver_puntuaciones);
        try {
            datos = recuperarDatos();
            if (datos.size() != 0) {
                ordenarPorTiempo(findViewById(R.id.recycler));

            } else {
                ((TextView)findViewById(R.id.txtNombre)).setText("SIN PUNTUACIONES");
                ((TextView)findViewById(R.id.txtFoto)).setText("");
                ((TextView)findViewById(R.id.txtTiempo)).setText("");
            }
        }catch (JSONException e){
            Log.d(APP, "Error al recuperar los datos del fichero de puntuaciones");
        }
        Log.d(APP, "XX");
    }

    private List<Puntuacion>  recuperarDatos() throws JSONException {
        Log.d(APP, "Class VerPuntuaciones, recuperarDatos");
        Puntuaciones2 p = new Puntuaciones2(this);
        List<Puntuacion> listaPuntos = p.getPuntuaciones(this);
        Log.d(APP, "XX");
        return listaPuntos;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(APP, "Class VerPuntuaciones, onOptionsItemSelected");
        int id_item = item.getItemId();
        if (id_item == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void ordenarPorNombre(View v){
        Collections.sort(datos, new ComparadorPuntuaciones());
        adaptador = new AdapterPuntuaciones(datos, this);
        repintarPuntuaciones();
    }

    public void ordenarPorTiempo(View v){
        Collections.sort(datos);
        adaptador = new AdapterPuntuaciones(datos, this);
        repintarPuntuaciones();
    }

    private void repintarPuntuaciones(){
        adaptador = new AdapterPuntuaciones(datos, this);
        recView = (RecyclerView) findViewById(R.id.recycler);
        recView.setAdapter(adaptador);//mostrando la lista
        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if (recView.getItemDecorationCount() == 0)
            recView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
