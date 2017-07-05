package services;

import logica.Mensaje;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class MensajeServices extends GestionDB<Mensaje> {
    private static MensajeServices instancia;

    public MensajeServices() {
        super(Mensaje.class);
    }

    public static MensajeServices getInstancia(){
        if(instancia == null){
            instancia = new MensajeServices();
        }
        return instancia;
    }
}
