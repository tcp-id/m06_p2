package manager;

import model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Hibernate {
    private Session session;
    private Transaction tx;

    public Hibernate() {
    }

    // CONECT AND DISCONECT ------------------------------------------

    public void endSession() {
        session.close();
    }

    public void initSession() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    //RECUPERAR LOS OBJETOS DE ENTRADA Y GUARDAR EN ARRAY ------------------------------

    public ArrayList<Entrada> getAllEntradas(){
        ArrayList<Entrada> tipoEntradas = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("select u from Entrada u");

            List<Entrada> entraList = (List<Entrada>) q.list();

            tipoEntradas.addAll(entraList);

            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return  tipoEntradas;
    }

    /* --------------------------------------------
    ------TABLAS INTERMEDIAS-----------------------
     BODEGA 12M-VID(LIST)
     CAMPO 12M-VID(LIST)
     CAMPO 121-BODEGA(ID)
     ----------------------------------------------*/

    //BODEGA 12M-VID(LIST) ---------------------------------------------------------

    public void one2ManyBodegaVids(Bodega bodega) {
        List<Vid> vids = bodega.getVids();
        int[] cantidadVids = {};
        TipoVid[] tiposVids = {};

        try {
            tx = session.beginTransaction();
            for (int i = 0; i < 4; i++) {
                Vid vid = new Vid(tiposVids[i], cantidadVids[i]);
                session.save(vid);
                vids.add((vid));
            }
            session.save(bodega);
            tx.commit();
            System.out.println("OK ENTRIES");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    // CAMPO 12M-VID(LIST) ------------------------------------------

    public void one2Many(Campo campo){

        List<Vid> campoVids = campo.getListadeVids();
        int[] cantidadVids = {};
        TipoVid[] tipos = {};

        try {
            tx = session.beginTransaction();
            for(int i = 0; i < 4; i++){
                Vid vid = new Vid(tipos[i], cantidadVids[i]);
                session.save(vid);
                campoVids.add((vid));
            }
            session.save(campo);
            tx.commit();
            System.out.println("OK ENTRIES");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    // CAMPO 121-BODEGA(ID) ------------------------------------------

    public void one2oneCampoBodega(Campo campo) {
        Bodega bodega = new Bodega();
        campo.setBodega(bodega);

        try {
            tx = session.beginTransaction();
            session.save(bodega);
            session.save(campo);
            tx.commit();
            System.out.println("OK ENTRIES");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }














    //INSERTA UNA ENTRADA -----------------------------------------------

    public int insertEntrada(Entrada entrada){

        try{
            tx =  session.beginTransaction();
            int id = (Integer) session.save(entrada);
            tx.commit();
            System.out.println("Inserted OK");

            return  id;

        } catch (HibernateException e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return 0;
    }

    //COGE UNA OBJETO ------------------------------------------------------

    public Entrada getEntrada(int id){
        try {
            tx = session.beginTransaction();
            Entrada entrada = session.get(Entrada.class, id);
            System.out.println(entrada.toString());
            tx.commit();
            System.out.println("Saved OK!!");

            return entrada;

        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return  null;
    }



}