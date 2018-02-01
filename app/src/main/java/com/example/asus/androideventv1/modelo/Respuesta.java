package com.example.asus.androideventv1.modelo;

/**
 * Created by asus on 2/01/2018.
 */

/*
esta clase sirve para saber el tiempo de respuesta y el estado de los metodos para ver si estan funcionando para lo cual se utilizara en los webs eervices
ta que la respuesta saldra en la consola para saber eñ estado
 */
public class Respuesta {
    /*
    este atributo sirve como identificados de la respuesta del estado del metodo
     */
    private int codigo;
    /*
    este atributo srive como mensaje de texto para saber en la consola
     */
    private String mensaje;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "codigo=" + codigo +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}

