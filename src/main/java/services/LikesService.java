package services;

import logica.Likes;

/**
 * Created by Leny96 on 24/7/2017.
 */
public class LikesService extends GestionDB<Likes> {
    private static LikesService instancia;

    public LikesService() {
        super(Likes.class);
    }

    public static LikesService getInstancia() {
        if (instancia == null) {
            instancia = new LikesService();
        }
        return instancia;
    }
}