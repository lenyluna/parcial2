package services;

import logica.Etiqueta;


/**
 * Created by Leny96 on 5/7/2017.
 */
public class EtiquetaServices extends GestionDB<Etiqueta>{
    private static EtiquetaServices instancia;

    public EtiquetaServices() {
        super(Etiqueta.class);
    }

    public static EtiquetaServices getInstancia(){
        if(instancia == null){
            instancia = new EtiquetaServices();
        }
        return instancia;
    }

    public void cargarDemo(){
        crearEntidad(new Etiqueta("Memes"));
        crearEntidad(new Etiqueta("Funny"));
        crearEntidad(new Etiqueta("Madre"));
        crearEntidad(new Etiqueta("Bar"));
        crearEntidad(new Etiqueta("Romo"));
    }
}
