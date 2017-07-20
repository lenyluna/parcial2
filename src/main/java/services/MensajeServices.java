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

    public void cargarDemo(){

        Mensaje mensa = new Mensaje("klk menol","2","lenyluna","today");
        Mensaje mensa2 = new Mensaje("todo bien de bien","1","zomgod","today");

        crearEntidad(mensa);
        crearEntidad(mensa2);

    }
}
