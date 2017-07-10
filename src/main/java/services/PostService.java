package services;

import logica.Comentario;
import logica.Etiqueta;
import logica.Post;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    public void cargarDemo(){
        String uuid = UUID.randomUUID().toString();
        Set<Etiqueta> listEtiqueta  = new HashSet<>();
        listEtiqueta.add(EtiquetaServices.getInstancia().findEtiquetaByName("Memes"));
        listEtiqueta.add(EtiquetaServices.getInstancia().findEtiquetaByName("Funny"));
        Post post= new Post("Funny Meme Dump", "YUCA","/yucaImagenes/post1.png",1.40,findAllByUser("lenyluna"),"6 julio 2017",listEtiqueta);
        post.setListaComentario(new HashSet<Comentario>());
        post.setViews(20);
        post.setHash(uuid);
        crearEntidad(post);
        Set<Etiqueta> listEtiqueta2  = new HashSet<>();
        listEtiqueta2.add(EtiquetaServices.getInstancia().findEtiquetaByName("Madre"));
        listEtiqueta2.add(EtiquetaServices.getInstancia().findEtiquetaByName("Bar"));
        listEtiqueta2.add(EtiquetaServices.getInstancia().findEtiquetaByName("Romo"));
        Post post2 = new Post("Troleando a mami", "Batata","/yucaImagenes/post2.jpg",0.101,findAllByUser("zomgod"),"6 julio 2017",listEtiqueta2);
        post2.setListaComentario(new HashSet<Comentario>());
        post2.setViews(10);
        uuid = UUID.randomUUID().toString();
        post2.setHash(uuid);
        crearEntidad(post2);
    }
}
