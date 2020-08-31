/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author alexa
 */
public class Tester {
    
        public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("Hans", "Hansevej 2", 27500));
            em.persist(new Employee("Hilda", "Hildavej 69", 22500));
            em.persist(new Employee("Karl", "Karlstraede 1", 30500));
            em.persist(new Employee("Dorthe", "Dorthe alle 8", 18500));
            em.getTransaction().commit();
            
//            Query query = em.createQuery("Select e FROM Employee e");
//            List<Employee> result = query.getResultList();
//            System.out.println("Total : " + result);
        
        } finally {
            em.close();
            emf.close();
        }
    }
    
}
