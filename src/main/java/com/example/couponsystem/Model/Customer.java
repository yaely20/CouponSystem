package com.example.couponsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer  implements Serializable {
    @GeneratedValue //מספור אוטומטי
    @Id
    private  int idCustomer;
    private String firstName;
    private String lastName;
    @Embedded
    private  Credetional credetional;
    @JsonBackReference
    @ManyToMany(targetEntity = Coupon.class)
    private List<Coupon> coupons;


    public Customer() {
    }

    public Customer(int idCustomer, String firstName, String lastName, Credetional credetional) {
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credetional = credetional;
        this.coupons = new ArrayList<>();
    }

    public void addCoupon(Coupon c) {
        this.coupons.add(c);
        c.getCustomers().add(this);
    }

    public void removeCoupon(Customer customer) {
        for (Coupon coupon : customer.coupons) {
            coupon.getCustomers().remove(this);
        }
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Credetional getCredetional() {
        return credetional;
    }

    public void setCredetional(Credetional credetional) {
        this.credetional = credetional;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", credetional=" + credetional +
                ", coupons=" + coupons +
                '}';
    }
}
