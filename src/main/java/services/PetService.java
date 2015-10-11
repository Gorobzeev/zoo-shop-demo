package services;

import dao.FacadeDao;
import dao.StorageRepositories;
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
        StorageRepositories session = StorageRepositories.getInstance();
        List<Pet> pets = session.getPetRepository().findAll();
        return Response.ok(pets, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Pet itemToAdd) {
        StorageRepositories session = StorageRepositories.getInstance();
        Pet pet = session.getPetRepository().create(itemToAdd);
        return Response.ok(pet, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @Path("{id}")
    @DELETE
    @Produces("application/json")
    public Response delete(@PathParam("id") int id) {
        StorageRepositories session = StorageRepositories.getInstance();
        Pet pet = session.getPetRepository().findById(id);
        session.getPetRepository().delete(pet);
        return Response.ok(pet, MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Pet itemToUpdate) {
        StorageRepositories session = StorageRepositories.getInstance();
        Pet newPet = session.getPetRepository().update(itemToUpdate);
        return Response.ok(newPet, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
