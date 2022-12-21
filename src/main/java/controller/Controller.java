package controller;

import manager.Hibernate;
import model.Bodega;
import model.Campo;
import model.Entrada;
import model.Vid;

import java.util.ArrayList;
import java.util.Spliterator;

public class Controller {
    private Bodega bodega;
    private Campo campo;
    private Vid vid;
    private Hibernate hibernate;

    public Controller() {
        this.bodega = new Bodega();
        this.campo = new Campo();
        this.vid = new Vid();
        this.hibernate = new Hibernate();
    }


    public void init() {
        hibernate.initSession();

        ArrayList<Entrada> entrada = hibernate.getAllEntradas();
        ArrayList<Bodega> bodegas = new ArrayList<>();
        ArrayList<Campo> campos = new ArrayList<>();
        ArrayList<Vid> vids = new ArrayList<>();

        for (Entrada e : entrada) {
           String[] cap = e.getInstruccion().split(" ");
            if (cap[0].equals("B")){

                int nomBodega =  hibernate.insertBodega(new Bodega(bodega.getNombre()));
                bodega = hibernate.getBodega(nomBodega);
                bodegas.add(bodega);

                hibernate.one2ManyBodegaVids(bodega);

            } else if (cap[0].equals("C")){
                int id = hibernate.insertCampo(new Campo(campo.getBodega()));
                campo = hibernate.getCampo(id);
                campos.add(campo);

                hibernate.one2oneCampoBodega(campo);
                hibernate.one2ManyCampoVids(campo);

            } else if (cap[0].equals("V")) {
                int id = hibernate.insertVid(new Vid(vid.getTipo(), vid.getCantidad()));
                vid = hibernate.getVid(id);
                vids.add(vid);


            } else {

                hibernate.one2ManyCampoVids();
            }

        System.out.println();
        }

        hibernate.endSession();
    }
}
