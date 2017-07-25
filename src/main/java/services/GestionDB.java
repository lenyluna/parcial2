package services;

import javafx.geometry.Pos;
import logica.Etiqueta;
import logica.Mensaje;
import logica.Post;
import logica.Usuario;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Leny96 on 5/7/2017.
 */
public class GestionDB<T> {
    private static EntityManagerFactory emf;
    private Class<T> claseEntidad;

    public GestionDB(Class<T> claseEntidad) {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        }
        this.claseEntidad = claseEntidad;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    private Object getValorCampo(T entidad) {
        if (entidad == null) {
            return null;
        }
        //aplicando la clase de reflexión.
        for (Field f : entidad.getClass().getDeclaredFields()) {  //tomando todos los campos privados.
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    Object valorCampo = f.get(entidad);
                    return valorCampo;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void crearEntidad(T entidad) {
        EntityManager em = getEntityManager();

        try {
            if (em.find(claseEntidad, getValorCampo(entidad)) != null) {
                System.out.println("No se creo la entidad porque ya existe");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Parametros igeal.");
        }
        em.getTransaction().begin();
        try {
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public void editar(T entidad) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public T find(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(claseEntidad, id);
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public Post findH(Object hash) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Post e where e.hash like :h");
        query.setParameter("h", "%" + hash + "%");
        return (Post) query.getSingleResult();
    }


    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(claseEntidad);
            criteriaQuery.select(criteriaQuery.from(claseEntidad));
            return em.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }


    public void eliminar(Object entidadId) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            T entidad = em.find(claseEntidad, entidadId);
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public Usuario findAllByUser(String username) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Usuario e where e.username like :username");
        query.setParameter("username", "%" + username + "%");
        return (Usuario) query.getSingleResult();

    }

    public List<Mensaje> findAllChat(String emisor, String receptor) {
        EntityManager em = getEntityManager();
        String qkin = "select e from Mensaje e where e.emisor = :emisor and e.userDestino = :receptor or e.emisor = :receptor and e.userDestino = :emisor ";
        Query query = em.createQuery(qkin);
        query.setParameter("emisor", "'" + emisor + "'");
        query.setParameter("receptor", "'" + receptor + "'");
        System.out.println("la JODIDA QUERY DEL CARAJO: " + query.getFirstResult());

        return query.getResultList();

    }


    public Etiqueta findEtiquetaByName(String name) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Etiqueta e where e.name like :name");
        query.setParameter("name", "%" + name + "%");
        return (Etiqueta) query.getSingleResult();

    }

    public List<Post> findPostByUser(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Post e where e.user.id like :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public Post findPostBytitulo(String titulo) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Post e where e.titulo like :titulo");
        query.setParameter("titulo", "%" + titulo + "%");
        return (Post) query.getSingleResult();

    }

    public List<Post> findALLPost(int pageActual) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("select e from Post e", Post.class);
            query.setFirstResult(pageActual*12);
            query.setMaxResults(12);
            return query.getResultList();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }

    }
}
