package logica;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Leny96 on 4/7/2017.
 */
@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String contenido;
    private String fecha;
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario autor;
    @ManyToOne (targetEntity = Post.class)
    private Post post;
    private int upVote;
    private int downVote;

    public Comentario(){

    }

    public Comentario(String contenido, String fecha, Usuario autor, Post post) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
        this.post = post;
        this.upVote = 0;
        this.downVote=0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public int cantUpVote(){
        int cant=upVote;
        return cant+=1;
    }
    public int cantDownVote(){
        int cant=downVote;
        return cant+=1;
    }
}
