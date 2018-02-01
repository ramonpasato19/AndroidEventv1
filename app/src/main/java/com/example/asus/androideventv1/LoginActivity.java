package com.example.asus.androideventv1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.androideventv1.modelo.Asistente;
import com.example.asus.androideventv1.modelo.Login;
import com.example.asus.androideventv1.utilidades.ClienteRest;
import com.example.asus.androideventv1.utilidades.OnTaskCompleted;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private ClienteRest clienteRest;
    private int WS_INGRESAR = 1;
    private int WS_GUARDAR=2;
    private Login login = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        Button btnLogin = (Button) findViewById(R.id.btnIngresar);
        btnLogin.setOnClickListener(this);

        Button btnregistrar = (Button) findViewById(R.id.btnRegistrar);
        btnregistrar.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIngresar:
                loginUsuario();
                //Intent intent2 = new Intent(this,MainActivity.class);
                //startActivity(intent2);
                break;
            case R.id.btnRegistrar:
                Intent intent1 = new Intent(this,RegisterUser.class);
                startActivity(intent1);
                break;

            default:
                break;

        }

    }

    private void loginUsuario() {
        clienteRest = new ClienteRest(this);

        try {
            String url="http://35.227.80.6:80/ProjectEventos/srv/evento/login";

            Asistente l = new Asistente();

            l.setEmail(((EditText) findViewById(R.id.txtCorreoLogin)).getText().toString());
            l.setPassword(((EditText) findViewById(R.id.txtPasswordLogin)).getText().toString());


            clienteRest.doPost(url,l,WS_INGRESAR,true);
        }catch (Exception e){

            e.printStackTrace();
            showMensaje("Error Logeo");
        }

    }

    @Override
    public void onTaskCompleted(int idSolicitud) {

        if(idSolicitud==WS_INGRESAR) {
            System.out.println("Si entra aqui");
            if(!clienteRest.isCancelled()) {
                String cadenaNombre1, cadenaNombre2;
                String cadenaPassword1, cadenaPassword2;
                EditText txtCorreoL = (EditText) findViewById(R.id.txtCorreoLogin);
                EditText txtPasswordL = (EditText) findViewById(R.id.txtPasswordLogin);
                System.out.println("correo   " + txtCorreoL.toString());
                System.out.println("contrasela  " + txtPasswordL.toString());
                ArrayList<Asistente> usuario = (ArrayList<Asistente>) clienteRest.getResultList(Asistente.class);
                System.out.println("-----------------");
                System.out.println("usuario");
                for (int i = 0; i < usuario.size(); i++) {
                    cadenaNombre1 = txtCorreoL.getText().toString();
                    cadenaNombre2 = usuario.get(i).getEmail().toString();

                    cadenaPassword1 = txtPasswordL.getText().toString();
                    cadenaPassword2 = usuario.get(i).getPassword().toString();


                    System.out.println("ATRAPA TEXTO " + cadenaNombre1 + "USUARIO LISTA: " + cadenaNombre2);
                    // if (txtNombre.getText().equals(usuario.get(i).getUsername())&& txtNombre.getText().equals(usuario.get(i).getPassword()))
                    //if (txtNombre.getText().toString()==((usuario.get(i).getUsername().toString())))

                    //txtMens.setText("USUARIO INCORRECTO");
                    if ((cadenaNombre1.equals(cadenaNombre2)) && (cadenaPassword1.equals(cadenaPassword2))) {
                        System.out.println("USUARIO EXISTE");
                        Intent i2 = new Intent(this, MainActivity.class);
                        startActivity(i2);
                    }

                    System.out.println(i);
                }
            }
        }
    }

    /**
     * Permite mostrar un mensaje Toast en pantalla,
     * @param mensaje    Texto del mensaje a mostrar
     */
    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        toast.show();
    }
}

