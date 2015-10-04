package services;

import dao.FacadeDao;
import entity.Pet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/pets")
public class PetService {

    @GET
    @Produces("application/json")
    public Response getAll() {
        FacadeDao facade = new FacadeDao();
        List<Pet> pets = facade.getPetDao().loadAll();
        facade.closeSqlConnection();
        return Response.ok(pets, MediaType.APPLICATION_JSON_TYPE).build();

    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Pet itemToAdd) {
        FacadeDao facade = new FacadeDao();
        Pet pet = facade.getPetDao().add(itemToAdd);
        facade.closeSqlConnection();
        return Response.ok(pet, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @Path("{id}")
    @DELETE
    @Produces("application/json")
    public Response delete(@PathParam("id") int id) {
        FacadeDao facadeDao = new FacadeDao();
        Pet pet = facadeDao.getPetDao().findById(id);
        facadeDao.getPetDao().delete(pet);
        facadeDao.closeSqlConnection();
        return Response.ok(pet, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Pet itemToUpdate) {
        FacadeDao facade = new FacadeDao();
        boolean updated = facade.getPetDao().update(itemToUpdate);
        facade.closeSqlConnection();
        return Response.ok(updated, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
