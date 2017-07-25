package SOAP;

import api.PostFilter;
import services.PostService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

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
}
