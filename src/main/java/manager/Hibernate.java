package manager;

import model.Bodega;
import model.Campo;
import model.Entrada;
import model.Vid;
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

    public void endSession() {
        session.close();
    }

    public void initSession() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    //METE UNA BODEGA

    public int insertBodega(Bodega bodega){

        try{
            tx =  session.beginTransaction();
            int id = (Integer) session.save(bodega);
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

    //COGE UNA BODEGA

    public Bodega getBodega(int id_bodega){
        try {
            tx = session.beginTransaction();
            Bodega bodega = session.get(Bodega.class, id_bodega);
            System.out.println(bodega.toString());
            tx.commit();
            System.out.println("Saved OK!!");

            return bodega;

        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return  null;
    }


    //ASSIGNA LA BODEGA A LA MEMORIA CAMPO i SAVE

    public void oneToOneBodega_Campo(Bodega bodega){
        Campo campo = new Campo();
        bodega.getId_bodega();

        try {
            tx = session.beginTransaction();
            session.save(bodega);
            session.save(campo);
            tx.commit();;
            System.out.println("Aded Success");
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
    }

    //CONSEGUIR TODA LA LISTA SCRIPT

    public ArrayList<Entrada> getAllBodegas(){
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



}