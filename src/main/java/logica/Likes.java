package logica;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Leny96 on 24/7/2017.
 */
@Entity
public class Likes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario user;
    private Typeline2 opcion;
    public Likes(){

    }
    public Likes(Usuario user, Typeline2 opcion) {
        this.user = user;
        this.opcion = opcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Typeline2 getOpcion() {
        return opcion;
    }

    public void setOpcion(Typeline2 opcion) {
        this.opcion = opcion;
    }

}