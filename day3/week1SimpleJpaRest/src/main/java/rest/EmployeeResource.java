package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.EmployeeFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("employee")
public class EmployeeResource {
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    private EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
    return Response.ok().entity(gson.toJson(EmployeeFacade.getAllEmployees())).build();
    }
    
    @Path("/highest_salary")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestSalary() {
    return Response.ok().entity(gson.toJson(EmployeeFacade.getEmployeeHighestSalary())).build();
    }    
    
    @Path("/{id}")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getEmployeeByID(@PathParam("id") Long id) {
    return Response.ok().entity(gson.toJson(facade.getEmployeeByID(id))).build();
    }
    
    @Path("/name{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmplyeesByName(@PathParam("name")String name) {
    return Response.ok().entity(gson.toJson(facade.getEmployeeByName(name))).build();
    }
          
}
