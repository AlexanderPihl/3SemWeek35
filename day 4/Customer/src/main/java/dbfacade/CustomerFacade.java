/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

/**
 *
 * @author alexa
 */

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author alexa
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer addCustomer(String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }

    // Find customer by ID
    public static Customer findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    // Find lastname by name
    public static List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select c from Customer c where c.lastName=:name", Customer.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Get count of all customers
    public static long getNumberOfCustomers() { 
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(c.id) FROM Customer c",Long.class);  
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    // Get all customers
    public static List<Customer> getAllCustomer() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        Customer c1 = facade.addCustomer("Bob", "Hansen");
        Customer c2 = facade.addCustomer("Georg", "Jensen");
        Customer c3 = facade.addCustomer("Svend", "Nielsen");

        // All customers
        List<Customer> results = getAllCustomer();
        for (Customer c : results) {
            System.out.println(c);
        }
        
        // Numbers of customers
         System.out.println("Total customers: " + getNumberOfCustomers());
         
         // Find customer with ID: 2
         System.out.println("Find customer with ID: 2 = " + findById(2));
         
         // Find cutomer with lastname Nielsen
         List<Customer> results2 = findByLastName("Nielsen");
         System.out.println("Customer by lastname: " + results2);
    }

}
