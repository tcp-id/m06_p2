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

        ArrayList<Entrada> entrada = hibernate.getAllBodegas();

        for (Entrada e : entrada){
            System.out.println(e.getInstruccion());
        }

        hibernate.endSession();

    }


}
