package basico.android.cctic.edu.cajascolor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class InicioMovilReceiver extends BroadcastReceiver {

    private static final String APP = "coloresAPP";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(APP, "Class InicioMovilReceiver, constructor");
        Log.d(APP, "Se ha encendido el dispositivo");
        // Queremos que se invoque cuando se inicie el teléfono
        // se tiene que ejecutar ante una determinada señal
        // para eso en el Manifest seteamos una opción
        //    <intent-filter>
        //        <action android:name="android.intent.action.BOOT_COMPLETED"></action>
        //    </intent-filter>
        // Para hacer esto necesitamos un cierto permiso, que también se declara en el manifest
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        // lanzamos un intent para que la aplicación arranque al encenderse el dispositivo
        /*
        Intent i = new Intent(context, Login.class);
        context.startActivity(i);
        */
        NotificaMensajeBuenosDias.lanzarNotificiacion(context);
        Log.d(APP, "XX");
    }
}
