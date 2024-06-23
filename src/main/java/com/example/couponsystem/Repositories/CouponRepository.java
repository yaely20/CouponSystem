package com.example.couponsystem.Repositories;

import com.example.couponsystem.Model.Category;
import com.example.couponsystem.Model.Coupon;
import com.example.couponsystem.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    //תחילה מחזיר את כל הקופונים שה ID שלהם =לקופון בפרמטר, ואחר כך שולף את הפרטים של הלקוח שיש לו את הקופון הזה
    @Query("select c from Customer c where c.idCustomer in (select c.idCustomer from c.coupons cust where cust.id=?1)")
    public Set<Customer> getAllCouponsCustomer(int couponId);
    @Query("select c from Coupon c where c.category=?1 and c.company.id=?2")
    public Set<Coupon> findByCategoryAndCompanyID(Category category,int companyId);
    @Query("select  c from  Coupon  c where  c.id=?1")
    public Coupon findById(int couponID);
    @Query("select c from Coupon  c where c.price>=?1 and c.id=?2")
    public Set<Coupon> findByMaxPrice(double price, int companyID);

    @Query("select case when count(c)>0 then 'true' else 'false' end from  Coupon c where c.title=?1 and c.company.id=?2 ")
    public Boolean existsByTitleAndCompanyID(String title, int companyID);
    @Query("select case when count(c)>0 then 'true' else 'false' end from  Coupon c where c.title=?1 and c.company.id=?2 and c.id=?3 ")
    public  Integer existsByTitleAndCompanyIDAndID(String title,int companyID , int cpnId);
    @Query("select c from Coupon c join  c.customers cust where c.category=?1 and cust.idCustomer=?2")
    public Set<Coupon> findByCategoryAndCustomer(Category category,int cust_id);
    @Query("select c from  Coupon c join  c.customers cust where cust.idCustomer=?1 and c.price=?2")
    public Set<Coupon> findMaxPriceCustomer(int cust_id, double maxPrice);
   @Query("select  c from  Coupon  c where  c.endDate<?1")
    public Set<Coupon> findByEndDateBefore(Date endDate);
}
