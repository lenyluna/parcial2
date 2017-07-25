package main;

import SOAP.SOAPstart;
import api.JsonTransformer;
import api.recursos;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import javafx.geometry.Pos;
import logica.*;
import services.*;
import spark.Request;
import spark.Spark;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static spark.Spark.*;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class Main {
    private static final String COOKIE_NAME = "user_cookies";
    private static String SESSION_NAME = "id";

    public static recursos resource;


    public static void main(String[] args) throws Exception {
        ConfigDB.getInstancia().startDb();
        staticFileLocation("/publico");
        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/");
        loadDemo();
        JsonTransformer jsonTransformer = new JsonTransformer();
        SOAPstart.init();


        Spark.before("/mensaje/*", (request, response) -> {
            Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            if (user.getId() == 3) {
                System.out.println(user.getId() + "ESTE ES EL JODIDO ID!!!!");
                response.redirect("/");
            }
        });


        Spark.get("/", (request, response) -> {
            checkCOOKIES(request);
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/index.ftl");
                Map<String, Object> map = new HashMap<>();

                if (request.session().attribute(SESSION_NAME) != null) {
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username", user.getUsername());
                    map.put("tipoUser", user.getPrivilegio().name());
                } else {
                    map.put("login", "false");
                }
                map.put("listPost", PostService.getInstancia().findAll());
                formTemplate.process(map, writer);
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                halt(500);
            }

            return writer;
        });

        path("/api", () -> {
            before("/*", (q, a) -> System.out.println("ENTRANDO AL API"));
            path("/post", () -> {
                get("/all", (request, response) -> {
                    try {
                        return PostService.getInstancia().findallapi();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }, jsonTransformer);
                get("/:nombre", (request, response) -> {
                    Object writer = new Object();

                    try {
                        writer = PostService.getInstancia().findPostsByUsername(request.params("nombre"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return writer;
                }, jsonTransformer);
                post("/new", "multipart/form-data", (request, response) -> {
                    StringWriter writer = new StringWriter();
                    Usuario user = null;
                    try {
                        if (request.session().attribute(SESSION_NAME) != null) {
                            user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                        } else {
                            user = UsuarioServices.getInstancia().findAllByUser("anonimo");
                            response.cookie(COOKIE_NAME, user.getUsername(), 3600);
                            request.session().attribute(SESSION_NAME, user.getId());
                        }
                        String location = "image";          // the directory location where files will be stored
                        long maxFileSize = 100000000;       // the maximum size allowed for uploaded files
                        long maxRequestSize = 100000000;    // the maximum size allowed for multipart/form-data requests
                        int fileSizeThreshold = 1024;       // the size threshold after which files will be written to disk

                        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                                location, maxFileSize, maxRequestSize, fileSizeThreshold);
                        request.raw().setAttribute("org.eclipse.jetty.multipartConfig",
                                multipartConfigElement);

                        Collection<Part> parts = request.raw().getParts();
                        String titulo = request.raw().getParameter("titulo") != null ? request.raw().getParameter("titulo") : "unknown";
                        String descripcion = request.raw().getParameter("descripcion") != null ? request.raw().getParameter("descripcion") : "unknown";
                        String etiquetas = request.raw().getParameter("eti");
                        Set<Etiqueta> listEtiqueta = new HashSet<>();
                        if (etiquetas.length() != 0) {
                            String etiqueta[] = etiquetas.split(",");
                            System.out.println("wea asjjas");
                            for (int i = 0; i < etiqueta.length; i++) {
                                Etiqueta et = EtiquetaServices.getInstancia().findEtiquetaByName(etiqueta[i]);
                                if (et == null) {
                                    Etiqueta et2 = new Etiqueta(etiqueta[i]);
                                    EtiquetaServices.getInstancia().crearEntidad(et2);
                                    listEtiqueta.add(et2);
                                } else {
                                    listEtiqueta.add(et);
                                }
                            }
                            for (Etiqueta et : listEtiqueta) {
                                EtiquetaServices.getInstancia().crearEntidad(et);
                            }
                        }
                        String fName = request.raw().getPart("img").getSubmittedFileName();
                        double fsize = convertir(request.raw().getPart("img").getSize());
                        System.out.println("-----------------------------------");
                        System.out.println("Title: " + request.raw().getParameter("title"));
                        System.out.println("File: " + fName);

                        Part uploadedFile = request.raw().getPart("img");

                        File theDir = new File("src/main/resources/publico/yucaImagenes/");

// if the directory does not exist, create it
                        if (!theDir.exists()) {
                            System.out.println("creating directory: " + theDir.getName());
                            boolean result = false;

                            try {
                                theDir.mkdir();
                                result = true;
                            } catch (SecurityException se) {
                                //handle it
                            }
                            if (result) {
                                System.out.println("DIR created again");
                            }
                        }
                        Path out = Paths.get(theDir + "/" + fName);
                        try (final InputStream in = uploadedFile.getInputStream()) {
                            Files.copy(in, out);
                            uploadedFile.delete();
                        }
                        // cleanup
                        multipartConfigElement = null;
                        parts = null;
                        uploadedFile = null;


                        //----------------------------------------------------------
                        Date date = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

                        Post post = new Post(titulo, descripcion, "/yucaImagenes/" + fName, fsize, user, format.format(date), listEtiqueta);
                        String uuid = UUID.randomUUID().toString();
                        post.setHash(uuid);
                        PostService.getInstancia().crearEntidad(post);
                        response.redirect("OK");

                    } catch (Exception e) {
                        e.printStackTrace();
                        response.redirect("Error");
                    }

                    return null;


                });

            });

        });

        Spark.get("/CrearPost/", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Usuario user = null;
                if (request.session().attribute(SESSION_NAME) != null) {
                    user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                }
                Template formTemplate = configuration.getTemplate("templates/crearPost.ftl");
                Map<String, Object> map = new HashMap<>();
                if (user != null) {
                    map.put("username", user.getUsername());
                    map.put("login", "true");
                    map.put("tipoUser", user.getPrivilegio().name());
                } else {
                    map.put("login", "false");
                }
                map.put("modificar", "false");
                formTemplate.process(map, writer);

            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.post("/CrearPost/guardando", "multipart/form-data", (request, response) -> {
            StringWriter writer = new StringWriter();
            Usuario user = null;
            try {
                if (request.session().attribute(SESSION_NAME) != null) {
                    user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                } else {
                    user = UsuarioServices.getInstancia().findAllByUser("anonimo");
                    response.cookie(COOKIE_NAME, user.getUsername(), 3600);
                    request.session().attribute(SESSION_NAME, user.getId());
                }

                String location = "image";          // the directory location where files will be stored
                long maxFileSize = 100000000;       // the maximum size allowed for uploaded files
                long maxRequestSize = 100000000;    // the maximum size allowed for multipart/form-data requests
                int fileSizeThreshold = 1024;       // the size threshold after which files will be written to disk

                MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                        location, maxFileSize, maxRequestSize, fileSizeThreshold);
                request.raw().setAttribute("org.eclipse.jetty.multipartConfig",
                        multipartConfigElement);

                Collection<Part> parts = request.raw().getParts();
                String titulo = request.raw().getParameter("titulo") != null ? request.raw().getParameter("titulo") : "unknown";
                String descripcion = request.raw().getParameter("descripcion") != null ? request.raw().getParameter("descripcion") : "unknown";
                String etiquetas = request.raw().getParameter("eti");
                Set<Etiqueta> listEtiqueta = new HashSet<>();
                if (etiquetas.length() != 0) {
                    String etiqueta[] = etiquetas.split(",");
                    System.out.println("wea asjjas");
                    for (int i = 0; i < etiqueta.length; i++) {
                        Etiqueta et = EtiquetaServices.getInstancia().findEtiquetaByName(etiqueta[i]);
                        if (et == null) {
                            Etiqueta et2 = new Etiqueta(etiqueta[i]);
                            EtiquetaServices.getInstancia().crearEntidad(et2);
                            listEtiqueta.add(et2);
                        } else {
                            listEtiqueta.add(et);
                        }
                    }
                    for (Etiqueta et : listEtiqueta) {
                        EtiquetaServices.getInstancia().crearEntidad(et);
                    }
                }
                String fName = request.raw().getPart("img").getSubmittedFileName();
                double fsize = convertir(request.raw().getPart("img").getSize());
                System.out.println("-----------------------------------");
                System.out.println("Title: " + request.raw().getParameter("title"));
                System.out.println("File: " + fName);

                Part uploadedFile = request.raw().getPart("img");

                File theDir = new File("src/main/resources/publico/yucaImagenes/");

// if the directory does not exist, create it
                if (!theDir.exists()) {
                    System.out.println("creating directory: " + theDir.getName());
                    boolean result = false;

                    try {
                        theDir.mkdir();
                        result = true;
                    } catch (SecurityException se) {
                        //handle it
                    }
                    if (result) {
                        System.out.println("DIR created");
                    }
                }
                Path out = Paths.get(theDir + "/" + fName);
                try (final InputStream in = uploadedFile.getInputStream()) {
                    Files.copy(in, out);
                    uploadedFile.delete();
                }
                // cleanup
                multipartConfigElement = null;
                parts = null;
                uploadedFile = null;


                //----------------------------------------------------------
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");

                Post post = new Post(titulo, descripcion, "/yucaImagenes/" + fName, fsize, user, format.format(date), listEtiqueta);
                String uuid = UUID.randomUUID().toString();
                post.setHash(uuid);
                PostService.getInstancia().crearEntidad(post);
                response.redirect("/");

            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }

            return null;


        });

        Spark.post("/signup/guardando", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                String username = request.queryParams("username") != null ? request.queryParams("username") : "anonymous";
                String password = request.queryParams("password") != null ? request.queryParams("password") : "unknown";
                String nombre = request.queryParams("name") != null ? request.queryParams("name") : "unknown";
                String correo = request.queryParams("email") != null ? request.queryParams("email") : "unknown";
                UsuarioServices.getInstancia().crearEntidad(new Usuario(nombre, username, password, correo, Typeline.Normal));
                response.cookie(COOKIE_NAME, username, 3600);
                List<Usuario> allUser = UsuarioServices.getInstancia().findAll();
                request.session().attribute(SESSION_NAME, allUser.get(allUser.size() - 1).getId());

                response.redirect("/");
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.get("/verpost/:id/:countV", (request, response) -> {
            checkCOOKIES(request);
            StringWriter writer = new StringWriter();
            long id = Long.parseLong(request.params("id"));
            String countV = request.params("countV");
            Post post = PostService.getInstancia().find(id);
            try {
                Template formTemplate = configuration.getTemplate("templates/verPost.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                map.put("link", post.genLink());
                if (countV.equalsIgnoreCase("true")) {
                    post.setViews(post.cantViews());
                    PostService.getInstancia().editar(post);
                }
                map.put("bw", post.anchoBanda());
                map.put("listComent", post.getListaComentario());
                map.put("accesada", post.getAccesada());
                if (request.session().attribute(SESSION_NAME) != null) {
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username", user.getUsername());
                    map.put("tipoUser", user.getPrivilegio().name());
                    if (post.getUser() == user) {
                        map.put("modificar", "true");
                    } else {
                        map.put("modificar", "false");
                    }
                } else {
                    map.put("login", "false");
                }
                formTemplate.process(map, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return writer;
        });

        Spark.get("/mensaje/:emisor/:receptor", (request, response) -> {
            checkCOOKIES(request);
            StringWriter writer = new StringWriter();

            try {
                String emisor = request.params("emisor");
                String receptor = request.params("receptor");
                //   Post post = PostService.getInstancia().findH(id);

                Template formTemplate = configuration.getTemplate("templates/mensajes.ftl");
                Map<String, Object> map = new HashMap<>();
                // map.put("listComent", MensajeServices.getInstancia().findAllChat(emisor,receptor));
                if (request.session().attribute(SESSION_NAME) != null) {
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username", user.getUsername());
                    map.put("name", user.getName());
                    map.put("tipoUser", user.getPrivilegio().name());
                    map.put("emisor", emisor);
                    map.put("receptor", receptor);
                    // System.out.println("tama;o"+MensajeServices.getInstancia().findAllChat("2",receptor).size());
                } else {
                    map.put("login", "false");
                }
                formTemplate.process(map, writer);
            } catch (Exception e) {
                e.printStackTrace();

                response.redirect("/");
            }


            return writer;
        });

        Spark.get("/men_show/:emisor/:receptor", (request, response) -> {
            checkCOOKIES(request);
            StringWriter writer = new StringWriter();

            try {
                String emisor = request.params("emisor");
                String receptor = request.params("receptor");
                //   Post post = PostService.getInstancia().findH(id);

                Template formTemplate = configuration.getTemplate("templates/contenido_chat.ftl");
                Map<String, Object> map = new HashMap<>();
                if (request.session().attribute(SESSION_NAME) != null) {
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username", user.getUsername());
                    map.put("name", user.getName());
                    map.put("tipoUser", user.getPrivilegio().name());
                    map.put("listaMensajes", MensajeServices.getInstancia().findAllChat("" + user.getId(), receptor));
                    //  System.out.println("tama;o"+MensajeServices.getInstancia().findAllChat(""+user.getId(),receptor).size());
                } else {
                    map.put("login", "false");
                }
                formTemplate.process(map, writer);
            } catch (Exception e) {
                e.printStackTrace();

                response.redirect("/");
            }

            /*
            <#list listaMensajes as mensaje>

    <#if mensaje.emisor == username >
        <p>YUCA PAPA</p>
    </#if>

</#list>
             */

            return writer;
        });

        Spark.get("/postByEtiqueta/:id/", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            StringWriter writer = new StringWriter();
            Usuario user = null;
            if (request.session().attribute(SESSION_NAME) != null) {
                user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            }
            ArrayList<Post> listPost = new ArrayList<Post>();
            List<Post> list = PostService.getInstancia().findAll();
            for (Post post : list) {
                for (Etiqueta eti : post.getListaEtiqueta()) {
                    if (eti.getId() == id) {
                        listPost.add(post);
                    }
                }
            }
            try {
                Template formTemplate = configuration.getTemplate("templates/postByEti.ftl");
                Map<String, Object> map = new HashMap<>();
                if (user != null) {
                    map.put("username", user.getUsername());
                    map.put("login", "true");
                    map.put("tipoUser", user.getPrivilegio().name());
                } else {
                    map.put("login", "false");
                }
                map.put("etiqueta", EtiquetaServices.getInstancia().find(id).getName());
                map.put("listPost", listPost);
                formTemplate.process(map, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.get("/post/:id", (request, response) -> {
            checkCOOKIES(request);
            StringWriter writer = new StringWriter();
            try {
                String id = request.params("id");
                Post post = PostService.getInstancia().findH(id);
                post.setAccesada(post.cantAcce());
                PostService.getInstancia().editar(post);
                Template formTemplate = configuration.getTemplate("templates/verPost.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                map.put("link", post.genLink());
                map.put("bw", post.anchoBanda());
                map.put("listComent", post.getListaComentario());
                map.put("accesada", post.getAccesada());
                if (request.session().attribute(SESSION_NAME) != null) {
                    Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                    map.put("login", "true");
                    map.put("username", user.getUsername());
                    map.put("tipoUser", user.getPrivilegio().name());
                } else {
                    map.put("login", "false");
                }
                formTemplate.process(map, writer);
            } catch (Exception e) {
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
                    response.cookie(COOKIE_NAME, username, 3600);
                    request.session().attribute(SESSION_NAME, result.get(0).getId());
                    if (Long.parseLong(request.params("ubicar")) != -1) {
                        response.redirect("/verpost/" + Long.parseLong(request.params("ubicar")) + "/false");
                    } else {
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
            } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.get("/listUsuario/", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                Template formTemplate = configuration.getTemplate("templates/usuarios.ftl");
                Map<String, Object> map = new HashMap<>();
                Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                map.put("login", "true");
                map.put("username", user.getUsername());
                map.put("tipoUser", user.getPrivilegio().name());
                map.put("listUser", UsuarioServices.getInstancia().findAll());
                formTemplate.process(map, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.post("/verpost/:id/comentario", (request, response) -> {
            StringWriter writer = new StringWriter();
            String comentario = request.queryParams("comentario");
            long id = Long.parseLong(request.params("id"));
            Post post = PostService.getInstancia().find(id);
            Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            if (user != null) {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
                Comentario com = new Comentario(comentario, format.format(date).toString(), user, post);
                post.getListaComentario().add(com);
                ComentarioService.getInstancia().crearEntidad(com);
                PostService.getInstancia().editar(post);
            }
            response.redirect("/verpost/" + id + "/false");
            return null;
        });

        Spark.get("/upVote/:id", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            Comentario com = ComentarioService.getInstancia().find(id);
            Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            Likes like = new Likes(user, Typeline2.like);
            LikesService.getInstancia().crearEntidad(like);
            com.addLikes(like);
            com.setUpVote(com.cantUpVote());
            ComentarioService.getInstancia().editar(com);
            response.redirect("/verpost/" + com.getPost().getId() + "/false");
            return null;
        });

        Spark.get("/downVote/:id", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            Comentario com = ComentarioService.getInstancia().find(id);
            Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            Likes like = new Likes(user, Typeline2.unlike);
            LikesService.getInstancia().crearEntidad(like);
            com.addLikes(like);
            com.setDownVote(com.cantDownVote());
            ComentarioService.getInstancia().editar(com);
            response.redirect("/verpost/" + com.getPost().getId() + "/false");
            return null;
        });

        Spark.get("/eliminar/:id", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            Post post = PostService.getInstancia().find(id);
            deleteComen(post);
            ArrayList<Etiqueta> arrayList = new ArrayList<>();
            for (Etiqueta et : post.getListaEtiqueta()) {
                arrayList.add(et);
            }
            post.getListaEtiqueta().removeAll(post.getListaEtiqueta());
            post.getListaComentario().removeAll(post.getListaComentario());
            PostService.getInstancia().eliminar(id);
            veryDeleteEtique(arrayList);
            response.redirect("/");
            return null;
        });

        Spark.get("/eliminar/:idPost/comentario/:id", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            long idPost = Long.parseLong(request.params("idPost"));
            Comentario com = ComentarioService.getInstancia().find(id);
            ComentarioService.getInstancia().eliminar(id);
            response.redirect("/verpost/" + idPost + "/false");
            return null;
        });

        Spark.get("/cambiarPrivi/:id/", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            Usuario user = UsuarioServices.getInstancia().find(id);
            user.setPrivilegio(Typeline.AdministradorAdquirido);
            UsuarioServices.getInstancia().editar(user);
            response.redirect("/listUsuario/");
            return null;
        });

        Spark.get("/eliminar/:id", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            UsuarioServices.getInstancia().eliminar(id);
            response.redirect("/listUsuario/");
            return null;
        });

        Spark.get("/perfil", (request, response) -> {
            StringWriter writer = new StringWriter();
            Usuario user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
            try {
                Template formTemplate = configuration.getTemplate("templates/Perfil.ftl");
                List<Post> listPost = PostService.getInstancia().findPostByUser(user.getId());
                Map<String, Object> map = new HashMap<>();
                map.put("login", "true");
                map.put("username", user.getUsername());
                map.put("tipoUser", user.getPrivilegio().name());
                map.put("user", user);
                map.put("cantArticulo", listPost.size());
                map.put("views", cantViewByUser(listPost));
                map.put("accesada", cantAccesadaByUser(listPost));
                map.put("listPost", listPost);
                formTemplate.process(map, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });

        Spark.get("/post/modificar/:id", (request, response) -> {
            StringWriter writer = new StringWriter();
            long id = Long.parseLong(request.params("id"));
            Post post = PostService.getInstancia().find(id);
            try {
                Usuario user = null;
                if (request.session().attribute(SESSION_NAME) != null) {
                    user = UsuarioServices.getInstancia().find(request.session().attribute(SESSION_NAME));
                }
                Template formTemplate = configuration.getTemplate("templates/crearPost.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("username", user.getUsername());
                map.put("login", "true");
                map.put("tipoUser", user.getPrivilegio().name());
                map.put("titulo", post.getTitulo());
                map.put("descripcion", post.getDescripcion());
                map.put("etiquetas", addEtiquetas(post));
                map.put("url", post.getUrlimagen());
                map.put("modificar", "true");
                map.put("id", post.getId());
                formTemplate.process(map, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;


        });

        Spark.post("/post/modificar/:id/guardando", (request, response) -> {
            long id = Long.parseLong(request.params("id"));
            String titulo = request.queryParams("titulo");
            String descripcion = request.queryParams("descripcion");
            Post post = PostService.getInstancia().find(id);
            post.setTitulo(titulo);
            post.setDescripcion(descripcion);
            PostService.getInstancia().editar(post);
            response.redirect("/verpost/" + id + "/false");
            return null;
        });
    }

    private static void loadDemo() {
        if (UsuarioServices.getInstancia().findAll().size() == 0) {
            UsuarioServices.getInstancia().cargarDemo();
        }
        if (EtiquetaServices.getInstancia().findAll().size() == 0) {
            EtiquetaServices.getInstancia().cargarDemo();
        }

        if (PostService.getInstancia().findAll().size() == 0) {
            PostService.getInstancia().cargarDemo();
        }
        if (MensajeServices.getInstancia().findAll().size() == 0) {
            MensajeServices.getInstancia().cargarDemo();
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

    private static void deleteComen(Post post) {
        for (Comentario com : post.getListaComentario()) {
            ComentarioService.getInstancia().eliminar(com.getId());
        }
    }

    private static void veryDeleteEtique(ArrayList<Etiqueta> listEt) {
        for (Etiqueta et2 : listEt) {
            for (Post post : PostService.getInstancia().findAll()) {
                for (Etiqueta et : post.getListaEtiqueta()) {
                    if (et2.getName().equalsIgnoreCase(et.getName())) {
                        listEt.remove(et2);
                    }
                }
            }
        }
        if (listEt.size() != 0) {
            for (Etiqueta et1 : listEt) {
                EtiquetaServices.getInstancia().eliminar(et1.getId());
            }
        }
    }

    private static double convertir(long sizeB) {
        double cant = 0;
        return cant = sizeB * 0.000001;
    }

    private static int cantViewByUser(List<Post> listPost) {
        int cant = 0;
        for (Post post : listPost) {
            cant += post.getViews();
        }
        return cant;
    }

    private static int cantAccesadaByUser(List<Post> listPost) {
        int cant = 0;
        for (Post post : listPost) {
            cant += post.getAccesada();
        }
        return cant;
    }

    private static String addEtiquetas(Post post) {
        String etiquetas = "";
        int i = 0;
        for (Etiqueta eti : post.getListaEtiqueta()) {
            if (i == 0) {
                etiquetas = eti.getName();
            } else {
                etiquetas += ", " + eti.getName();
            }
            i++;
        }
        return etiquetas;
    }
}
