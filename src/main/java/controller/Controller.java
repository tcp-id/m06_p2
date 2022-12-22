package controller;

import manager.Hibernate;
import model.*;

import java.util.ArrayList;

public class Controller {
    private Bodega bodega;
    private Campo campo;
    private Vid vid;
    private Hibernate hibernate;
    private ArrayList<Entrada> entradas;
    private ArrayList<Bodega> bodegas;
    private ArrayList<Campo> campos;
    private ArrayList<Vid> vids;

    public Controller() {
        this.bodega = new Bodega();
        this.campo = new Campo();
        this.vid = new Vid();
        this.hibernate =  new Hibernate();
        this.entradas = hibernate.getAllEntradas();
        this.bodegas = new ArrayList<>();
        this.campos = new ArrayList<>();
        this.vids = new ArrayList<>();
    }



    public void init() {
        hibernate.initSession();

        for (Entrada e : entradas) {
            String[] cap = e.getInstruccion().split(" ");

            if (cap[0].equals("B")) {
                Bodega nomBodega = new Bodega(e.getInstruccion());
                hibernate.insertBodega(nomBodega);
                bodegas.add(nomBodega);

            } else if (cap[0].equals("C")) {
                for (Bodega b : bodegas) {
                    Bodega bod = hibernate.getBodega(b.getId_Bodega());
                    hibernate.one2oneCampoBodega(bod);

                    campo = hibernate.getCampo(bodega.getId_Bodega());
                    campos.add(campo);
                }

            } else if (cap[0].equals("V")) {
                TipoVid tipo = str2int(cap[1]);
                int cant = Integer.parseInt(cap[2]);

                for (Campo c : campos) {
                    hibernate.one2ManyCampoVids(c, tipo, cant);

                    vid = hibernate.getVid(campo.getId_Campo());
                    vids.add(vid);
                }

            } else {
                for (Campo c : campos) {
                    Bodega bod = (Bodega) c.getBodega().getlistVids();
                    hibernate.one2ManyBodegaVids(bod);
                }
            }
        }

        hibernate.endSession();
    }

    public TipoVid str2int(String tipo){
        TipoVid n = null;
        if(tipo.toUpperCase().equals("NEGRA")){
            n= TipoVid.NEGRA;
        } else if (tipo.toUpperCase().equals("BLANCA")) {
            n= TipoVid.BLANCA;
        }
        return n;
    }
}