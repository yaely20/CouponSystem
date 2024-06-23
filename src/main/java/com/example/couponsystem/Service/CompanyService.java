package com.example.couponsystem.Service;

import com.example.couponsystem.model.*;
import com.example.couponsystem.Reposetories.CompanyReposetory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompanyService extends ClientService{

   // private int companyID;

    public CompanyService() {

    }


    @Override
    public boolean login(Credetional credetional) throws Exception {
        return companyReposetory.existsByCredentionals(credetional);
    }

    public void addCoupon(Coupon coupon,int companyID) throws Exception {
        if(coupon==null)
            throw  new Exception("can't add a null coupon");
        if(couponReposetory.existsById(coupon.getId()))
            throw  new Exception("coupon is already exists");
       // coupon.setId();
        //Check if the start date is after current time
        checkdate(coupon.getStartDate());
        //Check if the start date before end date
        if(coupon.getEndDate() != null && coupon.getStartDate().isAfter(coupon.getEndDate()) )
            throw new Exception("invalid end date");
        //Check if the end date is after current time
        checkdate(coupon.getEndDate());
        if(!companyReposetory.existsById(coupon.getCompany().getId()))
            throw new Exception("the company isn't exists");
        if(couponReposetory.existsByTitleAndCompanyID(coupon.getTitle(),coupon.getCompany().getId()))
            throw new Exception("coupons' title is already exists in this company");
        couponReposetory.save(coupon);

    }

    public void updateCoupon(Coupon coupon,int companyID) throws Exception {
        if(coupon==null)
            throw  new Exception("can't add a null coupon");
        if(!couponReposetory.existsById(coupon.getId()))
            throw  new Exception("coupon isn't exists");
        coupon.setId(companyID);
        //Check if the start date is after current time
        checkdate(coupon.getStartDate());
        //Check if the start date before end date
        if(coupon.getEndDate() != null && coupon.getStartDate().isAfter(coupon.getEndDate()) )
            throw new Exception("invalid end date");
        //Check if the end date is after current time
        checkdate(coupon.getEndDate());
        if(!companyReposetory.existsById(coupon.getCompany().getId()))
            throw new Exception("the company isn't exists");
//        if(couponReposetory.existsByTitleAndCompanyID(coupon.getTitle(),coupon.getCompany().getId()))
//            throw new Exception("coupons' title is already exists in this company");
        couponReposetory.save(coupon);

    }

    public void deleteCoupon(int couponID) throws Exception {
        if(couponID<=0)
            throw new Exception("invalid id");
        if(!customerReposetory.existsById(couponID))
            throw new Exception("customer isn't exists");
        Coupon coupon=couponReposetory.findById(couponID);
        couponReposetory.delete(coupon);
    }

    public ArrayList<Coupon> getCompanyCoupons(int companyID) throws Exception {
        if(companyReposetory.findCompanyCoupons(companyID).isEmpty())
            throw new Exception("there aren't coupons");
        return new ArrayList<Coupon> (companyReposetory.findCompanyCoupons(companyID));
    }

    public ArrayList<Coupon> getCompanyCoupons(Category category,int companyID) throws Exception {
        if(couponReposetory.findByCategoryAndCompanyID(category,companyID).isEmpty())
            throw new Exception("there aren't coupons with this category");
        return new ArrayList<Coupon> (couponReposetory.findByCategoryAndCompanyID(category,companyID));
    }

    public ArrayList<Coupon> getCompanyCoupons(double maxPrice,int companyID) throws Exception {
        if(maxPrice<=0)
            throw new Exception("the maxPrice can't be negative");
        if(couponReposetory.findByMaxPrice(maxPrice,companyID).isEmpty())
            throw new Exception("there aren't coupons with this price limit");
        return new ArrayList<Coupon> (couponReposetory.findByMaxPrice(maxPrice,companyID));
    }

    public Company getCompanyDetails(int companyID) throws Exception {
        Company c=companyReposetory.findById(companyID);
        if(c==null)
            throw new Exception("company isn't exists");
        return c;
    }
    //Check if the date is after the current date
    private void checkdate(LocalDate date) throws Exception {
        if(date != null) {
            LocalDate currentDate = LocalDate.now();
            if(date.isBefore(currentDate)) {
                throw new Exception("The date have to be after the current time");
            }
        }
    }







}
