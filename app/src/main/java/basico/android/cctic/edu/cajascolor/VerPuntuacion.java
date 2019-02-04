package basico.android.cctic.edu.cajascolor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class VerPuntuacion extends AppCompatActivity {


    private static final String APP = "coloresAPP";
    private Puntuacion puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(APP, "Class VerPuntuacion, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_puntuacion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        puntuacion = (Puntuacion)intent.getExtras().get("puntuacion");
        pintarPuntuacion();
        Log.d(APP, "XX");
    }

    private void pintarPuntuacion(){
        TextView caja = findViewById(R.id.puntuacion);
        String texto = puntuacion.getNombre()+"\r\n"+puntuacion.getPuntuacion();
        caja.setText(texto);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(APP, "Class VerPuntuacion, onOptionsItemSelected");
        int id_item = item.getItemId();
        if (id_item == android.R.id.home){ // esta no es la R de mi proyecto, es la de android
            // se ha pulsado el botón de volver del menú superior
            super.onBackPressed();
        }
        Log.d(APP, "XX");
        return super.onOptionsItemSelected(item);
    }
}
