package com.example.couponsystem.Repositories;

import com.example.couponsystem.Model.Coupon;
import com.example.couponsystem.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query("select idCustomer from Customer where credetional.email = ?1 and credetional.password =?2")

    public Integer findByEmailAndPassword(String email,String password);

    @Query("select c  from Customer c where c.idCustomer= ?1")

    public Customer findById(int customerID);

//    @Query("select case when count(c.idCustomer)>0 then 'true' else 'false' end from Customer c where c.idCustomer= ?1")
//
//    public Boolean existsById(int customerID);

    @Query("select case when count(c.idCustomer)>0 then 'true' else 'false' end from Customer c where c.credetional.email =?1")

    public boolean existsByEmail(String email);

    @Query("select c from Customer c where c.credetional.email = ?1 and c.idCustomer =?2")
    public Customer findCustomerByEmailAndId(String email , int id);
    @Query("select c from  Coupon  c where c.id in(select c.id from c.customers cust where cust.idCustomer=?1)")
    public Set<Coupon> findCoupon(int cust_id);
    @Query("select c from  Coupon c where c.id in (select c.id from  c.customers cust where  cust.idCustomer=?1 and c.id=?2)")
    public Coupon findCustomerCoupon(int custId, int cpnId);

}
