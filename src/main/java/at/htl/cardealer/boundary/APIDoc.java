package at.htl.cardealer.boundary;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Polleichtner CarDealer API",
                description = "API for a car dealership, where customers can buy a car, look at the dealerships assortment and view their purchase history",
                version = "1.0"
        )
)
public class APIDoc extends Application{
}
