package logica;

/**
 * Created by Leny96 on 4/7/2017.
 */
public class Usuario {
    private long id ;
    private String name;
    private String username;
    private String password;
    private boolean administrador;

    public Usuario(String name, String username, String password, boolean administrador) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.administrador = administrador;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdministrador() {
        return administrador;
    }
}
