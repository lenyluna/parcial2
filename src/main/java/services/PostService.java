package services;

import logica.Post;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class PostService extends GestionDB<Post>  {

    private static PostService instancia;

    public PostService() {
        super(Post.class);
    }

    public static PostService getInstancia(){
        if(instancia == null){
            instancia = new PostService();
        }
        return instancia;
    }

}
