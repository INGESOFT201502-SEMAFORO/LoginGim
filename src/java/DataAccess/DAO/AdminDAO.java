/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Admin;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Juan
 */
public class AdminDAO {
    
    public EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("GimnasioPU");
    
    public Admin persist(Admin admin) {
        
        EntityManager em = emf1.createEntityManager();
        em.getTransaction().begin();
        
        try {
            em.persist(admin);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            return admin;  
        }
    }
    
    public Admin searchByUsername (String username){
        EntityManager em = emf1.createEntityManager();
        Admin admin = null;
        Query q = em.createNamedQuery("Admin.findByUsername");
        q.setParameter("username", username);
        try {
            admin = (Admin) q.getSingleResult();
            
        } catch (Exception e) {
        } finally {
            em.close();
            return admin;
        }   
    }
}
