package at.htl.cardealer.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "P_CAR")
@XmlRootElement
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String brand;
    private String model;
    private int horsePower;
    private int constructionYear;
    private String color;
    private boolean availability;


    public Car() {}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    public Car(String brand, String model, int horsePower, int constructionYear, String color){
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.constructionYear = constructionYear;
        this.color = color;
        this.availability = true;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean status) {
        this.availability = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public String getColor() {
        return color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", horsePower=" + horsePower +
                ", constructionYear=" + constructionYear +
                ", color='" + color + '\'' +
                '}';
    }
}
