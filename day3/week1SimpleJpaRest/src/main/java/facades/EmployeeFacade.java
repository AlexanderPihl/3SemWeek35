/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


/**
 *
 * @author alexa
 */
public class EmployeeFacade {
    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;
    
    public EmployeeFacade() {}
    
        public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }
        
        public static List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> edtoList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select e FROM Employee e");
        List<Employee> employees = query.getResultList();
        employees.stream().forEach(p -> {edtoList.add(new EmployeeDTO(p));});
        return edtoList;
    }
        
        public EmployeeDTO getEmployeeByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        EmployeeDTO e1 = new EmployeeDTO(employee);
        return e1;
     
    }
        
        public EmployeeDTO getEmployeeByName(String name){
              EntityManager em = emf.createEntityManager();
                try{
                    Query query1 = em.createQuery("Select e FROM Employee e WHERE e.name = :name");
                    query1.setParameter("name", name);
                     query1.setMaxResults(1);
                    Employee result2 = (Employee)query1.getSingleResult();
                     EmployeeDTO e2 = new EmployeeDTO(result2);
                    return e2;
                }finally {
                    em.close();
                }
        }

        public static List getEmployeeHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
            List<Employee> result = query.getResultList();
            return result;
        } finally {
            em.close();
        }
    }
        
        public Employee createEmployee(String name, String adress, int salary) {
        Employee employee = new Employee(name, adress, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }
  }   
