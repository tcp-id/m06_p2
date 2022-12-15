package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Campo")

public class Campo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_campo;

    @OneToMany
    @JoinColumn(name="id_campo")
    private List<Vid> listadeVids;

    @OneToOne
    @JoinColumn(name="id_Bodega")
    private Bodega bodega;

    public Campo() {}

    public int getId_campo() {
        return id_campo;
    }

    public List<Vid> getListadeVids() {
        return listadeVids;
    }

    public Bodega getBodega() { return bodega; }

    public void setId_campo(int id_campo) {
        this.id_campo = id_campo;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    @Override
    public String toString() {
        return "Campo{" +
                "id_campo=" + id_campo +
                ", listadeVids=" + listadeVids +
                '}';
    }
}
