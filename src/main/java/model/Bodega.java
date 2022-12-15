package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Bodega")
public class Bodega {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id_bodega;

    @Column
    private  String nombre;

    @OneToMany
    @JoinColumn(name="bodega_id")
    private List<Vid> vids;

    public Bodega() {}

    public Bodega(int id_bodega, String nombre, List<Vid> vids) {
        this.id_bodega = id_bodega;
        this.nombre = nombre;
        this.vids = vids;
    }

    public int getId_bodega() {
        return id_bodega;
    }

    public List<Vid> getVids() {
        return vids;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "id=" + id_bodega +
                ", nombre='" + nombre + '\'' +
                ", bodegas=" + vids +
                '}';
    }
}
