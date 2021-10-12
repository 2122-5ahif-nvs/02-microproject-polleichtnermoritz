package at.htl.cardealer.dto;

import javax.persistence.Entity;

public class InvoiceDto {
    private Long customerId;
    private Long carId;
    private double amount;
    private String purchaseDate;

    public Long getCustomerId() {
        return customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "customerId=" + customerId +
                ", carId=" + carId +
                ", amount=" + amount +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }
}
