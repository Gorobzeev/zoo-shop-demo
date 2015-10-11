package dao;

import entity.Pet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class PetRepository {

    private final SessionFactory sessionFactory;


        public PetRepository(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        public Pet create(Pet item){
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            return item;
        }

        public Pet update(Pet item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        return item;
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

    public Pet findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Pet where id = :id").setParameter("id", id);
        Pet result = (Pet) query.iterate().next();
        session.getTransaction().commit();
        return result;
    }
}
