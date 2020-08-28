/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import entity.Animal;
import java.util.List;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author alexa
 */
@Path("animals_db")
public class AnimalFromDB {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    /**
     * Retrieves representation of an instance of rest.AnimalFromDB
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AnimalFromDB
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @Path("/animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
    EntityManager em = emf.createEntityManager();
    try{
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animals = query.getResultList();
        return new Gson().toJson(animals);
     } finally {
            em.close();
     }
  }
    
    @Path("animalbyid/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimal(@PathParam("id") int id) {
        //Hvis den kaldes med .../animalbyid/2  vil id nu være lig 2.
        //Den værdi kan I så benytte til at slå op i databasen med em.find(
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a where a.id=:id", Animal.class);
            query.setParameter("id", id);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
    
    @Path("animalbytype/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTypeAnimal(@PathParam("type") String type) {
        //Hvis den kaldes med .../animalbyid/2  vil id nu være lig 2.
        //Den værdi kan I så benytte til at slå op i databasen med em.find(
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a where a.type=:type", Animal.class);
            query.setParameter("type", type);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
    
    
    @Path("random_animal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTypeAnimal() {
        //Hvis den kaldes med .../animalbyid/2  vil id nu være lig 2.
        //Den værdi kan I så benytte til at slå op i databasen med em.find(
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a order by function('RAND')", Animal.class);
            query.setMaxResults(1);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

}
