package model;

import javax.persistence.*;

@Entity
@Table(name="Entrada")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column
    private  String instruccion;

    public Entrada() {}


    public String getInstruccion() {
        return instruccion;}

    @Override
    public String toString() {
        return "Entrada{" +
                "id=" + id +
                ", instruccion='" + instruccion + '\'' +
                '}';
    }
}
