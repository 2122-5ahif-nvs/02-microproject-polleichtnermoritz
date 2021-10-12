package at.htl.cardealer.control;

import at.htl.cardealer.entity.Customer;
import io.quarkus.test.junit.QuarkusTest;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerRepositoryTest {

    private static final Logger LOG = Logger.getLogger(CarRepositoryTest.class.getSimpleName());

    @Inject
    CustomerRepository repo;

    @BeforeAll
    @Transactional
    public void setup(){
        System.out.println("-----------------------before-----------------------------------");

        repo.add(new Customer("Max", "Mustermann", "Max.Mustermann@wallinger-online.com", "MustermannStraße 5"));
        repo.add(new Customer("Stefany", "Mustertransgender", "Stefany.Mustertransgender@hotmail.com", "Manuelstraße 5"));
        repo.add(new Customer("Susi", "Musterfrau", "Susi.Musterfrau@gmail.com", "Jagenaustraße 5"));
    }

    @AfterAll
    @Transactional
    public void clear(){
        repo.deleteAll();
    }


    @Test
    @Order(100)
    public void addCustomerOK(){
        assertThat(repo.getAll().size()).isEqualTo(3);
    }

    @Test
    @Order(110)
    public void getCustomerOK(){
        Customer c = repo.get(2L);

        assertThat(c.getFirstName().equals("Stefany"));
        assertThat(c.getLastName().equals("Mustertransgender"));
        assertThat(c.getAddress().equals("Manuelstraße 5"));
        assertThat(c.getEmailAddress().equals("Stefany.Mustertransgender@hotmail.com"));
    }

    @Test
    @Order(120)
    public void getAllCustomersOK(){
        List<Customer> list = repo.getAll();

        assertThat(list.size()).isEqualTo(3);

        assertThat(list)
                .extracting(Customer::getFirstName)
                .contains("Max", "Stefany", "Susi");

        assertThat(list)
                .extracting(Customer::getLastName)
                .contains("Mustermann", "Mustertransgender", "Musterfrau");
    }
}
