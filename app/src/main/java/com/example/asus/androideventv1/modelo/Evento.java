package com.example.asus.androideventv1.modelo;

/**
 * clase entidad de evento
 * Created by asus on 2/01/2018.
 */
/*
esta clase es la principal ya que va a guardar los datos principales de los eventos populares que va haber en la parte de azuay
ademas que tiene lo smismo atributos que en el servidor de aplicaciones
 */

public class Evento {


/*
es un identificador del evento y unico
 */
    private int codigo;
    /*
    este srive pára diferenciar la fecha de cada evento a realizarse
     */
    private String fecha;

    /*
este nos una un a descripcion de que se trata el evento si privado publico o beneficiencia
 */
    private String descripcion;
    /*
este atributo nos sirve para saber que persona esta acargo para contactos internos o prepuntas internas
 */
    private String representante;
    /*
 este atributo parasa ber la hora especifica del evento a realizarse
  */
    private String hora;
    /*
 este atributo para saber el lugar donde se realizara el evento
  */
    private String lugar;
    /*
 este atributo es para ser mas concretas en la ubicacion haciendo uso de tegonologias como GPS y mas exacto
  */
    private String coordenadas;
    /*
 este atributo nos sirve para poder diferencias en que categorias se clasifican determinados eventos populares
  */
    private String categoria;
    /*
 este atributo nos sirve para saber si va exitir concursos como danza, juegos populares etc...
  */
    private String concurso;

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getConcurso() {
        return concurso;
    }

    public void setConcurso(String concurso) {
        this.concurso = concurso;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "codigo=" + codigo +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", representante='" + representante + '\'' +
                ", hora='" + hora + '\'' +
                ", lugar='" + lugar + '\'' +
                ", coordenadas='" + coordenadas + '\'' +
                ", categoria='" + categoria + '\'' +
                ", concurso='" + concurso + '\'' +
                '}';
    }
}// fin de la clase evento
