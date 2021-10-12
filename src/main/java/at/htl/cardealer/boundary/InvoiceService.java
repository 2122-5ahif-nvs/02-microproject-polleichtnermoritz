package at.htl.cardealer.boundary;

import at.htl.cardealer.dto.InvoiceDto;
import at.htl.cardealer.control.InvoiceRepository;
import at.htl.cardealer.entity.Invoice;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("api-Invoice")
@Tag(name = "Invoice")
public class InvoiceService {

    @Inject
    InvoiceRepository invoiceRepository;

    @Operation(
            summary = "adds Invoice to repo",
            description = "adds the calculation given in the POST request, and deletes the car with the Invoice.carId (because calculation means that someone bought the car)" +
                    ", to a list in the InvoiceRepository"
    )
    @POST
    @Path("Invoice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Post(JsonObject o){
        System.out.println("service "+ o);
        Invoice c = invoiceRepository.add(o);
        return Response.ok().entity(c).build();
    }


    @Operation(
            summary = "gets specific Invoice",
            description = "gets the specific Invoice with id given in the GET request from the list in InvoiceRepository"
    )
    @GET
    @Path("Invoice/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Invoice findInvoice(@PathParam("id") Long id){
        return invoiceRepository.get(id);
    }


    @Operation(
            summary = "gets all calculations",
            description = "gets all calculations from the list in the CalculationRepository"
    )
    @GET
    @Path("Invoice/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invoice> findAll(){
        return invoiceRepository.getAll();
    }

    @Operation(
            summary = "gets all the calculation from customer",
            description = "gets all the calculation (purchases) from a specific customer"
    )
    @GET
    @Path("Invoice/purchaseHistory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Invoice> getPurchaseHistory(@PathParam("id") Long id){
        return invoiceRepository.getUserPurchaseHistory(id);
    }

}
