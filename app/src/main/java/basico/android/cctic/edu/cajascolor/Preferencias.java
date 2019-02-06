package basico.android.cctic.edu.cajascolor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Preferencias {

    public final static String FILE = "preferencias";//nombre del fichero de preferences perfil.xml será
    private static String usuario;
    private static String foto;
    private static final String APP = "coloresAPP";
    private static final String claveUsuario = "usuario";
    private static final String claveFoto = "foto";

    public Preferencias(){

    }

    public static String getUsuario(Context context){
        Log.d(APP, "Class Preferencias, getUsuario");
        SharedPreferences ficheroDatos = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        usuario = ficheroDatos.getString(claveUsuario, "");
        Log.d(APP, "Se ha recogido el usuario \""+usuario+"\"");
        Log.d(APP, "XX");
        return usuario;
    }

    public static void setUsuario(String nombre, Context context){
        Log.d(APP, "Class Preferencias, setUsuario");
        usuario = nombre;
        SharedPreferences ficheroDatos = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ficheroDatos.edit();
        editor.putString(claveUsuario, usuario);
        editor.commit();
        Log.d(APP, "XX");
    }

    public static String getFoto(Context context){
        Log.d(APP, "Class Preferencias, getFoto");
        SharedPreferences ficheroDatos = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        foto = ficheroDatos.getString(claveFoto, "");
        Log.d(APP, "Se ha recogido la foto \""+foto+"\"");
        Log.d(APP, "XX");
        return foto;
    }

    public static void setFoto(String direccion, Context context){
        Log.d(APP, "Class Preferencias, setFoto");
        foto = direccion;
        SharedPreferences ficheroDatos = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ficheroDatos.edit();
        editor.putString(claveFoto, direccion);
        editor.commit();
        Log.d(APP, "XX");
    }
}
