package com.example.asus.androideventv1.modelo;

/**
 * clase entidad para referewcnais a una pelicula vista
 * Created by asus on 2/01/2018.
 */
/*
    esta clase es el pojo de Asistente que se utilizara para obetener los datos del registro
      del asitetne como el nombre email y password
 */
public class Asistente {
    /*
    este atributo srive como identififcador y y unico para el asistente o la persona que se registra y puede ir a un evento
     */
    private int codigo;
    /*
este atributo define el nombre de la persona
     */
    private String nombre;
    /*
este atributo define el id unico de cada persona o ciudadano
     */
    private String cedula;
    /*
este atributo es el numero de celular o casa para cualquier contacto
     */
    private String telefono;
    /*
este atributo es es el identificado y id unico de cada persona que es par el logueo de usuario  en la pagina de inicio
     */
    private String email;
    /*
este atributo sirve para tener referencia de la direccion de la persona
     */
    private String direccion;
    /*
este atributo es el el id o identificador para logueo de la pagina
     */
    private String usuario;
    /*
este atributo es la contraseña de logueo que le pertenece a la persona
     */
    private String password;
    /*
este atributo srive para diferecnai el rol que cumplirar en la pagina la persona ya sea como asministrador o como persona invitada
     */
    private int rol;
/*
metodos getter y setter para recuperar datos que actuan como clases pojo
los atributos que se usaran en esta clase seran los mismos que se uso en la clase pojo del proyecto eclipse que estan dentro del servidor de aplicaccaciones wildfly
 */

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Asistente{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                '}';
    }
}// fin de la clase Asistente que sirve como persona
