package logica;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leny96 on 4/7/2017.
 */
@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private String name;
    private String username;
    private String password;
    private String correo;
    private Typeline privilegio;
    @OneToMany(targetEntity = Mensaje.class,mappedBy = "emisor",fetch = FetchType.EAGER)
    private Set<Mensaje> listaMensaje;

    public Usuario(String name, String username, String password, String correo, Typeline privilegio) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.privilegio = privilegio;
        this.listaMensaje = new HashSet<>();
    }

    public Usuario(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Typeline getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(Typeline privilegio) {
        this.privilegio = privilegio;
    }

    public Set<Mensaje> getListaMensaje() {
        return listaMensaje;
    }

    public void setListaMensaje(Set<Mensaje> listaMensaje) {
        this.listaMensaje = listaMensaje;
    }
}
