package com.example.asus.androideventv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.androideventv1.modelo.Asistente;
import com.example.asus.androideventv1.modelo.Respuesta;
import com.example.asus.androideventv1.utilidades.ClienteRest;
import com.example.asus.androideventv1.utilidades.OnTaskCompleted;


public class RegisterUser extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private ClienteRest clienteRest;
    private int WS_GUARDAR = 1;
    private int WS_CONSULTA=2;
    //private Asistente persona = new Asistente();

    EditText nombre;
    EditText rol;
    EditText cedula;
    EditText email;
    EditText password;
    EditText telefono;
    EditText direccion;
    EditText usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);



        nombre = (EditText) findViewById(R.id.editname);
        email = (EditText) findViewById(R.id.editemail);
        password = (EditText) findViewById(R.id.editpassword);


        Button btnRegistro1 = (Button) findViewById(R.id.btnregistrar1);
        btnRegistro1.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnregistrar1:
                guardaAsistente();
                //Intent intent = new Intent(this,LoginActivity.class);
                //startActivity(intent);
                break;

            default:
                break;

        }

    }

    private void guardaAsistente() {
        clienteRest = new ClienteRest(this);

        try {
            //String url = "http://192.168.0.140:8080/ProjectEventos/srv/evento/saveAsistente";
            String url = "http://35.227.80.6:80/ProjectEventos/srv/evento/saveAsistente";


            Asistente p = new Asistente();

            p.setNombre(((EditText)findViewById(R.id.editname)).getText().toString());
            p.setEmail(((EditText)findViewById(R.id.editemail)).getText().toString());
            p.setPassword(((EditText)findViewById(R.id.editpassword)).getText().toString());

            p.setCedula(null);
            p.setTelefono(null);
            p.setRol(0);
            p.setUsuario(null);
            p.setDireccion(null);

            clienteRest.doPost(url,p,WS_GUARDAR,true);
        }catch (Exception e){
            showMensaje("Error Consulta");
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(int idSolicitud) {
        if(idSolicitud == WS_GUARDAR){
            if(!clienteRest.isCancelled()){
                Respuesta respuesta = clienteRest.getResult(Respuesta.class);

                showMensaje("Guardado:..... " + respuesta.getMensaje());
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

}//fin clase RegistroActivity
