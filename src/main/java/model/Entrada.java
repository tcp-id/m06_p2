package model;

import javax.persistence.*;

@Entity
@Table(name="Entrada")
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Entrada", unique = true)
    private int id_Entrada;

    @Column
    private  String instruccion;

    public Entrada() {}

    public int getId_Entrada() {
        return id_Entrada;}
    public String getInstruccion() {
        return instruccion;}
}
