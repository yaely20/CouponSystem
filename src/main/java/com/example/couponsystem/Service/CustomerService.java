package com.example.couponsystem.Service;

import com.example.couponsystem.model.Category;
import com.example.couponsystem.model.Coupon;
import com.example.couponsystem.model.Credetional;
import com.example.couponsystem.model.Customer;
import com.example.couponsystem.Reposetories.CustomerReposetory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService extends ClientService{

   // private int customerID;

    public CustomerService(){

    }

    @Override
    public boolean login(Credetional credetional) throws Exception {
        return customerReposetory.existsByCredentionals(credetional);
    }

    public void purchaseCoupon(Coupon coupon,int customerID) throws Exception {
        if(coupon==null)
            throw  new Exception("can't add a null coupon");
        if(!couponReposetory.existsById(coupon.getId()))
            throw  new Exception("coupon isn't exists");
        Coupon c=couponReposetory.findById(coupon.getId());
        if(customerReposetory.findCustomerCoupon(customerID,c.getId())!=null)
            throw  new Exception("the customer is already bought this coupon");
        if(c.getAmount()<=0)
            throw  new Exception("this coupon isn't in stock");
        LocalDate currentDate = LocalDate.now();
        if(c.getEndDate() != null && c.getEndDate().isBefore(currentDate))
            throw new Exception("this coupon is already expired.");
        Customer customer = customerReposetory.findById(customerID);
        c.setAmount(c.getAmount()-1);
        customer.addCoupon(c);
        customerReposetory.save(customer);
    }

    public ArrayList<Coupon> getCustomerCoupons(int customerID) throws Exception {
        if(customerReposetory.findById(customerID).getCoupons().isEmpty())
            throw new Exception("there aren't coupons for this customer");
        return new ArrayList<Coupon>(customerReposetory.findById(customerID).getCoupons());
    }

    public ArrayList<Coupon> getCustomerCoupons(Category category,int customerID) throws Exception {
        if(couponReposetory.findByCategoryAndCustomer(category,customerID).isEmpty())
            throw new Exception("there aren't coupons with this category for this customer");
        return new ArrayList<Coupon>(couponReposetory.findByCategoryAndCustomer(category,customerID));
    }

    public ArrayList<Coupon> getCustomerCoupons(double maxPrice,int customerID) throws Exception {
        if(maxPrice<=0)
            throw new Exception("the maxPrice can't be negative");
        if(couponReposetory.findMaxPriceCustomer(customerID,maxPrice).isEmpty())
            throw new Exception("there aren't coupons with this price limit for this customer");
        return new ArrayList<Coupon>(couponReposetory.findMaxPriceCustomer(customerID,maxPrice));
    }

    public Customer getCustomerDetails(int customerID) throws Exception {
        Customer c = customerReposetory.findById(customerID);
        if(c == null) {
            throw new Exception("customer isn't exists");
        }
        return c;
    }

    public ArrayList<Coupon> getAllCoupons(int customerID) throws Exception {
        if(customerReposetory.findCoupon(customerID).isEmpty()) {
            throw new Exception("No coupons found in the system");
        }
        return new ArrayList<Coupon>(customerReposetory.findCoupon(customerID));
    }
}
