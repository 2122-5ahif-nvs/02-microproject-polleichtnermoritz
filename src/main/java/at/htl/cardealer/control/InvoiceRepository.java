package at.htl.cardealer.control;

import at.htl.cardealer.dto.InvoiceDto;
import at.htl.cardealer.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class InvoiceRepository implements PanacheRepository<Invoice> {
    @Inject
    CarRepository carRepository;
    @Inject
    CustomerRepository customerRepo;

    public InvoiceRepository() {}


    @Transactional
    public Invoice add(JsonObject o) {
        ObjectMapper mapper = new ObjectMapper();
        InvoiceDto dto = null;
        try {
            dto = mapper.readValue(o.toString(), InvoiceDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("repo" +dto);
        Invoice c = new Invoice(customerRepo.get(dto.getCustomerId()), carRepository.get(dto.getCarId()), dto.getAmount(), dto.getPurchaseDate());
        System.out.println("repo" +c);
        Invoice newCalc = getEntityManager().merge(c);
        carRepository.setUnAvailable(c.getCar().getId());
        return newCalc;
    }

    @Transactional
    public Invoice remove(Long id){
        Invoice c = get(id);
        delete(c);
        return c;
    }

    @Transactional
    public Invoice update(Long id, Invoice calculation){
        Invoice oldCalc = this.get(id);
        if (oldCalc != null){
            getEntityManager().merge(oldCalc);
            return oldCalc;
        }
        return null;
    }

    @Transactional
    public Invoice get(Long id){
        return find("id", id).firstResult();
    }

    public List<Invoice> getAll(){
        return listAll();
    }

    @Transactional
    public List<Invoice> getUserPurchaseHistory(Long id){
        return list("customer", customerRepo.get(id));//find("customer", customerRepo.get(id)).list();
    }
}
