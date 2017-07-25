package api;

public class PostFilter {

    private long id;
    private String titulo;
    private String descripcion;
    private String urlimagen;
    private String hash;
    private int accesada;
    private int views;
    private String fecha;
    private String user;

    public PostFilter() {
    }

    public PostFilter(long id, String titulo, String descripcion, String urlimagen, String hash, int accesada, int views, String fecha, String user) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlimagen = urlimagen;
        this.hash = hash;
        this.accesada = accesada;
        this.views = views;
        this.fecha = fecha;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getAccesada() {
        return accesada;
    }

    public void setAccesada(int accesada) {
        this.accesada = accesada;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
