package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Campo")

public class Campo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_Campo;

    @OneToMany
    @JoinColumn(name="id_Vid")
    private List<Vid> listadeVids;

    @OneToOne
    @JoinColumn(name="id_Bodega")
    private Bodega bodega;

    public Campo() {}

    public Campo(Bodega bodega) {
        this.listadeVids = new ArrayList<>();
        this.bodega = bodega;
    }

    public int getId_campo() {
        return id_Campo;
    }

    public List<Vid> getListadeVids() {
        return listadeVids;
    }

    public Bodega getBodega() { return bodega; }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    @Override
    public String toString() {
        return "Campo{" +
                "id_campo=" + id_Campo +
                ", listadeVids=" + listadeVids +
                '}';
    }
}
