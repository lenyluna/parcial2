package main;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import logica.Typeline;
import logica.Usuario;
import services.ConfigDB;
import services.EtiquetaServices;
import services.PostService;
import services.UsuarioServices;
import spark.Request;
import spark.Spark;

import java.awt.*;
import java.io.StringWriter;
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
                    map.put("cargando","false");
                }else {
                    map.put("login", "false");
                    map.put("cargando","false");
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

        Spark.post("/signup/", (request, response) -> {
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
            try{
                Template formTemplate = configuration.getTemplate("templates/verPost.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("post",PostService.getInstancia().find(id));
                map.put("link",PostService.getInstancia().find(id).genLink());
                map.put("bw",PostService.getInstancia().find(id).anchoBanda());
                if(request.session().attribute(SESSION_NAME)!=null){
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username",user.getUsername());
                    map.put("tipoUser",user.getPrivilegio().name());
                    map.put("cargando","false");
                }else {
                    map.put("login", "false");
                    map.put("cargando","true");
                }
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
                    if(Long.parseLong(request.params("ubicar"))!=-1){
                        response.redirect("/verpost/"+Long.parseLong(request.params("ubicar")));
                    }else {
                        response.redirect("/");
                    }
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
