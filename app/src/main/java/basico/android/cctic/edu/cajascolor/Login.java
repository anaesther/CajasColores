package basico.android.cctic.edu.cajascolor;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    //private Preferencias preferencias;
    private static final String APP = "coloresAPP";
    boolean cambioNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(APP, "Class Login, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //preferencias = new Preferencias(this);
        String usuario = Preferencias.getUsuario(this);
        Intent intent = getIntent();
        cambioNombre = intent.getBooleanExtra("Cambio", false);
        Log.d(APP, "¿Cambiar el nombre? "+cambioNombre);
        if (!usuario.equals("") && !cambioNombre){
            avanzar();
        }
    }

    public void guardarNombre(View v){
        Log.d(APP, "Class Login, guardarNombre");
        EditText et = (EditText)findViewById(R.id.nombreUsuario);
        String nombre = et.getText().toString();
        Preferencias.setUsuario(nombre, this);
        avanzar();
    }

    private void avanzar() {
        Log.d(APP, "Class Login, avanzar");
        // Este intent me cierra la pila de actividades, al final vuelvo a la anterior en vez de abrir una nueva.
        // Lo bueno es que no tengo mil actividades iguales abiertas, lo malo es que quiero que sea nueva
        //Intent intent = new Intent(this, SeleccionarColores.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //////////////
        Intent intent = new Intent(this, SeleccionarColores.class);

        if (!cambioNombre) {
            startActivity(intent);
        } else {
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    public void cancelar(View v){
        Log.d(APP, "Class Login, cancelar");
        if (cambioNombre){
            Intent intent = new Intent(this, SeleccionarColores.class);
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}
