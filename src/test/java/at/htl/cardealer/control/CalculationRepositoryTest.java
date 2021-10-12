package at.htl.cardealer.control;

import at.htl.cardealer.entity.Invoice;
import at.htl.cardealer.entity.Car;
import at.htl.cardealer.entity.Customer;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(PER_CLASS)
public class CalculationRepositoryTest {

    private static final Logger LOG = Logger.getLogger(CarRepositoryTest.class.getSimpleName());

    @Inject
    InvoiceRepository repo;
    @Inject
    CarRepository carRepo;
    @Inject
    CustomerRepository custRepo;

    @BeforeAll
    @Transactional
    public void setup(){
        carRepo.add(new Car("Audi", "RS5", 420, 2017, "black"));
        carRepo.add(new Car("Mercedes-Benz", "C63 S", 430, 2014, "white"));
        carRepo.add(new Car("BMW", "M2 Competition", 410, 2015, "purple"));

        custRepo.add(new Customer("Max", "Mustermann", "Max.Mustermann@wallinger-online.com", "MustermannStraße 5"));
        custRepo.add(new Customer("Stefany", "Mustertransgender", "Stefany.Mustertransgender@hotmail.com", "Manuelstraße 5"));

        repo.persist(new Invoice(custRepo.get(1L), carRepo.get(1L), 2000, "2020-11-11"));
        repo.persist(new Invoice(custRepo.get(2L), carRepo.get(2L), 2000, "2020-11-11"));
        repo.persist(new Invoice(custRepo.get(1L), carRepo.get(3L), 2000, "2020-11-11"));
    }

    @AfterAll
    @Transactional
    public void clear(){
        repo.deleteAll();
        custRepo.deleteAll();
        carRepo.deleteAll();
    }


    @Test
    @Order(100)
    public void
    addCalculationOK(){
        assertThat(repo.getAll().size()).isEqualTo(3);
    }

    @Test
    @Order(110)
    public void getCalculationOK(){
        Invoice c = repo.get(2L);

        assertThat(c.getCarId()).isEqualTo(2L);
        assertThat(c.getCustomerId()).isEqualTo(2L);
        assertThat(c.getAmount()).isEqualTo(2000);
        assertThat(c.getPurchaseDate()).isEqualTo("2020-11-11");
    }

    @Test
    @Order(120)
    public void getAllCalculationsOK(){
        List<Invoice> list = repo.getAll();

        assertThat(list.size()).isEqualTo(3);

        assertThat(list)
                .extracting(Invoice::getCustomerId)
                .contains(1L, 2L);

        assertThat(list)
                .extracting(Invoice::getCarId)
                .contains(1L, 2L, 3L);
    }
}
