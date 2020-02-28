package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import static entities.Person_.id;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {
//    int id;

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.DROP_AND_CREATE);

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

//    @Path("add")
//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public String addPerson() {
//        return "{\"fName\":\"Kurt\",\"lName\":\"Wonnegut\", phone:\"12345678\",\"id\":0}";
//    }
//    
    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddPerson(String person) throws PersonNotFoundException {
        PersonDTO personDTO = GSON.fromJson(person, PersonDTO.class);
        //Create the Entity object from the DTO and persist it
        //Get the persisted object and convert it back into a DTO,
        PersonDTO result = FACADE.addPerson(personDTO.getfName(), personDTO.getlName(), personDTO.getPhone());
        //this time with the ID
        PersonDTO addedPerson = FACADE.getPerson(result.getId());
        return Response.ok(addedPerson).build();
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String GetAll() {
        PersonsDTO persons = FACADE.getAllPersons();
        return GSON.toJson(persons);
    }

    @Path("GetPerson/{id}")
    @GET
    public String getPersonById(@PathParam("id")int id) throws PersonNotFoundException{
        PersonDTO thePerson = FACADE.getPerson(id);
         return GSON.toJson(thePerson);
    }

    @Path("delete")
    @PUT
    public String deletePerson(String DTO) throws PersonNotFoundException {
        PersonDTO HowTODelte = GSON.fromJson(DTO, PersonDTO.class);
        PersonDTO wasDelete = FACADE.deletePerson(HowTODelte.getId());
        return GSON.toJson(wasDelete + " Was deleted");
    }

    @Path("editperson")
    @PUT
    public Response editPerson(String person) throws PersonNotFoundException {
        PersonDTO DTO = GSON.fromJson(person, PersonDTO.class);
        PersonDTO Edidt = FACADE.editPerson(DTO);
        return Response.ok(Edidt + " was edited").build();
    }

}
