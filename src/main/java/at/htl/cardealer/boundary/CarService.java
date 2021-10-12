package at.htl.cardealer.boundary;

import at.htl.cardealer.control.CarRepository;
import at.htl.cardealer.entity.Car;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("api-car")
@Tag(name = "Car")
public class CarService {

    @Inject
    CarRepository carRepository;


    @Operation(
        summary = "adds car to repo",
        description = "adds the car given in the POST request to a list in the CarRepository"
    )
    @POST
    @Path("car")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Post(Car car){
        Car c = carRepository.add(car);
        return Response.ok(c).build();
    }

    @Operation(
            summary = "gets specific car",
            description = "gets the specific car with id given in the GET request from the list in CarRepository"
    )
    @GET
    @Path("car/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Car findCar(@PathParam("id") Long id){
        return carRepository.get(id);
    }

    @Operation(
            summary = "gets all car",
            description = "gets all car from the list in the CarRepository"
    )
    @GET
    @Path("allAvailable")
    @Produces( MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Car> findAllAvailableCars(@QueryParam("limit") int limit){
        System.out.println(limit);
        return carRepository.getAvailableCars().stream().limit(limit).collect(Collectors.toList());
    }

    @Operation(
            summary = "updates a specific car",
            description = "updates a specific car with the id from the PUT request in the CarRepository"
    )
    @PUT
    @Path("car/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id")Long id, Car car){
        Car newCar = carRepository.update(id, car);
        return Response.ok(newCar).build();
    }

    @Operation(
            summary = "deletes a specific car",
            description = "deletes a specific car with the id from the DELETE request in the CarRepository"
    )
    @DELETE
    @Path("car/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Car delete(@PathParam("id") Long id){
        return carRepository.setUnAvailable(id);
    }
}
