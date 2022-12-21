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

    // ------------------------------------ GETS ------------------------------------------------

       public Bodega getBodega(int id) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Bodega bodega = session.get(Bodega.class, id);
            System.out.println(bodega.toString());

            tx.commit();
            System.out.println("SAVED BODEGA!!");

            return bodega;

        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return  null;
    }
    public Campo getCampo(int id) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Campo campo = session.get(Campo.class, id);
            System.out.println(campo.toString());

            tx.commit();
            System.out.println("SAVED BODEGA!!");

            return campo;

        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return  null;
    }

    public Vid getVid(int id) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Vid vid = session.get(Vid.class, id);
            System.out.println(vid.toString());

            tx.commit();
            System.out.println("SAVED BODEGA!!");

            return vid;

        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return  null;
    }

    //------- RECUPERAR LOS OBJETOS DE ENTRADA Y GUARDAR EN ARRAY ------------------------------

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

    /* -----------------------------------------------------------------------------
    -----------------------------------TABLAS INTERMEDIAS---------------------------
     BODEGA 12M-VID(LIST)
     CAMPO 12M-VID(LIST)
     CAMPO 121-BODEGA(ID)
     -----------------------------------------------------------------------------*/

    //BODEGA 12M-VID(LIST) ---------------------------------------------------------

    public void one2ManyBodegaVids(Bodega bodega) {
        List<Vid> vids = bodega.getlistVids();
        int[] cantidadVids = {};
        TipoVid[] tiposVids = {};

        try {
            tx = session.beginTransaction();
            for (int i = 0; i < vids.size(); i++) {
                Vid vid = new Vid(tiposVids[i], cantidadVids[i]);
                session.save(vid);
                vids.add((vid));
            }
            session.save(bodega);
            tx.commit();
            System.out.println("OK VIDS EN BODEGA");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    // CAMPO 12M-VID(LIST) ------------------------------------------

    public void one2ManyCampoVids(Campo campo){

        List<Vid> campoVids = campo.getListadeVids();
        int[] cantidadVids = {};
        TipoVid[] tipos = {};

        try {
            tx = session.beginTransaction();
            for(int i = 0; i < campoVids.size(); i++){
                Vid vid = new Vid(tipos[i], cantidadVids[i]);
                session.save(vid);
                campoVids.add((vid));
            }
            session.save(campo);
            tx.commit();
            System.out.println("OK VIDS EN CAMPO");
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
            System.out.println("CAMPO EN BODEGA");
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    // ------------------------------ INSERTS -----------------------------------------------

    public int insertBodega(Bodega bodega){

        try{
            tx =  session.beginTransaction();
            int id = (Integer) session.save(bodega);
            tx.commit();
            System.out.println("Inserted BODEGA");

            return  id;

        } catch (HibernateException e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return 0;
    }
    public int insertCampo(Campo campo){

        try{
            tx =  session.beginTransaction();
            int id = (Integer) session.save(campo);
            tx.commit();
            System.out.println("Inserted CAMPO");

            return  id;

        } catch (HibernateException e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return 0;
    }

    public int insertVid(Vid vid){

        try{
            tx =  session.beginTransaction();
            int id = (Integer) session.save(vid);
            tx.commit();
            System.out.println("Inserted ViD");

            return  id;

        } catch (HibernateException e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return 0;
    }





}