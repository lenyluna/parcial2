package services;

import logica.Typeline;
import logica.Usuario;

import java.util.UUID;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class UsuarioServices extends GestionDB<Usuario> {
    private static UsuarioServices instancia;

    public UsuarioServices() {
        super(Usuario.class);
    }

    public static UsuarioServices getInstancia(){
        if(instancia == null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }
    public void cargarDemo(){

        Usuario user = new Usuario("Leny","lenyluna","admin","leny0023@hotmail.com",Typeline.AdministradorGeneral,"DO");
        Usuario user1 =new Usuario("Starling","zomgod","root","starling.j@hotmail.com",Typeline.AdministradorGeneral,"DO");
        Usuario user2 = new Usuario("Anonimo","anonimo","","",Typeline.Anonimo,"");
        crearEntidad(user1);
        crearEntidad(user);
        crearEntidad(user2);
    }
}
