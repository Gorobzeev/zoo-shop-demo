package dao;

import entity.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class PetRepository {

    private final SessionFactory sessionFactory;


        public PetRepository(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        public void create(Pet item){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }

        public void update(Pet item){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }

        public void delete(Pet item){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }

        public List<Pet> findAll(){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Pet> result = session.createQuery("from Pet").list();
            session.getTransaction().commit();
            return result;
        }

}
