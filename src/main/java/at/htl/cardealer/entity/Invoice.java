package at.htl.cardealer.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "P_INVOICE")
@XmlRootElement
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private double amount;
    private String purchaseDate;

    @ManyToOne(cascade = CascadeType.ALL)
    Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    public Invoice(){}

    public Invoice(Customer customer, Car car, double amount, String purchaseDate){
        this.customer = customer;
        this.amount = amount;
        this.car = car;
        this.purchaseDate = purchaseDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public Long getId() { return id; }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getCarId() {
        return getCar().getId();
    }

    public Long getCustomerId() {
        return getCustomer().getId();
    }


    @Override
    public String toString() {
        return "Calculation{" +
                "id=" + id +
                ", amount=" + amount +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", customer=" + customer +
                ", car=" + car +
                '}';
    }
}
