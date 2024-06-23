package com.example.couponsystem.Reposetories;

import com.example.couponsystem.model.Company;
import com.example.couponsystem.model.Coupon;
import com.example.couponsystem.model.Credetional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CompanyReposetory extends JpaRepository<Company,Integer> {


    @Query("select id from Company where credetional.email = ?1 and credetional.password =?2")

    public Integer findByEmailAndPassword(String email,String password);

    @Query("select c  from Company c where c.id= ?1")

    public Company findById(int companyID);
//    @Query("select case when count(c.id)>0 then 'true' else 'false' end from Company c where c.id= ?1")
//
//    public Boolean existsById(int companyID);

    @Query("select case when count(c.id)>0 then 'true' else 'false' end from Company c where c.credetional = ?1")

    public Boolean existsByCredentionals(Credetional credetional);
    @Query("select case when count(c.id)>0 then 'true' else 'false' end from Company c where c.name = ?1 or c.credetional.email =?2")

    public Boolean existsByNameOrEmail(String name, String email);

    @Query("select c from Company c where c.credetional.email = ?1 and c.id =?2")

    public Company findCompanyByEmailAndId(String email, int id);

    @Query("select c from Coupon c where c.company.id= ?1")

    public List<Coupon> findCompanyCoupons(int companyID);



}
