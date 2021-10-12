package at.htl.cardealer.control;

import at.htl.cardealer.entity.Car;
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
public class CarRepositoryTest {

    private static final Logger LOG = Logger.getLogger(CarRepositoryTest.class.getSimpleName());

    @Inject
    CarRepository repo;

    @BeforeAll
    @Transactional
    public void setup(){
        System.out.println("-----------------------before-----------------------------------");

        repo.add(new Car("Audi", "RS5", 420, 2017, "black"));
        repo.add(new Car("Mercedes-Benz", "C63 S", 430, 2014, "white"));
        repo.add(new Car("BMW", "M2 Competition", 410, 2015, "purple"));
        repo.add(new Car("Nissan", "Skyline R34 GT-R", 500, 1990, "Red"));
    }

    @AfterAll
    @Transactional
    public void clear(){
        repo.deleteAll();
    }


    @Test
    @Order(100)
    public void
    addCarOK(){
        assertThat(repo.listAll().size()).isEqualTo(4);
    }

    @Test
    @Order(110)
    public void getCarOK(){
        Car car = repo.get(2L);

        assertThat(car.getBrand().equals("Mercedes-Benz"));
        assertThat(car.getModel().equals("C63 S"));
        assertThat(car.getColor().equals("white"));
    }

    @Test
    @Order(120)
    public void getAllCarsOK(){
        List<Car> list = repo.listAll();

        assertThat(list.size()).isEqualTo(4);

        assertThat(list)
                .extracting(Car::getBrand)
                .contains("Audi", "Mercedes-Benz", "BMW", "Nissan");

        assertThat(list)
                .extracting(Car::getModel)
                .contains("RS5", "C63 S", "M2 Competition", "Skyline R34 GT-R");
    }

    @Test
    @Order(130)
    public void updateCarOK(){
        Car newCar = new Car("test", "test", 1, 1, "None");
        Car updatedCar =repo.update(2L, newCar);

        assertThat(updatedCar.getModel().equals(newCar.getModel()));
        assertThat(updatedCar.getBrand().equals(newCar.getBrand()));
        assertThat(updatedCar.getColor().equals(newCar.getColor()));
    }

}
