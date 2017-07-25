package com.alphaplusoftgmail.picabumobil;

/**
 * Created by No. GP62 on 25/07/2017.
 */

public class Post {

    private int id;
    private String titulo;
    private String descripcion;
    private String urlimagen;
    private String hash;
    private String fecha;
    private String user;
    private int accesada;
    private int views;

    public Post() {
    }

    public Post(int id, String titulo, String descripcion, String urlimagen, String hash, String fecha, String user, int accesada, int views) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlimagen = urlimagen;
        this.hash = hash;
        this.fecha = fecha;
        this.user = user;
        this.accesada = accesada;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAccesada() {
        return accesada;
    }

    public void setAccesada(int accesada) {
        this.accesada = accesada;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
