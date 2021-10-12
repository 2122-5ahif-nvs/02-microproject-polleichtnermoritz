package at.htl.cardealer.control;

import at.htl.cardealer.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    @Inject
    InvoiceRepository invoiceRepository;

    public CustomerRepository(){
        //initWithDefaultCustomer();
    }

    @Transactional
    public Long add(Customer customer){
        getEntityManager().merge(customer);
        return customer.getId();
    }

    public Customer remove(Long id){
        Customer oldCust = this.get(id);
        if (oldCust != null){
            delete(oldCust);
            return oldCust;
        }
        return null;
    }

    public Customer update(Long id, Customer c){
        Customer oldCust = this.get(id);
        if (oldCust != null){
            getEntityManager().merge(oldCust);
            return oldCust;
        }
        return null;
    }

    public Customer get(Long id){
        return find("id", id).firstResult();
    }

    public List<Customer> getAll(){
        return listAll();
    }

}
