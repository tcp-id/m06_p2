package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Bodega")

public class Bodega {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    @Column(name="id_Bodega", unique = true, nullable = true)
    private int id_bodega;

    @Column
    private  String nombre;

    @ManyToOne
    @JoinColumn(name="Bodega_id")
    private List<Bodega> bodegas;

    public Bodega() {}

    public int getId_bodega() {
        return id_bodega;
    }

    public String getNombre() {
        return nombre;
    }


    @Override
    public String toString() {
        return "Bodega{" +
                "id=" + id_bodega +
                ", nombre='" + nombre + '\'' +
                ", bodegas=" + bodegas +
                '}';
    }
}
