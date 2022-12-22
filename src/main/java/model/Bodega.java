package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Bodega")
public class Bodega {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id_Bodega;

    @Column
    private  String nombre;

    @OneToMany
    @JoinColumn(name="id_Vid")
    private List<Vid> listVids;

    public Bodega() {}

    public Bodega(String nombre) {
        this.nombre = nombre;
        this.listVids = new ArrayList<>();
    }

    public int getId_Bodega() { return id_Bodega; }

    public List<Vid> getlistVids() {
        return this.listVids;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "id=" + id_Bodega +
                ", nombre='" + nombre + '\'' +
                ", bodegas=" + listVids +
                '}';
    }
}
