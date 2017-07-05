package logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Leny96 on 5/7/2017.
 */
@Entity
public class Mensaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String contenido;
    private Usuario emisor;
    private String userDestino;
    private String fecha;

    public Mensaje(long id, String contenido, Usuario emisor, String userDestino, String fecha) {
        this.id = id;
        this.contenido = contenido;
        this.emisor = emisor;
        this.userDestino = userDestino;
        this.fecha = fecha;
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

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public String getDestino() {
        return userDestino;
    }

    public void setDestino(String userDestino) {
        this.userDestino = userDestino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
