package controller;

import manager.Hibernate;
import model.Bodega;
import model.Campo;
import model.Entrada;

import java.util.ArrayList;

public class Controller {
    private Bodega bodega;
    private Campo campo;
    private Hibernate hibernate;

    public Controller() {
        this.bodega = new Bodega();
        this.campo = new Campo();
        this.hibernate = new Hibernate();
    }


    public void init(){
        hibernate.initSession();

        ArrayList<Entrada> entrada = hibernate.getAllEntradas();
        ArrayList<Entrada> bodegas = new ArrayList<>();
        ArrayList<Entrada> vids = new ArrayList<>();

        for (Entrada e : entrada){
            if(e.getInstruccion().contains("B")){
                bodegas.add(e);
                hibernate.insertEntrada(e);
            } else if (e.getInstruccion().contains("V")){
                vids.add(e);
            }
            System.out.println(e.getInstruccion());
        }

        hibernate.endSession();

    }


}
