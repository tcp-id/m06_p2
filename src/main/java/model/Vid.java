package model;

import javax.persistence.*;

@Entity
@Table(name="vid")

public class Vid {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    @Column(name="id_Vid", unique = true, nullable = false)
    private int id_Vid;

    @Column
    private TipoVid tipo;

    @Column
    private int cantidad;

    public Vid() {}

    public int getId_Vid() {return id_Vid;}

    public TipoVid getTipo() {return tipo;}

    public int getCantidad() {return cantidad;}


    @Override
    public String toString() {
        return "Vid{" +
                "id_Vid=" + id_Vid +
                ", cantidad=" + cantidad +
                '}';
    }
}
