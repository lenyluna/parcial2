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
    private String descripcion;
    private Image img;
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario user;
    @OneToMany(targetEntity = Etiqueta.class,fetch = FetchType.EAGER)
    private Set<Etiqueta> listaEtiqueta;
    @OneToMany(targetEntity = Comentario.class,mappedBy = "post",fetch = FetchType.EAGER)
    private Set<Comentario> listaComentario;
    private int cantVeces;
    private int views;
    private int cantAnchoDeBanda;
    private String link;

    public Post(String descripcion, Image img, Usuario user, Set<Etiqueta> listaEtiqueta) {
        this.descripcion = descripcion;
        this.img = img;
        this.user = user;
        this.listaEtiqueta = listaEtiqueta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
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

    public int getCantVeces() {
        return cantVeces;
    }

    public void setCantVeces(int cantVeces) {
        this.cantVeces = cantVeces;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCantAnchoDeBanda() {
        return cantAnchoDeBanda;
    }

    public void setCantAnchoDeBanda(int cantAnchoDeBanda) {
        this.cantAnchoDeBanda = cantAnchoDeBanda;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
