package at.htl.cardealer.control;

import at.htl.cardealer.entity.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    @Transactional
    public Car add(Car car){
        car.setAvailability(true);
        return this.getEntityManager().merge(car);
    }

    @Transactional
    public Car setUnAvailable(Long id){
        Car c = get(id);
        c.setAvailability(false);
        return c;
    }

    @Transactional
    public Car update(Long id, Car car){
        Car oldCar = this.get(id);
        if (oldCar != null){
            getEntityManager().merge(oldCar);
            return oldCar;
        }
        return null;
    }

    @Transactional
    public Car get(Long id){
        return find("id", id).firstResult();
    }

    @Transactional
    public List<Car> getAvailableCars(){
        return list("availability", true);
    }

    /*public void clear(){
        cars.clear();
        count = 0L;
    }*/
}
