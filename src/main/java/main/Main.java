package main;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import logica.Comentario;
import logica.Post;
import logica.Typeline;
import logica.Usuario;
import services.*;
import spark.Request;
import spark.Spark;

import java.awt.*;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.halt;
import static spark.Spark.redirect;
import static spark.Spark.staticFileLocation;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class Main {
    private static final String COOKIE_NAME = "user_cookies" ;
    private static String SESSION_NAME= "id";

    public static void main(String[] args) {
        ConfigDB.getInstancia().startDb();
        staticFileLocation("/publico");
        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/");
        loadDemo();


        Spark.get("/", (request, response) -> {
            checkCOOKIES(request);
            StringWriter writer = new StringWriter();
            try{
                Template formTemplate = configuration.getTemplate("templates/index.ftl");
                Map<String, Object> map = new HashMap<>();

                if(request.session().attribute(SESSION_NAME)!=null){
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username",user.getUsername());
                    map.put("tipoUser",user.getPrivilegio().name());
                }else {
                    map.put("login", "false");
                }
                map.put("listPost",PostService.getInstancia().findAll());
                formTemplate.process(map, writer);
            }catch (Exception e){
                System.out.println(e.toString());
                e.printStackTrace();
                halt(500);
            }

            return writer;
        });
        Spark.get("/CrearPost/",(request, response) -> {
            StringWriter writer = new StringWriter();
            Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            Template formTemplate = configuration.getTemplate("templates/crearPost.ftl");
            Map<String, Object> map = new HashMap<>();
            map.put("username",user.getUsername());
            map.put("login", "true");
            map.put("tipoUser",user.getPrivilegio().name());
            formTemplate.process(map, writer);
            return writer;
        });

        Spark.post("/signup/guardando", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                String username = request.queryParams("username") != null ? request.queryParams("username") : "anonymous";
                String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";
                String nombre = request.queryParams("nombre") != null ? request.queryParams("nombre") : "unknown";
                String correo = request.queryParams("email") != null ? request.queryParams("nombre") : "unknown";
                UsuarioServices.getInstancia().crearEntidad(new Usuario(nombre,username,password,correo, Typeline.Normal));
                response.cookie(COOKIE_NAME,username,3600);
                List<Usuario> allUser = UsuarioServices.getInstancia().findAll();
                request.session().attribute(SESSION_NAME,allUser.get(allUser.size()-1).getId());

                response.redirect("/");
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.get("/verpost/:id", (request, response) -> {
            checkCOOKIES(request);
            StringWriter writer = new StringWriter();
            long id = Long.parseLong(request.params("id"));
            Post post =PostService.getInstancia().find(id);
            try{
                Template formTemplate = configuration.getTemplate("templates/verPost.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("post",post);
                map.put("link",post.genLink());
                map.put("bw",post.anchoBanda());
                map.put("listComent",post.getListaComentario());
                if(request.session().attribute(SESSION_NAME)!=null){
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username",user.getUsername());
                    map.put("tipoUser",user.getPrivilegio().name());
                }else {
                    map.put("login", "false");
                }
                post.setViews(post.cantViews());
                PostService.getInstancia().editar(post);
                formTemplate.process(map, writer);
            }catch (Exception e){
                e.printStackTrace();
            }

            return writer;
        });


        Spark.post("/login/:ubicar", (request, response) -> {
            StringWriter writer = new StringWriter();
            List<Usuario> allUsuarios = UsuarioServices.getInstancia().findAll();
            try {
                String username = request.queryParams("username") != null ? request.queryParams("username") : "anonymous";
                String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";
                List<Usuario> result = allUsuarios.stream()
                        .filter(item -> item.getUsername().equals(username))
                        .filter(item -> item.getPassword().equals(password))
                        .collect(Collectors.toList());

                if (result.isEmpty()) {
                    System.out.println("NINGUN USUARIO CON ESA COMBINACION DE PARAMETROS ");
                    response.redirect("/errorlogin/");
                } else {
                    System.out.println("LOGEADO CON EXITO");
                    response.cookie(COOKIE_NAME,username,3600);
                    request.session().attribute(SESSION_NAME,result.get(0).getId());
                    if(Long.parseLong(request.params("ubicar"))!=-1){
                        response.redirect("/verpost/"+Long.parseLong(request.params("ubicar")));
                    }else {
                        response.redirect("/");
                    }
                }

            } catch (Exception e) {
                response.redirect("/");
            }
            return writer;
        });

        Spark.get("/logout", (request, response) -> {
            request.session().removeAttribute(SESSION_NAME);
            response.redirect("/");
            return null;
        });
        Spark.get("/errorlogin/", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/login2.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("login", "false");
                formTemplate.process(map, writer);
            }catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });
        Spark.get("/signup/", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/signup.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("login", "false");
                formTemplate.process(map, writer);
            }catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.post("/verpost/:id/comentario",(request, response) ->{
            StringWriter writer = new StringWriter();
            String comentario = request.queryParams("comentario");
            long id = Long.parseLong(request.params("id"));
            Post post = PostService.getInstancia().find(id);
            Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            if(user!=null) {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
                Comentario com = new Comentario(comentario,format.format(date).toString(),user, post);
                post.getListaComentario().add(com);
                ComentarioService.getInstancia().crearEntidad(com);
                PostService.getInstancia().editar(post);
            }
            response.redirect("/verpost/"+id);
            return null;
        });

        Spark.get("/upVote/:id", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            Comentario com = ComentarioService.getInstancia().find(id);
            com.setUpVote(com.cantUpVote());
            System.out.println("que lo que"+com.getUpVote());
            ComentarioService.getInstancia().editar(com);
            response.redirect("/verpost/"+com.getPost().getId());
            return null;
        });
        Spark.get("/downVote/:id", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            Comentario com = ComentarioService.getInstancia().find(id);
            com.setDownVote(com.cantDownVote());
            ComentarioService.getInstancia().editar(com);
            response.redirect("/verpost/"+com.getPost().getId());
            return null;
        });
    }

    private static void loadDemo(){
        if(UsuarioServices.getInstancia().findAll().size()==0) {
            UsuarioServices.getInstancia().cargarDemo();
        }
        if(EtiquetaServices.getInstancia().findAll().size()==0){
            EtiquetaServices.getInstancia().cargarDemo();
        }

        if(PostService.getInstancia().findAll().size()==0){
            PostService.getInstancia().cargarDemo();
        }

    }

    private static void checkCOOKIES(Request req) {
        if (req.session().attribute(SESSION_NAME) == null) {
            Map<String, String> cookies = req.cookies();
            if (cookies.containsKey(COOKIE_NAME)) {
                System.out.println("que lo que con que lo que ");
                System.out.println("COOKIE ENCONTRADA" + cookies.get(COOKIE_NAME));
                req.session().attribute(SESSION_NAME, cookies.get(COOKIE_NAME));
            }
        }
    }
}
