package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Campo")

public class Campo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="id_Campo", unique = true, nullable = false)
    private int id_campo;

    @ManyToOne
    @JoinColumn(name="id_Vid")
    private List<Vid> listadeVids;

    @OneToOne
    @JoinColumn(name="id_Bodega")
    private Bodega bodega;

    public Campo() {}

    public int getId_campo() {
        return id_campo;
    }

    public Bodega getBodega() { return bodega; }

    public void setId_campo(int id_campo) {
        this.id_campo = id_campo;
    }

    @Override
    public String toString() {
        return "Campo{" +
                "id_campo=" + id_campo +
                ", listadeVids=" + listadeVids +
                '}';
    }
}
