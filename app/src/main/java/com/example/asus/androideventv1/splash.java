package com.example.asus.androideventv1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startSplash();

    }

    private void startSplash(){
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try{
                    Thread.sleep(5000);
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


    /**
     * Llamada a actividad principal de la aplicación una vez finalizado el Splash.
     */
    private synchronized void iniciarAplicacion(){
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //cierra cola de actividades
        startActivity(i);
        finish();
    }


}
