package com.example.couponsystem.Reposetories;

import com.example.couponsystem.model.Category;
import com.example.couponsystem.model.Company;
import com.example.couponsystem.model.Coupon;
import com.example.couponsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface CouponReposetory extends JpaRepository<Coupon,Integer> {

    @Query("select c from Customer  c where c.idCustomer in(select c.idCustomer from c.coupons cust where cust.id= ?1)")

    public List<Customer> getAllCouponsCustomer(int coupon_id);

    @Query("select c  from Coupon c where c.id= ?1")

    public Coupon findById(int couponID);
    @Query("select c from Coupon  c where c.category= ?1 and c.company.id= ?2")

    public List<Coupon> findByCategoryAndCompanyID(Category category, int companyID);


    @Query("select c from Coupon  c where c.price>= ?1 and c.id= ?2")

    public List<Coupon> findByMaxPrice(double price, int companyID);

    @Query("select case when count(c.id)>0 then 'true' else 'false' end from Coupon c where c.title = ?1 and c.company.id =?2")

    public Boolean existsByTitleAndCompanyID(String title, int companyID);


    @Query("select case when count(c.id)>0 then 'true' else 'false' end from Coupon c where c.title = ?1 and c.company.id =?2 and c.id= ?3")

    Integer existsByTitleAndCompanyIDAndID(String title,int companyID , int cpnId);


    @Query("select c from Coupon  c join c.customers cust where c.category= ?1 and  cust.idCustomer= ?2 ")

    public List<Coupon> findByCategoryAndCustomer(Category category,int cust_id);

    @Query("select c from Coupon c join c.customers cust where cust.idCustomer =?1 and c.price <= ?2")
    public List<Coupon> findMaxPriceCustomer(int cust_id, double maxPrice);

    @Query("select c from Coupon c where c.endDate<?1 ")
    public List<Coupon> findByEndDateBefore(Date endDate);







}
