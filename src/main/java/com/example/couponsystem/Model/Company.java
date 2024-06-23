package com.example.couponsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company implements Serializable {

        @GeneratedValue
        @Id
        private int id;
        private String name;
        @Embedded
        private Credetional credetional;
        @JsonBackReference
        @OneToMany(mappedBy = "company")
        private List<Coupon> coupons;

        public Company() {
        }

        public Company(int id, String name, Credetional credetional) {
            this.id = id;
            this.name = name;
            this.credetional = credetional;
            this.coupons = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
            return "Company{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", credetional=" + credetional +
                    ", coupons=" + coupons +
                    '}';
        }


}
