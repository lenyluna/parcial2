package logica;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Leny96 on 4/7/2017.
 */
@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titulo;
    private String descripcion;
    private String urlimagen;
    private int views;
    private double imgsize;
    private String fecha;
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario user;
    @OneToMany(targetEntity = Etiqueta.class,fetch = FetchType.EAGER)
    private Set<Etiqueta> listaEtiqueta;
    @OneToMany(targetEntity = Comentario.class,mappedBy = "post",fetch = FetchType.EAGER)
    private Set<Comentario> listaComentario;

    public Post(String titulo, String descripcion, String urlimagen, double imgsize, Usuario user,String fecha, Set<Etiqueta> listaEtiqueta) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlimagen = urlimagen;
        this.imgsize = imgsize;
        this.user = user;
        this.listaEtiqueta = listaEtiqueta;
        this.fecha = fecha;
    }

    public Post(){

    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public double getImgsize() {
        return imgsize;
    }

    public void setImgsize(double imgsize) {
        this.imgsize = imgsize;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Set<Etiqueta> getListaEtiqueta() {
        return listaEtiqueta;
    }

    public void setListaEtiqueta(Set<Etiqueta> listaEtiqueta) {
        this.listaEtiqueta = listaEtiqueta;
    }

    public Set<Comentario> getListaComentario() {
        return listaComentario;
    }

    public void setListaComentario(Set<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
    }

    public String genLink(){
        String link="";

        return link="/local/";
    }

    public Double anchoBanda(){
        double total=0;
        return total= views*imgsize;
    }

    public int cantViews(){
        int cant=views;
        return views+=1;
    }
}
