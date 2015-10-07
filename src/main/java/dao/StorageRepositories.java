package dao;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StorageRepositories {

    private PetRepository petRepository;

    private static class Holder {
        private static final StorageRepositories
                INSTANCE = new StorageRepositories();
    }

    public static StorageRepositories getInstance() {
        return Holder.INSTANCE;
    }

    private StorageRepositories() {
        SessionFactory sessionFactory;

        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        this.petRepository = new PetRepository(sessionFactory);
    }

    public PetRepository getPetRepository() {
        return getPetRepository();
    }
}
