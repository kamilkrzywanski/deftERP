//package com.defterp.dataAccess;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.enterprise.inject.Disposes;
//import jakarta.enterprise.inject.Produces;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//public class EntityManagerFactoryProducer {
//
//    @Produces//package com.defterp.dataAccess;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.enterprise.inject.Disposes;
//import jakarta.enterprise.inject.Produces;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//public class EntityManagerFactoryProducer {
//
//    @Produces
//    @ApplicationScoped
//    public EntityManagerFactory createEntityManagerFactory() {
//        return Persistence.createEntityManagerFactory("my-presistence-unit");
//    }
//
//    public void close(@Disposes EntityManagerFactory entityManagerFactory) {
//        entityManagerFactory.close();
//    }
//
//    @Produces
//    @RequestScoped
//    public EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//    }
//
//    public void close(@Disposes EntityManager entityManager) {
//        entityManager.close();
//    }
//}

//    @ApplicationScoped
//    public EntityManagerFactory createEntityManagerFactory() {
//        return Persistence.createEntityManagerFactory("my-presistence-unit");
//    }
//
//    public void close(@Disposes EntityManagerFactory entityManagerFactory) {
//        entityManagerFactory.close();
//    }
//
//    @Produces
//    @RequestScoped
//    public EntityManager createEntityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//    }
//
//    public void close(@Disposes EntityManager entityManager) {
//        entityManager.close();
//    }
//}
