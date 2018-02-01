package com.example.asus.androideventv1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.asus.androideventv1.modelo.Evento;
import com.example.asus.androideventv1.modelo.Respuesta;
import com.example.asus.androideventv1.utilidades.ClienteRest;
import com.example.asus.androideventv1.utilidades.OnTaskCompleted;

import java.util.List;
import com.example.asus.androideventv1.CustomListAdapter;
import com.example.asus.androideventv1.AppController;
import com.example.asus.androideventv1.Event;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
public class eventoActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    static final int LONG_DELAY = 3500; // 3.5 seconds
    private ClienteRest clienteRest;
    private int WS_CONSULTA = 1;
    private Evento pelicula = null;           //Objeto Pelicula de OMDBApi para refernciar pelicula mostrada.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);

        Button btn2 = (Button) findViewById(R.id.btnListar);
        btn2.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnListar:
                listarEventos();
                break;
            default:
                break;
        }
    }

    private void listarEventos() {
        clienteRest = new ClienteRest(this);

        try{
            String url = "http://35.227.80.6:80/ProjectEventos/srv/evento/listareventos";
            Evento e=new Evento();



            clienteRest.doGet(url, null, WS_CONSULTA, true);
        }catch(Exception e){
            showMensaje("Error al consultar ");
            e.printStackTrace();
        }

    }


    //String url = "http://192.168.43.172:8080/ProjectEventos/srv/evento/login";


    @Override
    public void onTaskCompleted(int idSolicitud) {
        startSplash();
        if(idSolicitud == WS_CONSULTA){
            String representante, descripcion, fecha, lugar,categoria;


            //ArrayList<Evento> eve = (ArrayList<Evento>) clienteRest.getResultList(Evento.class);
            List<Evento> eventos = clienteRest.getResultList(Evento.class);
            System.out.println("-----------------");
            System.out.println("usuario");
            //for (int i = 0; i < eve.size(); i++) {
            for(Evento evento : eventos){
               // this.pelicula = pelicula;
               // ((TextView) findViewById(R.id.txtActors)).setText(pelicula.getRepresentante());
               // ((TextView) findViewById(R.id.txtTitle)).setText(pelicula.getDescripcion());
               // ((TextView) findViewById(R.id.txtPlot)).setText(pelicula.getFecha());
               // ((TextView) findViewById(R.id.txtGenre)).setText(pelicula.getLugar());
                //((TextView) findViewById(R.id.txtRuntime)).setText(pelicula.getCategoria());

                    Log.i("TEST", evento.toString());
               // representante = eve.get(i).getRepresentante().toString();
                //descripcion = eve.get(i).getRepresentante().toString();
                //fecha = eve.get(i).getRepresentante().toString();
                //lugar = eve.get(i).getRepresentante().toString();
                //categoria = eve.get(i).getRepresentante().toString();


                // if (txtNombre.getText().equals(usuario.get(i).getUsername())&& txtNombre.getText().equals(usuario.get(i).getPassword()))
                //if (txtNombre.getText().toString()==((usuario.get(i).getUsername().toString())))



                    //Intent i2 = new Intent(this, MainActivity.class);
                   // startActivity(i2);

                showMensaje("Listado Eventos:            " + eventos.toString()+"=============");
                //System.out.println(representante + descripcion+fecha+lugar+categoria);
            }

            startSplash();
        }
        }
/*
    private void mostrarPelicula(Evento pelicula) {
        this.pelicula = pelicula;
        ((TextView) findViewById(R.id.txtActors)).setText(pelicula.getRepresentante());
        ((TextView) findViewById(R.id.txtTitle)).setText(pelicula.getDescripcion());
        ((TextView) findViewById(R.id.txtPlot)).setText(pelicula.getFecha());
        ((TextView) findViewById(R.id.txtGenre)).setText(pelicula.getLugar());
        ((TextView) findViewById(R.id.txtRuntime)).setText(pelicula.getCategoria());


    }

*/




    /**
     * Permite mostrar un mensaje Toast en pantalla,
     * @param mensaje    Texto del mensaje a mostrar
     */
/*
    private void showMensaje(String mensaje){
        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG );
        toast.show();
        startSplash();

    }*/
    private Toast mToastToShow;
    private void showMensaje(String mensaje){
        int toastDurationInMilliSeconds = 15000;
         mToastToShow = Toast.makeText(this, mensaje, Toast.LENGTH_LONG );


        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }
            public void onFinish() {
                mToastToShow.cancel();
            }
        };

        // Show the toast and starts the countdown
        mToastToShow.show();
        toastCountDown.start();
        startSplash();

    }

    private void startSplash(){
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try{
                    Thread.sleep(20000);
                } catch (Exception e){
                    e.printStackTrace();
                } finally{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            iniciarAplicacion();
                        }
                    });
                }
            }
        }).start();
    }

    private synchronized void iniciarAplicacion(){
        Intent i = new Intent(this, eventoActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //cierra cola de actividades
        startActivity(i);
        finish();
    }


    public void showToast() {
        // Set the toast and duration


        // Set the countdown to display the toast

    }

    }
