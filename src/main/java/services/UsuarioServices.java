package services;

import logica.Typeline;
import logica.Usuario;

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
        crearEntidad(new Usuario("Leny","lenyluna","admin","leny0023@hotmail.com", Typeline.AdministradorGeneral));
        crearEntidad(new Usuario("Starling","zomgod","root","starling.j@hotmail.com", Typeline.AdministradorGeneral));
    }
}