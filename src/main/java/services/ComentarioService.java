package services;

import logica.Comentario;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class ComentarioService extends GestionDB<Comentario> {
    private static ComentarioService instancia;
    public ComentarioService() {
        super(Comentario.class);
    }

    public static ComentarioService getInstancia(){
        if(instancia == null){
            instancia = new ComentarioService();
        }
        return instancia;
    }
}
