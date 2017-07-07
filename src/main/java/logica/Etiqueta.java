package logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Leny96 on 4/7/2017.
 */
@Entity
public class Etiqueta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public Etiqueta(String name) {
        this.name = name;
    }

    public Etiqueta(){}
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
