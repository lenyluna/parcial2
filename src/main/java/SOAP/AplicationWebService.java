package SOAP;

import api.PostFilter;
import logica.Post;
import logica.Usuario;
import services.PostService;
import services.UsuarioServices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@WebService
public class AplicationWebService {

    @WebMethod
    public List<PostFilter> getPosts(){

        return PostService.getInstancia().findallapi();
    }

    @WebMethod
    public List<PostFilter> getPostsByUsername(String username){

        return  PostService.getInstancia().findPostsByUsername(username);
    }

    @WebMethod
    public String crear_post(String titulo, String descripcion, String urlimagen){

        int cuca = 3;

        Usuario user = UsuarioServices.getInstancia().find(Long.valueOf("3"));
        String uuid = UUID.randomUUID().toString();
        Post post = new Post(titulo, descripcion, urlimagen, uuid, user);
        PostService.getInstancia().crearEntidad(post);
        return "ok created";
    }
}
