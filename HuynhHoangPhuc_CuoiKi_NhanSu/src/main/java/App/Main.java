package App;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SQLdb");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        tx.commit();

    }
}