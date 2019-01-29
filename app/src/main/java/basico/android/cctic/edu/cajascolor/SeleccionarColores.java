package basico.android.cctic.edu.cajascolor;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

public class SeleccionarColores extends AppCompatActivity {

    private static final String APP = "coloresAPP";
    private int tocadas = 0;
    int colortocado;
    long horaComienzo;
    int segundos;
    Chronometer crono;
    String usuario;
    ArrayList<View> cuadros;
    Puntuaciones2 p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(APP, "Class SeleccionarColores, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_colores);
        Log.d(APP, "Recojo el color tocado");
        colortocado = getResources().getColor(R.color.colorTocado);
        recogerCuadros();
        Log.d(APP, "Ya tengo el array de cuadros relleno");
        setPantalla();
        Log.d(APP, "Voy a comprobar si la partida está empezada");
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("empezada") != null) {
                //View boton = findViewById(R.id.btnEmpezar);//versión anterior con Button
                View boton = findViewById(R.id.play);
                boton.setTag(true);
                boton.setVisibility(boton.INVISIBLE);
                Log.d(APP, "La partida ya había empezado");
                String tocados = savedInstanceState.getString("tocados");
                Log.d(APP, "He recogido el string con la info de los cuadros tocados");
                for (int i = 0; i < tocados.length(); i++) {
                    Log.d(APP, "Seteo un cuadro como tocado");
                    int cuadro = Integer.parseInt(tocados.substring(i, i + 1));
                    cuadros.get(cuadro).setBackgroundColor(colortocado);
                }
                Log.d(APP, "Seteo el resto de variables de estado y arranco el reloj");
                tocadas = savedInstanceState.getInt("cantidadTocados");
                horaComienzo = savedInstanceState.getLong("horaComienzo");
                crono.setBase(savedInstanceState.getLong("tiempoCrono"));
                crono.start();  
            }
        }
        try {
            p2 = new Puntuaciones2(this);
            p2.getPuntuaciones(this);
        }catch(JSONException e){
            Log.d(APP, "Error al recoger las puntuaciones");
        }
    }

    private void setPantalla(){
        Log.d(APP, "Class SeleccionarColores, setPantalla");
        crono = (Chronometer) findViewById(R.id.crono);
        usuario = Preferencias.getUsuario(this);
        //ActionBar ab = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // para poner una flecha en el actionbar para volver
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.atras); // para personalizar el aspecto
        getSupportActionBar().setTitle(usuario);
    }

    private void recogerCuadros(){
        Log.d(APP, "Class SeleccionarColores, recogerCuadros");
        cuadros = new ArrayList<View>();
        cuadros.add(findViewById(R.id.uno));
        cuadros.add(findViewById(R.id.dos));
        cuadros.add(findViewById(R.id.tres));
        cuadros.add(findViewById(R.id.cuatro));
        cuadros.add(findViewById(R.id.cinco));
        cuadros.add(findViewById(R.id.seis));
        cuadros.add(findViewById(R.id.siete));
        cuadros.add(findViewById(R.id.ocho));
        cuadros.add(findViewById(R.id.nueve));
        cuadros.add(findViewById(R.id.diez));
        cuadros.add(findViewById(R.id.once));
        cuadros.add(findViewById(R.id.doce));
    }

    public void seleccionar(View v){
        Log.d(APP, "Class SeleccionarColores, seleccionar");
        //if(findViewById(R.id.btnEmpezar).getTag() != null) {//versión anterior con Button
        if(findViewById(R.id.play).getTag() != null) {
            Log.d(APP, "Voy a recoger la tag");
            Object tocado = v.getTag();
            ////////////////////////////////////////// con la versión de las tags esto ya no hace falta
            // recojo el color del cuadro pulsado  //
            // int color = Color.TRANSPARENT; //inicializo la variable
            // Drawable background = v.getBackground(); //selecciono el background y lo meto en una variable
            //if (background instanceof ColorDrawable) // si el fondo es de verdad un color y no otra cosa
            //    color = ((ColorDrawable) background).getColor(); // recojo el color
            // Log.d(APP, "El color es "+color);
            // int colortocado = getResources().getColor(R.color.colorTocado); // recupero mi color tocado de los recursos (debería ser de clase)
            // if (color != colortocado) { // si el color de la caja no coincide con el color tocado
            //////////////////////////////////////////
            if (tocado == null) {
                v.setTag(true);
                tocadas++; // cuento caja pulsada
                Log.d(APP, "Has tocado " + tocadas + " cuadros");
                v.setBackgroundColor(colortocado); // le pongo el color a la caja
                if (tocadas == 12) { // si se han tocado ya 12 cajas
                    partidaTerminada();
                }
            }
        }
    }

    private void partidaTerminada(){
        Log.d(APP, "Class SeleccionarColores, partidaTerminada");
        crono.stop();
        segundos = (int)((System.currentTimeMillis() - horaComienzo)/1000);
        /////// se muestra el toast
        Context context = getApplicationContext();
        CharSequence text = "Has tardado "+segundos+" segundos";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        /////// veo las puntuaciones
        verPuntuaciones();
        /////// se cierra la aplicación
        Handler handler = new Handler(); // se retrasa el cierre de la aplicación para ver el toast un rato antes de que desaparezca
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                salir();
            }
        }, 5000);
    }

    private void verPuntuaciones() {
        Log.d(APP, "Class SeleccionarColores, verPuntuaciones");
        agregarPuntuacion();
        ArrayList<Integer> puntuaciones;
        String mejoresPuntuaciones = "MEJORES TIEMPOS DE "+usuario+"\r\n";
        Puntuaciones clasePuntos = new Puntuaciones(this);
        clasePuntos.nuevaPuntuacion(segundos, this);
        puntuaciones = clasePuntos.getPuntuaciones();
        Log.d(APP, "Voy a escribir las puntuaciones en pantalla");
        if (puntuaciones.size() > 0) {
            mejoresPuntuaciones += "1º)  " + puntuaciones.get(0);
        }
        if (puntuaciones.size() > 1) {
            mejoresPuntuaciones += "\r\n2º)  " + puntuaciones.get(1);
        }
        if (puntuaciones.size() > 2) {
            mejoresPuntuaciones += "\r\n3º)  " + puntuaciones.get(2);
        }
        ((TextView)findViewById(R.id.verPuntuaciones)).setText(mejoresPuntuaciones);
    }

    private void agregarPuntuacion(){
        Log.d(APP, "Class SeleccionarColores, agregarPuntuacion");
        p2.setPuntuacion(usuario, segundos, this);
    }

    private void salir(){
        Log.d(APP, "Class SeleccionarColores, salir");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }else{
            this.finish(); // si es una versión antigua se cierra el Action, como no hay más pantallas se cierra la aplicación
        }
    }

    public void empezar(View view) {
        Log.d(APP, "Class SeleccionarColores, empezar");
        getSupportActionBar().hide();//ocultamos la barra superior en el momento de empezar la partida
        view.setVisibility(view.INVISIBLE); // INVISIBLE lo oculta, GONE lo borra del mapa
        view.setTag(true);
        horaComienzo = System.currentTimeMillis();
        // pongo el cronómetro a cero antes de arrancarlo, porque si no cuenta desde el momento en el que apareció la ventana
        crono.setBase(SystemClock.elapsedRealtime());
        crono.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(APP, "Class SeleccionarColores, onSaveInstanceState");
        super.onSaveInstanceState(outState);
        Log.d(APP, "Se comprueba que la partida ha comenzado para guardar las variables de estado");
        //if (findViewById(R.id.btnEmpezar).getTag() != null) { // versión anterior con Button
        if (findViewById(R.id.play).getTag() != null) {
            Log.d(APP, "Se recogen todos los cuadros tocados y se guardan");
            outState.putString("empezada", "si");
            String tocados = "";
            for (int i = 0; i < cuadros.size(); i++) {
                // recojo el color del cuadro //
                int color = Color.TRANSPARENT; //inicializo la variable
                Drawable background = cuadros.get(i).getBackground(); //selecciono el background y lo meto en una variable
                if (background instanceof ColorDrawable) // si el fondo es de verdad un color y no otra cosa
                    color = ((ColorDrawable) background).getColor(); // recojo el color
                Log.d(APP, "El color es " + color);
                if (color == colortocado) {
                    tocados += i;
                }
            }
            Log.d(APP, "Guardo todas las variables de estado");
            outState.putString("tocados", tocados);
            outState.putLong("horaComienzo", horaComienzo);
            outState.putInt("cantidadTocados", tocadas);
            outState.putLong("tiempoCrono", crono.getBase());
            Log.d(APP, "Variables guardadas: los cuadros que han sido tocados, "+tocados+"; cantidad de cuadros tocados, "+tocadas+"; hora de comienzo del juego, "+horaComienzo+"; tiempo transcurrido en el crono, "+crono.getBase());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(APP, "Class SeleccionarColores, onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu, menu);
        //menu.add(1, 1, 1, usuario);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(APP, "Class SeleccionarColores, onOptionsItemSelected");
        int id_item = item.getItemId();
        if (id_item == R.id.menuNombre){ // hay que buscar cuál ha sido el botón del menú pulsado
            Intent intent = new Intent(this, Login.class);
            intent.putExtra("Cambio", true);
            /*
            startActivity(intent);
            finish();
            */
            startActivityForResult(intent, 1);// se lanza esto para que la acción vuelva a esta pantalla otra vez
            // la pantalla siguiente tendrá que hacer: setResult (RESULT_OK, intent_de_vuelta);
        } else if (id_item == android.R.id.home){ // esta no es la R de mi proyecto, es la de android
            // se ha pulsado el botón de volver del menú superior
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(APP, "Class SeleccionarColores, onActivityResult");
        if (requestCode == 1){// El número que se le haya pasado en el startAtivityForResult (debería ser una constante)
            if (resultCode == RESULT_OK){
                Log.d(APP, "result ok");

            } else if (resultCode == RESULT_CANCELED){
                Log.d(APP, "result canceled");
            }
        }
    }
}