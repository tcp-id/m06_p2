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

    public int getid_bodega() {
        return this.id_Bodega;
    }

    public String getNombre() { return this.nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

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
