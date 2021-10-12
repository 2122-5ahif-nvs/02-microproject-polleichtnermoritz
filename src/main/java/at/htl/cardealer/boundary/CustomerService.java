package at.htl.cardealer.boundary;

import at.htl.cardealer.control.CustomerRepository;
import at.htl.cardealer.entity.Customer;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api-customer")
@Tag(name = "Customer")
public class CustomerService {
    @Inject
    CustomerRepository customerRepository;


    @Operation(
            summary = "adds customer to repo",
            description = "adds the customer given in the POST request to a list in the CustomerRepository"
    )
    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Post(Customer customer){
        Long id = customerRepository.add(customer);
        return Response.ok(new Customer(id, customer.getFirstName(), customer.getLastName(), customer.getEmailAddress(), customer.getAddress())).build();
    }


    @Operation(
            summary = "gets specific customer",
            description = "gets the specific customer with id given in the GET request from the list in CustomerRepository"
    )
    @GET
    @Path("customer/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Customer findCar(@PathParam("id") Long id){
        return customerRepository.get(id);
    }


    @Operation(
            summary = "gets all customer",
            description = "gets all customer from the list in the CustomerRepository"
    )
    @GET
    @Path("customer/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Customer> findAll(){
        return customerRepository.getAll();
    }


    @Operation(
            summary = "updates a specific customer",
            description = "updates a specific customer with the id from the PUT request in the CustomerRepository"
    )
    @PUT
    @Path("customer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id")Long id, Customer customer){
        Customer newCustomer = customerRepository.update(id, customer);
        return Response.ok(newCustomer).build();
    }


    @Operation(
            summary = "deletes a specific customer",
            description = "deletes a specific customer with the id from the DELETE request in the CustomerRepository"
    )
    @DELETE
    @Path("customer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Customer delete(@PathParam("id") Long id){
        return customerRepository.remove(id);
    }
}
