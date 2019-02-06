package basico.android.cctic.edu.cajascolor;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class EjemploPermisosCuentas extends AppCompatActivity {

    // Ejemplo mucho más correcto de permisos en: https://github.com/Valexx55/ANDROID_CAM_23/blob/master/MiCamaraApp/app/src/main/java/com/example/vale/micamaraapp/MainActivity.java
    private final static int CODPERMISOS = 100;
    private final static String APP = "app_permisos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(APP, "Class MAinActivity, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] arrayPermisos = new String[2];
        arrayPermisos[0] = Manifest.permission.GET_ACCOUNTS;
        arrayPermisos[1] = Manifest.permission.READ_EXTERNAL_STORAGE;
        // Aquí pido los permisos, pero si el permiso ya está concedido no me lo pide
        ActivityCompat.requestPermissions(this, arrayPermisos, CODPERMISOS);
        Log.d(APP, "XX");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(APP, "Class MAinActivity, onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.d(APP, " Permiso concedido");
            Toast.makeText(this, "GRACIAS", Toast.LENGTH_LONG).show();
            obtenerCorreos();
        }else{
            Log.d(APP, " Permiso NO concedido");
            Toast.makeText(this, "NO TIENES PERMISOS", Toast.LENGTH_LONG).show();
            finish();
        }
        Log.d(APP, "XX");
    }

    private void obtenerCorreos(){
        //Obetenemos las cuentas de usuario y las mostramos
        Log.d(APP, "Class MAinActivity, obtenerCorreos");
        AccountManager accountManager = (AccountManager)getSystemService(ACCOUNT_SERVICE);
        Account[] cuentas = accountManager.getAccounts(); // si no hay permisos en el manifest esta línea falla. En el manifest hay que poner: <uses-permission android:name="android.permission.GET_ACCOUNTS"></uses-permission>
        for (Account cuenta : cuentas){
            Log.d(APP, " Nombre: "+cuenta.name);
            Log.d(APP, " Tipo " +cuenta.type);
            Log.d(APP, " ");
        }
        Log.d(APP, "XX");
    }
}
