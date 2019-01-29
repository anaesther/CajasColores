package basico.android.cctic.edu.cajascolor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Preferencias {

    public final static String FILE = "preferencias";//nombre del fichero de preferences perfil.xml será
    private static String usuario;
    private static final String APP = "coloresAPP";
    private static final String claveUsuario = "usuario";

    public Preferencias(){

    }

    public static String getUsuario(Context context){
        SharedPreferences ficheroDatos = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        usuario = ficheroDatos.getString(claveUsuario, "");
        Log.d(APP, "Se ha recogido el usuario \""+usuario+"\"");
        return usuario;
    }

    public static void setUsuario(String nombre, Context context){
        Log.d(APP, "método setUsuario en Preferencias");
        usuario = nombre;
        SharedPreferences ficheroDatos = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ficheroDatos.edit();
        editor.putString(claveUsuario, usuario);
        editor.commit();
    }
}
