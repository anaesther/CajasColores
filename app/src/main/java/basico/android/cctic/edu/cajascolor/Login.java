package basico.android.cctic.edu.cajascolor;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    //private Preferencias preferencias;
    private static final String APP = "coloresAPP";
    boolean cambioNombre;
    private static final int ACTIVITY_CAMERA = 1;
    private static final int ACTIVITY_SELECT_IMAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(APP, "Class Login, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //preferencias = new Preferencias(this);
        String usuario = Preferencias.getUsuario(this);
        Intent intent = getIntent();
        cambioNombre = intent.getBooleanExtra("Cambio", false);
        Log.d(APP, " ¿Cambiar el nombre? "+cambioNombre);
        if (!usuario.equals("") && !cambioNombre){
            avanzar();
        }
        Log.d(APP, "XX");
    }

    public void guardarNombre(View v){
        Log.d(APP, "Class Login, guardarNombre");
        EditText et = (EditText)findViewById(R.id.nombreUsuario);
        String nombre = et.getText().toString();
        Preferencias.setUsuario(nombre, this);
        avanzar();
        Log.d(APP, "XX");
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
        Log.d(APP, "XX");
    }

    public void cancelar(View v){
        Log.d(APP, "Class Login, cancelar");
        if (cambioNombre){
            Intent intent = new Intent(this, SeleccionarColores.class);
            setResult(RESULT_CANCELED, intent);
        }
        finish();
        Log.d(APP, "XX");
    }

    public void tomarFoto(View v){
        Log.d(APP, "Class Login, tomarFoto");
        /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.withAppendedPath(R.drawable, "fotoperfil"));//dónde guardarla y cómo llamar la foto
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }*/

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d(APP, " Creo el intent");
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            Log.d(APP, " entro en el if");
            startActivityForResult(takePictureIntent, ACTIVITY_CAMERA);
            Log.d(APP, " hemos hecho el activity for result");
        }
        Log.d(APP, "XX");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(APP, "Class Login, onActivityResult");
        if (requestCode == ACTIVITY_CAMERA){
            switch (resultCode){
                case RESULT_OK:
                    setearFotoTomada(data);
                    break;
                case RESULT_CANCELED:
                    Log.d(APP, " Ha cancelado foto");
                    break;
            }
        } else if (requestCode == ACTIVITY_SELECT_IMAGE){
            switch (resultCode) {
                case RESULT_OK:
                    setearFotoSeleccionada(data);
                    break;
                case RESULT_CANCELED:
                    Log.d(APP, " Canceló la foto");
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(APP, "XX");
    }

    public void buscarFoto(View v){
        Log.d(APP, "Class Login, buscarFoto");
        Intent intentpidefoto = new Intent ();
        intentpidefoto.setAction(Intent.ACTION_PICK);
        intentpidefoto.setType("image/*");//TIPO MIME
        startActivityForResult(intentpidefoto, ACTIVITY_SELECT_IMAGE);
        Log.d(APP, "XX");
    }

    private void setearFotoTomada(@Nullable Intent data){
        Log.d(APP, "Class Login, setearFotoTomada");
        //Bitmap foto = (Bitmap)data.getExtras().get("data");
        //Log.d(APP, data.getData().toString()); // se cierra la aplicación
        //Log.d(APP, data.getDataString()); // se cierra la aplicación
                    /*if (data.getData().toString() == null){ //esto casca
                        Log.d(APP, "ES NULO");
                    }else{
                        Log.d(APP, "NO ES NULO");
                    }
                    if (data.getData() == null){ //esto es nulo
                        Log.d(APP, "ES NULO");
                    }else{
                        Log.d(APP, "NO ES NULO");
                    }*/
                    /* El código antiguo, mejor el siguiente, el que pasó el teacher
                    Log.d(APP, " Recojo la foto");
                    ImageView im = findViewById(R.id.setFoto);
                    Log.d(APP, " He ido a por el view");
                    im.setImageBitmap(foto);
                    Log.d(APP, " He seteado la foto");
                    break;
                    */
        /// Código del teacher
        try {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ImageView im = findViewById(R.id.setFoto);
            im.setImageBitmap(thumbnail);
        }catch (Throwable t) {
            Log.e(APP, " ERROR AL SETEAR LA FOTO", t);
        }
        Log.d(APP, "XX");
    }

    private void setearFotoSeleccionada(@Nullable Intent data){
        Log.d(APP, "Clase Login, setearFotoSeleccionada");
        Uri uri = data.getData();
        try {
            Bitmap  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            ImageView imageView = findViewById(R.id.setFoto);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(APP, "XX");
    }
}
