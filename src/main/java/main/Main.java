package main;

import logica.Usuario;
import services.ConfigDB;
import services.UsuarioServices;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class Main {

    public static void main(String[] args) {
        ConfigDB.getInstancia().startDb();
        loadDemo();

    }

    private static void loadDemo(){
        UsuarioServices.getInstancia().cargarDemo();
    }
}
